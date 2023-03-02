package com.travel.b1910025.security.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import com.travel.b1910025.models.Tour;
import com.travel.b1910025.models.User;
import com.travel.b1910025.models.CartItem;
import com.travel.b1910025.payload.request.CartItemRequest;
import com.travel.b1910025.payload.response.CartItemResponse;
import com.travel.b1910025.repository.CartItemRepository;
import com.travel.b1910025.repository.TourRepository;
import com.travel.b1910025.repository.UserRepository;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	TourRepository tourRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CartItemRepository cartItemRepo;

	@Override
	public CartItemResponse createCartItem(CartItemRequest cartItemRequest) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(cartItemRequest.getUser_id()).orElseThrow();
		Tour tour = tourRepo.findById(cartItemRequest.getTour_id()).orElseThrow();
		CartItem cartItem = new CartItem(user, tour, cartItemRequest.getQuantity());
		if((tour.getSlot()<cartItemRequest.getQuantity())||(tour.getSlot()<=0))
		{
			return new CartItemResponse(cartItem, null, "Sold out!");
		}
		CartItem isInCart = cartItemRepo.findByUser_IdAndTour_IdAndStatus(cartItemRequest.getUser_id(), cartItemRequest.getTour_id(), 0);
		if (isInCart != null) {
			if (isInCart.getQuantity() + cartItemRequest.getQuantity() <= isInCart.getTour().getSlot())
				isInCart.setQuantity(isInCart.getQuantity() + cartItemRequest.getQuantity());
			else
				isInCart.setQuantity(isInCart.getTour().getSlot());
			cartItemRepo.save(isInCart);
			
			return new CartItemResponse(isInCart, null, "Cart Updated");
		} else {
			cartItemRepo.save(cartItem);
			return new CartItemResponse(cartItem, null, "Cart Added");
		}
	}

	@Override
	public Optional<CartItem> updateCartItem(long cartItemId, CartItemRequest cartItemRequest) {
		// TODO Auto-generated method stub
		Optional<CartItem> cartItem = cartItemRepo.findById(cartItemId);

		if (cartItem.isPresent()) {
			cartItem.get().setQuantity(cartItemRequest.getQuantity());

			cartItemRepo.save(cartItem.get());
			return cartItem;
		} else {
			throw new InvalidConfigurationPropertyValueException("cartItemId", cartItemId, "Not found");
		}

	}

	@Override
	public void deteleCartItem(long cartItemId) {
		// TODO Auto-generated method stub
		if (cartItemRepo.findById(cartItemId).get().getId().equals(cartItemId)) {
			cartItemRepo.deleteById(cartItemId);
		} else
			throw new InvalidConfigurationPropertyValueException("cartItemId", cartItemId, "Not Found");

	}

	@Override
	public List<CartItem> getCart(long userId) {
		// TODO Auto-generated method stub
		List<CartItem> list = cartItemRepo.findByUser_Id(userId);
		List<CartItem> list2 = new ArrayList<>();
		
		for (CartItem cartItem : list) {
			if(cartItem.getStatus()==0)
				list2.add(cartItem);
		}
		return list2;
	}

}
