package com.nana.mercor.service;

import com.nana.mercor.bringmeister.Product;
import com.nana.mercor.carousel.CarouselElementInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.nana.mercor.service.ResponseUtils.buildCardResponse;
import static com.nana.mercor.service.ResponseUtils.buildCarouselResponse;
import static com.nana.mercor.service.ResponseUtils.buildPlainApiaiResponse;

/**
 * CartService is class to add article to cart, clear it and to show cart.
 *
 * @author  Gulnaz Sagitova
 */

@Service
public class CartService {

    private final SearchService searchService;
    private final List<CarouselElementInfo> cart = new ArrayList<>();

    /**
     * Constructor for the class CartService
     * @param searchService - Service for searching an article with the given parameters
     */
    public CartService(final SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Add an article to cart
     * @param articleNumber  position in the carousel
     * @param count total number of the article
     */
    public boolean addToCart(final String articleNumber, final int count) {
        final Product product = searchService.getLastProducts().get(Integer.parseInt(articleNumber));
        if (product != null) {
            cart.add(CarouselElementInfo.buildCarouselElementInfoForCart(product, count));
            return true;
        } else {
            // TODO: Try to send search request to find product
            return false;
        }
    }

    /**
     * Show a cart with the articles
     * Returns a response object - carousel of the article
     */
    public String showCart() {
        if (cart.isEmpty()) {
            final String message = "Dein Warenkorb ist leer.";
            return buildPlainApiaiResponse(message, message, "", "");
        } else if (cart.size() == 1) {
            final String message = "Dein Warenkorb:";
            return buildCardResponse(message, message, message, cart.get(0));
        } else {
            final String message = "Dein Warenkorb:";
            return buildCarouselResponse(message, message, message, cart);
        }
    }

    /**
     * Remove all article from the cart
     * Returns a response object
     */
    public String clearCart() {
        cart.clear();
        final String message = "Die Artikeln wurde aus Deinem Warenkorb geloescht.";
        return buildPlainApiaiResponse(message, message, "", "");
    }
}
