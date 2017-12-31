package com.nana.mercor.service;

import com.nana.mercor.bringmeister.Product;
import com.nana.mercor.carousel.CarouselElementInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nana.mercor.service.ResponseService.buildCarouselResponse;
import static com.nana.mercor.service.ResponseService.buildPlainApiaiResponse;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;

@Service
public class CartService {

    private final SearchService searchService;
    private final List<CarouselElementInfo> cart = new ArrayList<>();

    public CartService(final SearchService searchService) {
        this.searchService = searchService;
    }

    public boolean addToCart(final String articleId, final int count) {
        final Optional<Product> product = searchService.getLastProducts().stream()
                .filter(p -> p.getId().equalsIgnoreCase(articleId))
                .findFirst();
        if (product.isPresent()) {
            cart.add(CarouselElementInfo.buildCarouselElementInfoForCart(product.get(), count));
            return true;
        } else {
            // TODO: Try to send search request to find product
            return false;
        }
    }

    public String showCart() {
        if (cart.isEmpty()) {
            final String message = "Your cart is empty";
            return buildPlainApiaiResponse(message, message, "", "");
        } else {
            final String message = "Here is your cart content:";
            return buildCarouselResponse(message, message, message, cart);
        }
    }

    public String clearCart() {
        cart.clear();
        final String message = "Your cart has been cleared";
        return buildPlainApiaiResponse(message, message, "", "");
    }
}
