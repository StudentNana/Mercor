package com.nana.mercor.service;
/**
 *
 * Enum holding types of the intents
 * @author  Gulnaz Sagitova
 */
public enum IntentType {

    SEARCH("orderRecognition"),
    ADD_TO_CART("addToCart"),
    CLEAR_CART("clearCart"),
    SHOW_CART("showCart");

    private String intentName;

    /**
     * Constructor IntentType
     * @param intentName - name of Intent like in Dialogflow
     */
    IntentType(String intentName) {
        this.intentName = intentName;
    }

    public String getIntentName() {
        return intentName;
    }
}
