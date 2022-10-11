package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Likes;
import com.revature.repositories.LikeRepository;

@Service
public class LikeService {

	@Autowired
	LikeRepository likeRepository;
	
	public List<Likes> getAllLikes(){
		return likeRepository.findAll();
	}
}
