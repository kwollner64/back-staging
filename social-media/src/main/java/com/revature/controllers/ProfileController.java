package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.Profile;
import com.revature.models.User;
import com.revature.services.ProfileService;

@RestController
@RequestMapping("/profiles")
@CrossOrigin(origins = {"http://mrbucket456.s3.amazonaws.com","http://localhost:4200"}, allowCredentials = "true")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	
	@GetMapping
	public ResponseEntity<List<Profile>>getAllProfile(){
		
		return ResponseEntity.ok(profileService.getAllProfile());
	}
	
	@PostMapping("/page")
	public ResponseEntity<Profile>getOneProfile(@RequestBody User user){
		
		return ResponseEntity.ok(profileService.getProfile(user));
	}
	
	@PostMapping("/{pid}")
	public ResponseEntity<Profile>getOneProfile(@PathVariable("pid")int id){
		
		User user = new User(id,"","","","");
		return ResponseEntity.ok(profileService.getProfile(user));
	}
	
	@PostMapping
	public ResponseEntity<Profile>createProfile(@RequestBody Profile profile){
		
		return ResponseEntity.ok(profileService.createProfile(profile));
	}
	
	@PutMapping
	public ResponseEntity<Profile>updateProfile(@RequestBody Profile profile){
		
		return ResponseEntity.ok(profileService.updateProfile(profile));
	}
	
}
