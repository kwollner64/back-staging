package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	public List<Post> findAllByOrderByIdDesc();
}
