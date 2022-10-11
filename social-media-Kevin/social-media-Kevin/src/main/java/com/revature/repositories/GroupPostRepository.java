package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Post;

public interface GroupPostRepository extends JpaRepository<Post, Integer> {
	
}