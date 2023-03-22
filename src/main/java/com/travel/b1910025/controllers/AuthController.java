package com.travel.b1910025.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travel.b1910025.models.User;
import com.travel.b1910025.payload.request.LoginRequest;
import com.travel.b1910025.payload.request.SignupRequest;
import com.travel.b1910025.payload.response.JwtResponse;
import com.travel.b1910025.payload.response.MessageResponse;
import com.travel.b1910025.repository.UserRepository;
import com.travel.b1910025.security.jwt.JwtUtils;
import com.travel.b1910025.security.services.UserDetailsImpl;

@RestController
@CrossOrigin(origins = {"http://localhost:3033","http://localhost:3000"})
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  // @Autowired
  // RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  // @CrossOrigin(origins = "http://127.0.0.1:5173")
  @CrossOrigin(origins = "*", maxAge = 3600)
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    return ResponseEntity.ok(new JwtResponse(
        jwt,
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        userDetails.getRole().toString()));
  }

  /**
   * @param signUpRequest
   * @return
   */
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }
@PostMapping("/signupuser")
	public ResponseEntity registerNewUser(@RequestParam("username") String username,
			@RequestParam("email") String email, @RequestParam("password") String password) {
	
		 if (userRepository.existsByUsername(username)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		} else if (userRepository.existsByEmail(email)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		} else {
			try {
				// Create new user's account
				User user = new User(username, email, encoder.encode(password), "user");
				userRepository.save(user);
				//return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
				return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
    
    
    // Create new user's account
    User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()), "user");

    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  
  
  @RequestMapping(value="/logout", method=RequestMethod.GET)  
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
      if (auth != null){      
         new SecurityContextLogoutHandler().logout(request, response, auth);  
      }  
       return "redirect:/";  
   }  
  
  
}
