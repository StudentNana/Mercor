package com.nana.mercor.service;

public enum IntentType {

    SEARCH("orderRecognition"),
    ADD_TO_CART("addToCart"),
    CLEAR_CART("clearCart"),
    SHOW_CART("showCart");

    private String intentName;

    IntentType(String intentName) {
        this.intentName = intentName;
    }

    public String getIntentName() {
        return intentName;
    }
}
