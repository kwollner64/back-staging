package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Like;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.repositories.LikeRepository;
import com.revature.repositories.PostRepository;

@Service
public class LikeService {

	private LikeRepository likeRepository;
	private PostRepository postRepository;

	public LikeService(LikeRepository likeRepository, PostRepository postRepository) {
		this.likeRepository = likeRepository;
		this.postRepository = postRepository;
	}

	public Like liked(Like like) {

		return this.likeRepository.save(like);
	}

	public List<Post> findAllLikedPosts(User user) {

		List<Like> allLikedPosts = this.likeRepository.findAll();

		List<Like> curreUserLikedPosts = new ArrayList<>();

		for (int i = 0; i < allLikedPosts.size(); i++) {

			if (allLikedPosts.get(i).getUserId() == user.getId()) {
				curreUserLikedPosts.add(allLikedPosts.get(i));
			}

		}

		return this.findAllPostsLiked(curreUserLikedPosts);

	}

	public List<Post> findAllPostsLiked(List<Like> currentLikedPosts) {

		List<Post> allPosts = this.postRepository.findAll();

		List<Post> allLikedPosts = new ArrayList<>();

		for (int i = 0; i < currentLikedPosts.size(); i++) {

			for (int j = 0; j < allPosts.size(); j++) {

				if (allPosts.get(j).getId() == currentLikedPosts.get(i).getPostId()) {

					allLikedPosts.add(allPosts.get(j));

				}
			}
		}

		return allLikedPosts;
	}

	public void unLiked(Like like) {

		Like deletingThisLikedPost = this.likeRepository.findByUserIdAndPostId(like.getUserId(), like.getPostId());

		this.likeRepository.delete(deletingThisLikedPost);

	}
	
	public long countHowManyLikes(Like like) {
		
		return this.likeRepository.countByPostId(like.getPostId());
	}
}
