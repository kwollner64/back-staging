package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Profile;
import com.revature.models.User;
import com.revature.repositories.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public List<Profile> getAllProfile(){
		List<Profile> allProfiles = profileRepository.findAll();
		return allProfiles;
	}
	
	public Profile getProfile(User user) {
		List<Profile> profiles = profileRepository.findByUser(user);
		if(profiles.isEmpty()) return new Profile(-1,"","",user);
		return profiles.get(0);
		
	}
	
	public Profile createProfile(Profile profile) {
		profile = profileRepository.saveAndFlush(profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		profile = profileRepository.save(profile);
		return profile;
	}
	
	
	
}
