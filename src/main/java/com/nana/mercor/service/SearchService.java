package com.nana.mercor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.nana.mercor.bringmeister.Product;
import com.nana.mercor.bringmeister.SearchResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);
    private static final String URL = "https://berlin.bringmeister.de/fast-search/index.php";
    private static final String GETRAENKE_CATEGORY_ID = "2847";
    private static final String ARTICLES_COUNT_PER_PAGE = "100";
    private static final Map<String, String> QUERY_TEMPLATE = ImmutableMap.of(
            "cat", GETRAENKE_CATEGORY_ID,
            "pc", ARTICLES_COUNT_PER_PAGE,
            "q=", "%s %s"
    );
    private static final String QUERY_TEMPLATE_STRING = Joiner.on("&").withKeyValueSeparator("=").join(QUERY_TEMPLATE);
    private static final String PRODUCTS_NOT_FOUND_MESSAGE = "Keine Artikeln gefunden";
    private static final String PRODUCTS_FOUND_MESSAGE = "Gefundene Artikeln: %d";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final String responseTemplate;

    public SearchService() throws IOException, URISyntaxException {
        responseTemplate = readFileToString("templates/searchResponse.json");
    }

    private Optional<SearchResponse> getSearchResponse(final String article, final String packageType) {

        final String query = String.format(QUERY_TEMPLATE_STRING, article, packageType);
        LOGGER.info("Query string: {}", query);

        final ByteArrayEntity requestEntity = new ByteArrayEntity(query.getBytes());
        requestEntity.setContentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType());

        HttpPost postMethod = new HttpPost(URL);
        postMethod.setEntity(requestEntity);

        final HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpResponse rawResponse = httpClient.execute(postMethod);
            final String json = IOUtils.toString(rawResponse.getEntity().getContent(), StandardCharsets.UTF_8.displayName());
            final SearchResponse searchResponse = MAPPER.readValue(json, SearchResponse.class);
            return Optional.of(searchResponse);
        } catch (IOException e) {
            LOGGER.info("Error querying online shop", e);
            return Optional.empty();
        }
    }

    public String search(final String article, final String packageType) {
        final Optional<SearchResponse> searchResponse = getSearchResponse(article, packageType);
        String message = PRODUCTS_NOT_FOUND_MESSAGE;
        if(searchResponse.isPresent()) {
            final List<Product> products = searchResponse.get().getProducts();
            if (!products.isEmpty()) {
                message = String.format(PRODUCTS_FOUND_MESSAGE, products.size());
            }
        }
        return String.format(responseTemplate, message, message, article, packageType);
    }

    private String readFileToString(final String pathString) throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource(pathString).toURI());
        StringBuilder data = new StringBuilder();
        Stream<String> lines = Files.lines(path);
        lines.forEach(line -> data.append(line).append("\n"));
        lines.close();
        return data.toString();
    }

}
