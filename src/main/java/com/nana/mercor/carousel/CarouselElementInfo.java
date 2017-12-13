package com.nana.mercor.carousel;

import java.util.List;

public class CarouselElementInfo {

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

}
