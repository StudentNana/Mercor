package com.nana.mercor.service;

import com.nana.mercor.carousel.CarouselElementInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtils {

    private ResponseUtils() { }

    public static String buildPlainApiaiResponse(final String speech, final String displayText, final String article,
                                          final String packageType) {
        final String template = getResourceFileAsString("templates/plainApiaiResponse.json");
        return String.format(template, speech, displayText, article, packageType);
    }

    private static String getResourceFileAsString(String resourceFileName) {
        InputStream is = ResponseUtils.class.getClassLoader().getResourceAsStream(resourceFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader.lines().collect(Collectors.joining("\n"));
    }

    public static String buildCarouselResponse(final String speech, final String displayText, final String textToSpeech,
                                               final List<CarouselElementInfo> elements) {
        final String responseTemplate = getResourceFileAsString("templates/carouselResponse.json");
        final String elementTemplate = getResourceFileAsString("templates/carouselElement.json");
        final String carouselElementsString = elements.stream()
                .map(e -> String.format(elementTemplate, e.getTitle(), e.getDescription(), e.getUrl(),
                        e.getAccessibilityText(), e.getKey(), buildSynonyms(e.getSynonyms())))
                .collect(Collectors.joining(","));
        return String.format(responseTemplate, speech, displayText, textToSpeech, carouselElementsString);
    }

    private static String buildSynonyms(final List<String> synonyms) {
        final List<String> synonymsWithQuotes = synonyms.stream().map(s -> "\"" + s + "\"").collect(Collectors.toList());
        return String.join(",\n", synonymsWithQuotes);
    }

}
