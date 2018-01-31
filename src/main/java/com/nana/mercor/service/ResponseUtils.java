package com.nana.mercor.service;

import com.nana.mercor.carousel.CarouselElementInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ResponseUtils is util class to create a response from server to Dialogflow
 *
 * @author  Gulnaz Sagitova
 */
public class ResponseUtils {

    /**
     * Constructor for the class ResponseUtils
     */
    private ResponseUtils() { }

    /**
     * Create a simple response
     * @param speech  - text that should be speak
     * @param displayText - text that should be displayed
     * @param article - name of the article
     * @param packageType - packing of the article
     */
    public static String buildPlainApiaiResponse(final String speech, final String displayText, final String article,
                                          final String packageType) {
        final String template = getResourceFileAsString("templates/plainApiaiResponse.json");
        return String.format(template, speech, displayText, article, packageType);
    }

    /**
     * Reads resource file from class path and returns it as String
     * @param resourceFileName  JSON file
     */
    private static String getResourceFileAsString(String resourceFileName) {
        InputStream is = ResponseUtils.class.getClassLoader().getResourceAsStream(resourceFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader.lines().collect(Collectors.joining("\n"));
    }

    /**
     * Create a carousel response
     * @param speech  - text that should be speak
     * @param displayText - text that should be displayed
     * @param textToSpeech - text that should be speak
     * @param elements - list of articles
     */
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

    /**
     * Create a single element card response
     * @param speech  - text that should be speak
     * @param displayText - text that should be displayed
     * @param textToSpeech - text that should be speak
     * @param element - article
     */
    public static String buildCardResponse(final String speech, final String displayText, final String textToSpeech,
                                               final CarouselElementInfo element) {
        final String responseTemplate = getResourceFileAsString("templates/basicCardResponse.json");
        return String.format(responseTemplate, speech, displayText, speech, displayText,
                element.getTitle(), element.getDescription(), element.getDescription(), element.getUrl(),
                element.getAccessibilityText());
    }

    /**
     * create synonyms
     * @param synonyms list of synonyms
     */
    private static String buildSynonyms(final List<String> synonyms) {
        final List<String> synonymsWithQuotes = synonyms.stream().map(s -> "\"" + s + "\"").collect(Collectors.toList());
        return String.join(",\n", synonymsWithQuotes);
    }

}
