package com.revature.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.LoginRequest;
import com.revature.models.User;
import com.revature.services.ResetPassService;

@RestController
@RequestMapping("/auth/reset-password")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ResetPassController {

	private final ResetPassService resetPassService;
	
	public ResetPassController(ResetPassService resetPassService) {
		
		this.resetPassService=resetPassService;
		
	}
	
	@PutMapping("")
	public ResponseEntity<User> resetPassword(@RequestBody LoginRequest userWithNewPassword) {

		return ResponseEntity
				.ok(resetPassService.resetPassword(userWithNewPassword.getEmail(), userWithNewPassword.getPassword()));

	}

	@PostMapping("")
	public ResponseEntity<User> verifyEmail(@RequestBody String email) {

		Optional<User> emailFoundOrNot = resetPassService.findByEmail(email);

		if (emailFoundOrNot.isPresent()) {
			return ResponseEntity.ok(emailFoundOrNot.get());
		}

		else {
			return ResponseEntity.badRequest().build();
		}

	}

}
