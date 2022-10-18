package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Group;
import com.revature.models.GroupPost;
import com.revature.repositories.GroupPostRepository;

@Service
public class GroupPostService {
	
	@Autowired
	GroupPostRepository groupPostRepository;
	
	public Optional<List<GroupPost>> getAllPosts(Integer groupID){
		return groupPostRepository.findAllByid(groupID);
	}

	public GroupPost upsert(GroupPost post) {
		return this.groupPostRepository.save(post);
	}
	
	
	
}
