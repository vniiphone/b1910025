package com.travel.b1910025.security.services;

import org.springframework.stereotype.Component;

import com.travel.b1910025.payload.response.DashbroadResponse;

@Component
public interface AdminService {
	DashbroadResponse getDashBroad();
}
