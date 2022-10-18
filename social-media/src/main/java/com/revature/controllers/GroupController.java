package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.Group;
import com.revature.models.GroupPost;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.services.GroupService;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "https://mrbucket456.s3.amazonaws.com/index.html", allowCredentials = "true")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	//@Authorized
	@GetMapping("")
	public ResponseEntity<List<Group>> getAllGroups(){
		return ResponseEntity.ok(this.groupService.getAllGroups());
	}
	
	// GET A GROUP
	//@Authorized
	@GetMapping("/group/{groupID}")
	public ResponseEntity<Optional<Group>> getGroup(@PathVariable("groupID") Integer groupID){
		return ResponseEntity.ok(this.groupService.getGroup(groupID));
	}
	
	// CREATE GROUP
	// @Authorized
	@PostMapping("")
	public void createGroup(@RequestBody Group newGroup) {
		this.groupService.createGroup(newGroup);
	}
	
	// ADD GROUP MEMBER
	//@Authorized
	@PostMapping("/member/{groupID}")
	public void addMember(@PathVariable("groupID") Integer groupID, @RequestBody User user) {
		groupService.addMember(user, groupID);
	}
	
	// DELETE GROUP MEMBER
	@DeleteMapping("")
	public void deleteMember(int userID, int groupID) {
		this.groupService.deleteMember(userID, groupID);
	}
	
	// EDIT GROUP
	@PutMapping("")
	public ResponseEntity<Group> editGroup(@RequestBody Group updatedGroup){
		return ResponseEntity.ok(groupService.editGroup(updatedGroup));
	}
	
	
}
