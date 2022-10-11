package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Likes;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Integer> {

}
