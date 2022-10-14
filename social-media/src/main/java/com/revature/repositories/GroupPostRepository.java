package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.GroupPost;

public interface GroupPostRepository extends JpaRepository<GroupPost, Integer> {
	
	@Query(value="SELECT * FROM group_posts WHERE group_posts.groupid = ?1", nativeQuery = true)
	Optional<List<GroupPost>> findAllByid(Integer groupID);
	
}
