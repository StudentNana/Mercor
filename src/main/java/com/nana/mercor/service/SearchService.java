package com.nana.mercor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nana.mercor.bringmeister.SearchResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class SearchService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);
    public static final String URL = "https://berlin.bringmeister.de/fast-search/index.php";
    public static final String QUERY_TEMPLATE = "cat=2847&q=%s";
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public Optional<SearchResponse> search(final String article, final String packageType) {

        final String query = String.format(QUERY_TEMPLATE, article);
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

}
