package com.travel.b1910025.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.b1910025.payload.response.DashbroadResponse;
import com.travel.b1910025.security.services.AdminService;

@CrossOrigin(origins = {"http://localhost:3001"," http://localhost:3000"})
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	AdminService adminService;

	/* get dash broad data */
	@GetMapping("/dashboard")
	public DashbroadResponse dashBroad() {
		return adminService.getDashBroad();
	}
}
