package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "group_posts")
public class GroupPost {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private int groupID;
	private String text;
	private String imageUrl;
	//@ManyToOne
	//@JoinColumn(name="group_id")
	//private Group group;
	
	//@OneToMany(cascade = CascadeType.ALL)
	//private List<Post> comments;
	
	@ManyToOne
	@JoinColumn(name="author_id")
	private User author;
}
