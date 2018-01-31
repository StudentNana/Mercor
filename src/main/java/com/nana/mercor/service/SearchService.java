package com.nana.mercor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.nana.mercor.bringmeister.Option__;
import com.nana.mercor.bringmeister.Product;
import com.nana.mercor.bringmeister.SearchResponse;
import com.nana.mercor.carousel.CarouselElementInfo;
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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.nana.mercor.carousel.CarouselElementInfo.buildCarouselElementInfoForSearchResult;
import static com.nana.mercor.service.ResponseUtils.buildCardResponse;
import static com.nana.mercor.service.ResponseUtils.buildCarouselResponse;
import static com.nana.mercor.service.ResponseUtils.buildPlainApiaiResponse;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * SearchService is class to create a request to online shop and get list of article.
 *
 * @author  Gulnaz Sagitova
 */

@Service
public class SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);
    private static final String URL = "https://berlin.bringmeister.de/fast-search/index.php";
    private static final String GETRAENKE_CATEGORY_ID = "2847";
    private static final Map<String, String> QUERY_TEMPLATE = ImmutableMap.of(
            "cat", GETRAENKE_CATEGORY_ID,
            "pc", "%d",
            "q=", "%s"
    );
    private static final String QUERY_TEMPLATE_STRING = Joiner.on("&").withKeyValueSeparator("=").join(QUERY_TEMPLATE);
    private static final String PRODUCTS_NOT_FOUND_MESSAGE = "Leider wurden keine Artikeln gefunden";
    private static final String PRODUCTS_FOUND_MESSAGE = "So schau mal, was ich gefunden habe";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private List<Product> lastProducts = new ArrayList<>();

    /**
     * Build on-line shop query string
     * @param params
     * @param limit
     */
    private String buildQuery(final List<String> params, final int limit) {
        return String.format(QUERY_TEMPLATE_STRING, limit, String.join(" ", params));
    }

    /**
     * exequte POST request against on-line shop url
     * @param article  name of the article
     * @param packageType packing type of the article
     * @param brandId  Brand name of the article
     * @param limit total count of articles
     */
    private Optional<SearchResponse> getSearchResponse(final String article, final String packageType,
                                                       final String brandId, final int limit) {

        final List<String> params = Stream.of(article, packageType)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
        String query = buildQuery(params, limit);

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

    /**
     * Search for an article with given article name, packing type, brand name and specialization
     * Returns a simple or carousel response to Dialogflow
     * @param article  name of the article
     * @param packageType packing type of the article
     * @param brandName   Brand name of the article
     * @param specialization   additional info of article
     */
    public String search(final String article, final String packageType, final String brandName,
                         final String specialization) {
        final Optional<SearchResponse> searchResponse = getSearchResponse(article, packageType, null, 100);
        if(searchResponse.isPresent()) {
            List<Product> products = getProductsFromResponse(searchResponse.get(), article, packageType, brandName);
            if (!products.isEmpty()) {
                lastProducts = products;
                final ArrayList<CarouselElementInfo> carouselElementInfos = new ArrayList<>();
                AtomicInteger counter = new AtomicInteger(0);
                products.stream().forEach(p -> {
                    final CarouselElementInfo carouselElementInfo =
                            buildCarouselElementInfoForSearchResult(p, counter.incrementAndGet());
                    carouselElementInfos.add(carouselElementInfo);
                });
                String responseJson;
                if (carouselElementInfos.size() == 1) {
                    responseJson = buildCardResponse(PRODUCTS_FOUND_MESSAGE, PRODUCTS_FOUND_MESSAGE,
                            PRODUCTS_FOUND_MESSAGE, carouselElementInfos.get(0));
                } else {
                    responseJson = buildCarouselResponse(PRODUCTS_FOUND_MESSAGE, PRODUCTS_FOUND_MESSAGE,
                            PRODUCTS_FOUND_MESSAGE, carouselElementInfos);
                }
                return responseJson;
            }
        }

        return buildPlainApiaiResponse(PRODUCTS_NOT_FOUND_MESSAGE, PRODUCTS_NOT_FOUND_MESSAGE, article, packageType);
    }

    /**
     * Get articles from response online shop
     * @param searchResponse  response from on-line shop
     * @param article   name of the article
     * @param packageType packing type of the article
     * @param brandName   Brand name of the article
     */
    private List<Product> getProductsFromResponse(final SearchResponse searchResponse, final String article,
                                                  final String packageType, final String brandName) {
        List<Product> products = searchResponse.getProducts();
        if (!products.isEmpty() && isNotEmpty(brandName)) {
            final Optional<Option__> brandOptional = getBrand(searchResponse, brandName);
            if (brandOptional.isPresent()) {
                final Optional<SearchResponse> brandedSearchResponse
                        = getSearchResponse(article, packageType, brandOptional.get().getId(), 10);
                if (brandedSearchResponse.isPresent()) {
                    products = brandedSearchResponse.get().getProducts();
                }
            }
        }
        return products.subList(0, 10);
    }

    /**
     * Returns an brand name of the article
     * @param searchResponse  name of the article
     * @param brand packing type of the article
     */
    private Optional<Option__> getBrand(final SearchResponse searchResponse, final String brand) {
        final List<Option__> options = searchResponse.getFacets().getFilters().getBmBrand().getOptions();
        return options.stream()
                .filter(b -> b.getName().toLowerCase().contains(brand.toLowerCase()))
                .findFirst();
    }

    /**
     * Returns list of article after last search
     */
    public List<Product> getLastProducts() {
        return Collections.unmodifiableList(lastProducts);
    }

}
