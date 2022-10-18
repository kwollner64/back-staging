package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Like;

public interface LikeRepository extends JpaRepository<Like, Integer>{
	
	Like findByUserIdAndPostId(int userId, int postId);
	long countByAuthorId(int authorId);
	long countByPostId(int postId);

}
