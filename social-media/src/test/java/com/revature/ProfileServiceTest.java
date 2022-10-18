package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.Profile;
import com.revature.models.User;
import com.revature.repositories.ProfileRepository;
import com.revature.services.ProfileService;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

	@InjectMocks
	private ProfileService profileService;
	
	@Mock
	private Profile profile;
	
	@Mock
	private Profile expectedProfile;
	
	@Mock
	private User user;
	
	@Mock
	private ProfileRepository profileRepository;
	
	@Mock
	private List<Profile> profiles;
	
	@BeforeEach
	public void beforeEach() {
		user = new User(1,"testuser@gmail.com","password","test","user");
		profile = new Profile(-1,"text from profile","img_url",true,"01-01-1974",true,true,user);
		expectedProfile = new Profile(1,"text from expectedProfile","img_url",true,"01-01-1974",true,true,user);
		profiles = new ArrayList<Profile>();
	}
	
	@Test
	public void testUpdateProfile() {
		Mockito.when(profileRepository.save(profile)).thenReturn(expectedProfile);
		profile = profileService.updateProfile(profile);
		assertEquals(expectedProfile.getText(), profile.getText());
	}
	
	@Test
	public void testCreateProfile() {
		Mockito.when(profileRepository.saveAndFlush(profile)).thenReturn(expectedProfile);
		profile = profileService.createProfile(profile);
		assertEquals(expectedProfile.getText(), profile.getText());
	}
	
	@Test
	public void testGetAllProfile() {
		List<Profile> expectedProfiles = new ArrayList<Profile>();
		expectedProfiles.add(profile);
		expectedProfiles.add(expectedProfile);
		Mockito.when(profileRepository.findAll()).thenReturn(expectedProfiles);
		profiles = profileService.getAllProfile();
		assertEquals(expectedProfiles.size(),profiles.size());
	}
	
	@Test
	public void testGetOneProfile() {
		profiles.add(expectedProfile);
		Mockito.when(profileRepository.findByUser(user)).thenReturn(profiles);
		profile = profileService.getProfile(user);
		assertEquals(profiles.get(0).getId(), profile.getId());
	}
	
	//This test is to check for when a profile is not found
	@Test
	public void testGetOneProfileNotFound() {
		profile.setId(-10);
		Mockito.when(profileRepository.findByUser(user)).thenReturn(profiles);
		profile = profileService.getProfile(user);
		assertEquals(-1, profile.getId());
	}
}
