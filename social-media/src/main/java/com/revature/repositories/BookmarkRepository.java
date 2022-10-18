package com.revature.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
	
	Bookmark findByUserIdAndPostId(int userId, int postId);

}
