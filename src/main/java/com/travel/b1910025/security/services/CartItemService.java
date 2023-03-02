package com.travel.b1910025.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.travel.b1910025.models.CartItem;
import com.travel.b1910025.payload.request.CartItemRequest;
import com.travel.b1910025.payload.response.CartItemResponse;

@Component
public interface CartItemService {
	CartItemResponse createCartItem(CartItemRequest cartItemRequest);

	Optional<CartItem> updateCartItem(long cartItemId, CartItemRequest cartItem);

	void deteleCartItem(long cartItemId);

	List<CartItem> getCart(long userId);
	


}
