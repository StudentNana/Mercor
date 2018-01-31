package com.nana.mercor.carousel;

import com.google.common.collect.ImmutableList;
import com.nana.mercor.bringmeister.Product;

import java.util.Collections;
import java.util.List;

/**
 * It is dialogflow object to create carousel list of articles
 *
 * @author  Gulnaz Sagitova
 */

public class CarouselElementInfo {

    private static final Object EURO = "€";

    private final String title;
    private final String description;
    private final String url;
    private final String accessibilityText;
    private final String key;
    private final List<String> synonyms;
    private final int count;

    /**
     * Constructor for the class CarouselElementInfo
     * @param title - Name of the article
     * @param description - additional Information of Article
     * @param url - url for the picture
     * @param accessibilityText - additional Information
     * @param key - unique key of the article
     * @param synonyms - synonyms of key
     * @param count - total Number of the article
     */
    public CarouselElementInfo(String title, String description, String url, String accessibilityText, String key,
                               int count, List<String> synonyms) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.accessibilityText = accessibilityText;
        this.key = key;
        this.synonyms = synonyms;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getAccessibilityText() {
        return accessibilityText;
    }

    public String getKey() {
        return key;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public int getCount() {
        return count;
    }

    /**
     * Create carousel for response for a cart
     * @param product  a carousel of found article
     * @param count  a carousel of found article
     */
    public static CarouselElementInfo buildCarouselElementInfoForCart(final Product product, final int count) {
        final String description = String.format("%d * %s = %4.2f %s\\n  \\n%s", count, product.getFormatedPrice(),
                product.getPrice() * count, EURO, product.getPacking());
        return new CarouselElementInfo(
                product.getName(),
                description,
                "https:" + product.getImageUrl(),
                product.getName(),
                product.getId(),
                count,
                Collections.EMPTY_LIST);
    }

    /**
     * Create carousel for response after search
     * @param product  a carousel of found article
     * @param index  a carousel of found article
     */
    public static CarouselElementInfo buildCarouselElementInfoForSearchResult(final Product product, final int index) {
        return new CarouselElementInfo(
                String.format("%d. %s", index, product.getName()),
                String.format("%s %s\\n  \\n%s", product.getFormatedPrice(), EURO, product.getPacking()),
                "https:" + product.getImageUrl(),
                product.getName(),
                product.getId(),
                0,
                ImmutableList.of(Integer.toString(index)));
    }

}
