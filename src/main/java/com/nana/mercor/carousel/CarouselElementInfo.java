package com.nana.mercor.carousel;

import com.google.common.collect.ImmutableList;
import com.nana.mercor.bringmeister.Product;

import java.util.List;

public class CarouselElementInfo {

    private static final Object EURO = "Euro";

    private final String title;
    private final String description;
    private final String url;
    private final String accessibilityText;
    private final String key;
    private final List<String> synonyms;


    public CarouselElementInfo(String title, String description, String url, String accessibilityText, String key,
                               List<String> synonyms) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.accessibilityText = accessibilityText;
        this.key = key;
        this.synonyms = synonyms;
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

    public static CarouselElementInfo buildCarouselElementInfo(final Product product, final int count) {
        return new CarouselElementInfo(
                product.getName(),
                String.format("%s %s \\n %s", product.getFormatedPrice(), EURO, product.getPacking()),
                "https:" + product.getImageUrl(),
                product.getName(),
                product.getId(),
                ImmutableList.of(Integer.toString(count), product.getName().split(" ")[0]));
    }

}
