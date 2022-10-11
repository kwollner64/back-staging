package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.Likes;
import com.revature.models.Post;
import com.revature.services.LikeService;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

	@Autowired
	LikeService likeService;
	
	
	    @GetMapping
	    public ResponseEntity<List<Likes>> getAllLikes() {
	    	return ResponseEntity.ok(likeService.getAllLikes());
	    }
}
