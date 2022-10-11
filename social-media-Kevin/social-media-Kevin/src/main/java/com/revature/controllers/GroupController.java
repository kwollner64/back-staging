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

import com.revature.annotations.Authorized;
import com.revature.models.Group;
import com.revature.models.Post;
import com.revature.services.GroupService;

@RestController
@RequestMapping("/groups/")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	//@Authorized
	@GetMapping("")
	public ResponseEntity<List<Group>> getAllGroups(){
		return ResponseEntity.ok(this.groupService.getAllGroups());
	}
	
	//@Authorized
	@GetMapping("/{groupID}")
	public ResponseEntity<Optional<Group>> getGroup(@PathVariable("groupID") Integer groupID){
		return ResponseEntity.ok(this.groupService.getGroup(groupID));
	}
	
	//@Authorized
	@PutMapping("/{groupID}")
	public void upsertPost(@PathVariable("groupID") Integer groupID, @RequestBody Post post){
		this.groupService.upsert(groupID, post);
	}
	
	//@Authorized
	@PostMapping("")
	public void createGroup(@RequestBody Group newGroup) {
		this.groupService.createGroup(newGroup);
	}
	
	
}
