package com.revature.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Like;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.services.LikeService;

@RestController
@RequestMapping("/like")
@CrossOrigin(origins= "http://mrbucket456.s3.amazonaws.com", allowCredentials="true")
public class LikeController {
	
	private final LikeService likeService;
	
	
	
	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}



	@PostMapping("")
	 public ResponseEntity<Like> likeThisPost(@RequestBody Like like){
		
		System.out.println(like);
		
		return ResponseEntity.ok(this.likeService.liked(like));
		}
	
	@PutMapping("")
	public ResponseEntity<List<Post>> getAllLikedPosts(@RequestBody User user){
		
		
		return ResponseEntity.ok(this.likeService.findAllLikedPosts(user));
		
	}
	
	@PutMapping("/delete")
	public void UnlikeThisPost(@RequestBody Like like){
		
		System.out.println(like);
		
		this.likeService.unLiked(like);
		
	}
	
	@PutMapping("/count")
	public ResponseEntity<Long> countingTotalLikes(@RequestBody Like like) {
		
		return ResponseEntity.ok(this.likeService.countHowManyLikes(like));
		
	}


}
