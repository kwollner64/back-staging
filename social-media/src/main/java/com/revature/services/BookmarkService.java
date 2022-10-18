package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.models.Bookmark;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.repositories.BookmarkRepository;
import com.revature.repositories.PostRepository;

@Service
public class BookmarkService {
	
	private BookmarkRepository bookmarkReporsitory;
	private PostRepository postRepository;

	
	public BookmarkService(BookmarkRepository bookmarkReporsitory, PostRepository postRepository) {
		this.bookmarkReporsitory = bookmarkReporsitory;
		this.postRepository = postRepository;
	}

	public Bookmark bookmarked(Bookmark bookmark) {
		
		return this.bookmarkReporsitory.save(bookmark);
	}
	
	public List<Post> findAllBookmarks(User user){
		
		List<Bookmark> allBookmarks = this.bookmarkReporsitory.findAll();
		
		List<Bookmark> curreUserBookmarks = new ArrayList<>();
		
		for(int i=0; i<allBookmarks.size(); i++) {
			
			if(allBookmarks.get(i).getUserId() == user.getId())
			{
				curreUserBookmarks.add(allBookmarks.get(i));
			}
			
		}
		
		return this.findAllPostsBookmarked(curreUserBookmarks);
		
	}
	
	public List<Post> findAllPostsBookmarked(List<Bookmark> currentBookmarks){
		
		List<Post> allPosts = this.postRepository.findAll();
		
		List<Post> allBookmarkedPosts = new ArrayList<>();
		
		for(int i=0; i<currentBookmarks.size(); i++) {
			
			for(int j=0; j<allPosts.size();j++) {
			
				if(allPosts.get(j).getId() == currentBookmarks.get(i).getPostId()) {
				
					allBookmarkedPosts.add(allPosts.get(j));
				
				}
			}
		}
		
		return allBookmarkedPosts;
	}
	
	public void unBookmarked(Bookmark bookmark) {
		
		Bookmark deletingThisBookmark = this.bookmarkReporsitory.findByUserIdAndPostId(bookmark.getUserId(), bookmark.getPostId());
		
		this.bookmarkReporsitory.delete(deletingThisBookmark);
		
	}
	
}
