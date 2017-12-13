package com.nana.mercor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.nana.mercor.bringmeister.Option__;
import com.nana.mercor.bringmeister.Product;
import com.nana.mercor.bringmeister.SearchResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);
    private static final String URL = "https://berlin.bringmeister.de/fast-search/index.php";
    private static final String GETRAENKE_CATEGORY_ID = "2847";
    private static final String ARTICLES_COUNT_PER_PAGE = "100";
    private static final Map<String, String> QUERY_TEMPLATE = ImmutableMap.of(
            "cat", GETRAENKE_CATEGORY_ID,
            "pc", ARTICLES_COUNT_PER_PAGE,
            "q=", "%s"
    );
    private static final String QUERY_TEMPLATE_STRING = Joiner.on("&").withKeyValueSeparator("=").join(QUERY_TEMPLATE);
    private static final String PRODUCTS_NOT_FOUND_MESSAGE = "Keine Artikeln gefunden";
    private static final String PRODUCTS_FOUND_MESSAGE = "Gefundene Artikeln: %d";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final String responseTemplate;

    String[] title = new String[3];
    String[] img = new String[3];

    public SearchService() throws IOException, URISyntaxException {
        responseTemplate = getResourceFileAsString("templates/googleSearchResponse.json");
    }

    private Optional<SearchResponse> getSearchResponse(final String article, final String packageType,
                                                       final String brandId) {

        final List<String> params = Stream.of(article, packageType)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
        String query = String.format(QUERY_TEMPLATE_STRING, String.join(" ", params));
        if (isNotEmpty(brandId)) {
            query = query + String.format("bm_brand[%s]=%s", brandId, brandId);
        }
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

    public String search(final String article, final String packageType, final String brand, final String specialization) {
        final Optional<SearchResponse> searchResponse = getSearchResponse(article, packageType, null);
        String message = PRODUCTS_NOT_FOUND_MESSAGE;
        if(searchResponse.isPresent()) {
            List<Product> products = searchResponse.get().getProducts();
            if (!products.isEmpty() && isNotEmpty(brand)) {
                final List<Option__> options = searchResponse.get().getFacets().getFilters().getBmBrand().getOptions();
                final Optional<Option__> brandOptional = options.stream()
                        .filter(b -> b.getName().toLowerCase().contains(brand.toLowerCase()))
                        .findFirst();
                if (brandOptional.isPresent()) {
                    final Optional<SearchResponse> brandedSearchResponse
                            = getSearchResponse(article, packageType, brandOptional.get().getId());
                    if (brandedSearchResponse.isPresent()) {
                        products = brandedSearchResponse.get().getProducts();
                    }
                }
            }
            if (!products.isEmpty()) {
                message = String.format(PRODUCTS_FOUND_MESSAGE, products.size());
            }
        }

        return String.format(responseTemplate, message, message);
    }

//    public Optional<String> getBrandIdFromResponse()

    public String getResourceFileAsString(String resourceFileName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(resourceFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader.lines().collect(Collectors.joining("\n"));
    }


}
