package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Group;
import com.revature.models.GroupPost;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.repositories.GroupRepository;
import com.revature.repositories.PostRepository;

@Service
public class GroupService {
	
	@Autowired
	private GroupRepository groupRepository;
	
	public List<Group> getAllGroups(){
		List<Group> groups = groupRepository.findAll();
		System.out.println(groups);
		return groups;
	}
	
	public Optional<Group> getGroup(int groupID) {
		return groupRepository.findById(groupID);
	}

	public void createGroup(Group newGroup) {
		groupRepository.save(newGroup);
	}

	public void addMember(User user, Integer groupID) {
		Group group = groupRepository.findByid(groupID);
		group.groupMembers.add(user);
		groupRepository.saveAndFlush(group);
	}
	
	
}