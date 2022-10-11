package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Profile;
import com.revature.models.User;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	
	//its a list but the purpose of this function is to just return 1. I tried to not make it a list but get errors.
	List<Profile> findByUser(User user);
}
