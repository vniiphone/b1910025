//package com.travel.b1910025.security.services;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import com.travel.b1910025.models.Firm;
//import com.travel.b1910025.models.Category;
//import com.travel.b1910025.models.Tour;
//import com.travel.b1910025.payload.request.TourRequest;
//import com.travel.b1910025.payload.response.MessageResponse;
//import com.travel.b1910025.payload.response.TourResponse;
//import com.travel.b1910025.repository.FirmRepository;
//import com.travel.b1910025.repository.CategoryRepository;
//import com.travel.b1910025.repository.TourRepository;
//import org.springframework.data.domain.Sort;
//
//@Service
//public class TourServiceImpl implements TourService {
//	@Autowired
//	CategoryRepository categoryRepo;
//	@Autowired
//	FirmRepository firmRepo;
//	@Autowired
//	TourRepository tourRepo;
//
//
//
//	@Override
//	public Tour createTour(TourRequest tourRequest) {
//		Category category = categoryRepo.findById(productRequest.getCategory_id()).orElseThrow();
//		Firm firm = firmRepo.findById(tourRequest.getFirm_id()).orElseThrow();
//		Tour tour = new Tour(tourRequest.getName(), tourRequest.getDescription(),
//				productRequest.getStock(), productRequest.getPrice(), productRequest.getModelYear(),
//				productRequest.getImageUrl(), productRequest.getImagePublicId(), category, brand);
//			// Thiếu thuộc tính của Tour khi thêm
//
//		return tourRepo.save(tour);
//	}
//
//	@Override
//	public Optional<Tour> updateTour(Long tourId, TourRequest tourRequest) {
//		// TODO Auto-generated method stub
//		Optional<Tour> tour = tourRepo.findById(tourId);
//		if (tour.isPresent()) {
//			//Thiếu thuộc tính khi sửa
//			Category category = categoryRepo.findById(productRequest.getCategory_id()).orElseThrow();
//			Brand brand = brandRepo.findById(productRequest.getBrand_id()).orElseThrow();
//			product.get().setName(productRequest.getName());
//			product.get().setDescription(productRequest.getDescription());
//			product.get().setPrice(productRequest.getPrice());
//			product.get().setStock(productRequest.getStock());
//			product.get().setModelYear(productRequest.getModelYear());
//			product.get().setImageUrl(productRequest.getImageUrl());
//			product.get().setImagePublicId(productRequest.getImagePublicId());
//			product.get().setCategory(category);
//			product.get().setBrand(brand);
//			productRepo.save(product.get());
//			return tour;
//		} else {
//			throw new InvalidConfigurationPropertyValueException("tourId", tourId, "Not found");
//		}
//
//	}
//
//	@Override
//	public void deleteTour(Long tourId) {
//		// TODO Auto-generated method stub
//		if (productRepo.findById(productId).get().getId().equals(productId)) {
//			productRepo.deleteById(productId);
//		} else
//			throw new InvalidConfigurationPropertyValueException("productId", productId, "Not found");
//	}
//
//	@Override
//	public Product getASingleProduct(Long productId) {
//		// TODO Auto-generated method stub
//		return productRepo.findById(productId)
//				.orElseThrow(() -> new InvalidConfigurationPropertyValueException("productId", productId, "Not found"));
//	}
//
//	@Override
//	public Page<Product> getAllProducts(Optional<Integer> page, Optional<String> sortBy) {
//		// TODO Auto-generated method stub
//		return productRepo.findAll(PageRequest.of(
//				page.orElse(0),
//				20,
//				Sort.Direction.ASC, sortBy.orElse("id")));
//	}
//
//}
