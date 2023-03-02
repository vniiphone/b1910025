package com.travel.b1910025.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.travel.b1910025.repository.CategoryRepository;
import com.travel.b1910025.repository.TourRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.travel.b1910025.payload.response.ResourceNotFoundException;
import com.travel.b1910025.models.Category;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private TourRepository tourRepository;

	// get all categorys
	@GetMapping("")
	public List<Category> getAlllCategory() {
		return categoryRepository.findAll();
	}

	// create categorys rest api
	@PostMapping("/create")
	public Category createCaterogy(@RequestBody Category category) {
		return categoryRepository.save(category);
	}

	// get category by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<Category> getEmployeeById(@PathVariable Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
		return ResponseEntity.ok(category);
	}

	// update category rest api

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category caterogyDetails) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
		category.setName(caterogyDetails.getName());

		Category updatedEmployee = categoryRepository.save(category);
		return ResponseEntity.ok(updatedEmployee);
	}

	// delete employee rest api
	@DeleteMapping(value = "/{id}", consumes = { "*/*" })
		public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
		boolean hasCategory = categoryRepository.existsById(id);
		boolean hasExits = tourRepository.existsByCategoryId(id);
		
		if(hasCategory) {
			System.out.print("Id not exits");
			if(!hasExits) {
				categoryRepository.deleteById(id);
				return new ResponseEntity<>("Delete", HttpStatus.BAD_REQUEST );
			}
			else {
				System.out.println("Tồn tại Loại tour trong danh sách tour");
				return new ResponseEntity<>("Vẫn còn tồn tại trong tour du lịch", HttpStatus.BAD_REQUEST);
			}
		}
		else if(!hasCategory){
			System.out.println("Id not exits");
			return new ResponseEntity<>("ID "+id+" not found", HttpStatus.NOT_FOUND);
		}else {
			System.out.println("Lỗi 500 INTERNAL SERVER ERRROR");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
