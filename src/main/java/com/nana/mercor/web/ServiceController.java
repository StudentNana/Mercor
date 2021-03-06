package com.nana.mercor.web;

import com.nana.mercor.dialogflow.ApiaiQuery;
import com.nana.mercor.service.CartService;
import com.nana.mercor.service.IntentType;
import com.nana.mercor.service.ResponseUtils;
import com.nana.mercor.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mercor")
public class ServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    private final SearchService searchService;
    private final CartService cartService;

    @Autowired
    public ServiceController(SearchService searchService, CartService cartService){
        this.searchService = searchService;
        this.cartService = cartService;
    }

    @RequestMapping(value = "webhook", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String webhook(@RequestBody ApiaiQuery apiaiQuery) {

        final String intentName = apiaiQuery.getResult().getMetadata().getIntentName();
        final String response;
        if (IntentType.SEARCH.getIntentName().equals(intentName)) {
            response = search(apiaiQuery);
        } else if (IntentType.ADD_TO_CART.getIntentName().equals(intentName)) {
            response = addToCart(apiaiQuery);
        } else if (IntentType.SHOW_CART.getIntentName().equals(intentName)) {
            response = showCart();
        } else if (IntentType.CLEAR_CART.getIntentName().equals(intentName)) {
            response = clearCart();
        } else {
            response = String.format("This type of intent is not implemented yet: %s", intentName);
        }

        LOGGER.info(response);

        return response;
    }

    private String showCart() {
        return cartService.showCart();
    }

    private String clearCart() {
        return cartService.clearCart();
    }

    private String search(ApiaiQuery apiaiQuery) {
        return searchService.search(
                apiaiQuery.getResult().getParameters().getArticle(),
                apiaiQuery.getResult().getParameters().getPackage(),
                apiaiQuery.getResult().getParameters().getBrand(),
                apiaiQuery.getResult().getParameters().getSpezialization());
    }

    public String addToCart(ApiaiQuery apiaiQuery) {
        final boolean result = cartService.addToCart(apiaiQuery.getResult().getParameters().getArticleNumber(),
                Integer.parseInt(apiaiQuery.getResult().getParameters().getArticleNumber()));
        final String message;
        if (result) {
            message = "Ich habe den Artikel in den Warenkorb gelegt.";
        } else {
            message = "Etwas ist schief gelaufen :( Versuch bitte noch ein Mal.";
        }
        return ResponseUtils.buildPlainApiaiResponse(message, message,
                apiaiQuery.getResult().getParameters().getArticleNumber(), apiaiQuery.getResult().getParameters().getCount());
    }
}
