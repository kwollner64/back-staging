package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Group;
import com.revature.models.GroupPost;
import com.revature.services.GroupPostService;

@RestController
@RequestMapping("/group-posts")
@CrossOrigin(origins = "http://mrbucket456.s3.amazonaws.com/index.html", allowCredentials = "true")
public class GroupPostController {
	
	@Autowired
	GroupPostService groupPostService;
	
	@GetMapping
	public ResponseEntity<Optional<List<GroupPost>>> getAllPosts(Integer groupID){
		return ResponseEntity.ok(this.groupPostService.getAllPosts(groupID));
	}

	// NEW POST
	//@Authorized
	@PostMapping("")
	public ResponseEntity<GroupPost> upsertPost(@RequestBody GroupPost post){
		return ResponseEntity.ok(this.groupPostService.upsert(post));
	}
	
	
}
