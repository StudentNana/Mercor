package com.nana.mercor.web;

import com.nana.mercor.domain.ApiaiQuery;
import com.nana.mercor.service.CartService;
import com.nana.mercor.service.IntentType;
import com.nana.mercor.service.ResponseService;
import com.nana.mercor.service.SearchService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mercor")
public class ServiceController {

    private final SearchService searchService;
    private final CartService cartService;

    public ServiceController(SearchService searchService, CartService cartService){
        this.searchService = searchService;
        this.cartService = cartService;
    }

    @RequestMapping(value = "webhook", method = RequestMethod.POST)
    @ResponseBody
    public String webhook(@RequestBody ApiaiQuery apiaiQuery) {
        final String intentName = apiaiQuery.getResult().getMetadata().getIntentName();
        if (IntentType.SEARCH.getIntentName().equals(intentName)) {
            return search(apiaiQuery);
        } else if (IntentType.ADD_TO_CART.getIntentName().equals(intentName)) {
            return addToCart(apiaiQuery);
        } else if (IntentType.SHOW_CART.getIntentName().equals(intentName)) {
            return showCart();
        } else if (IntentType.CLEAR_CART.getIntentName().equals(intentName)) {
            return clearCart();
        } else {
            return String.format("This type of intent is not implemented yet: %s", intentName);
        }
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
            message = "Product added to cart.";
        } else {
            message = "Something wrong :( Product was NOT added";
        }
        return ResponseService.buildPlainApiaiResponse(message, message,
                apiaiQuery.getResult().getParameters().getArticleNumber(), apiaiQuery.getResult().getParameters().getCount());
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "{\n" +
                "        \"speech\": \"Hello speech from spring boot!\",\n" +
                "        \"displayText\": \"Hello text from spring boot!\",\n" +
                "  \t\t\"data\": {},\n" +
                "        \"contextOut\": [{\"name\":\"weather\", \"lifespan\":2, \"parameters\":{\"city\":\"Rome\"}}],\n" +
                "        \"source\": \"apiai-weather-webhook-sample\"\n" +
                "}";
    }

}
