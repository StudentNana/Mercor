package com.nana.mercor.service;

import com.nana.mercor.bringmeister.Product;
import com.nana.mercor.carousel.CarouselElementInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

}
