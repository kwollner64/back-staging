package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Post;
import com.revature.models.User;
import com.revature.services.BookmarkService;
import com.revature.models.Bookmark;

@RestController
@RequestMapping("/bookmark")
@CrossOrigin(origins= "http://mrbucket456.s3.amazonaws.com", allowCredentials="true")
public class BookmarkController {
	
	private final BookmarkService bookmarkService;
	
	
	
	public BookmarkController(BookmarkService bookmarkService) {
		this.bookmarkService = bookmarkService;
	}



	@PostMapping("")
	 public ResponseEntity<Bookmark> bookmarkThisPost(@RequestBody Bookmark bookmark){
		
		System.out.println(bookmark);
		
		return ResponseEntity.ok(this.bookmarkService.bookmarked(bookmark));
		}
	
	@PutMapping("")
	public ResponseEntity<List<Post>> getAllBookmarks(@RequestBody User user){
		
		
		return ResponseEntity.ok(this.bookmarkService.findAllBookmarks(user));
		
	}
	
	@PutMapping("/delete")
	public void UnbookmarkThisPost(@RequestBody Bookmark bookmark){
		
		System.out.println(bookmark);
		
		this.bookmarkService.unBookmarked(bookmark);
		
	}

}
