package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
	@Query(value = "SELECT * FROM GROUPS WHERE GROUPS.GROUPID = ?1", nativeQuery = true)
	Group findByid(int groupID);
}
