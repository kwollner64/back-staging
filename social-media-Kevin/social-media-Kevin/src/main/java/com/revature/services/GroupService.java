package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Group;
import com.revature.models.Post;
import com.revature.repositories.GroupPostRepository;
import com.revature.repositories.GroupRepository;
import com.revature.repositories.PostRepository;

@Service
public class GroupService {
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private GroupPostRepository groupPostRepository;
	
	public List<Group> getAllGroups(){
		List<Group> groups = this.groupRepository.findAll();
		System.out.println(groups);
		return groups;
	}
	
	public Optional<Group> getGroup(int groupID) {
		return this.groupRepository.findById(groupID);
	}

	public void createGroup(Group newGroup) {
		this.groupRepository.save(newGroup);
	}

	public void upsert(Integer groupID, Post post) {
		//groupPostRepository.sav
		System.out.println(groupID);
		Group group = groupRepository.findByid(groupID);
		System.out.println(group);
		group.groupPosts.add(post);
		groupRepository.save(group);
		
//		try {
//			}
//		catch(NullPointerException e) {
//			e.printStackTrace();
//		}
	}
}
