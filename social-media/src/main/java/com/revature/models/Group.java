package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupID;
	private int adminID;
	private String groupName;
	private String groupDescription;

	//	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
//	public List<GroupPost> groupPosts;
	
	@ManyToMany
	@JoinTable(name = "group_members", inverseJoinColumns = { @JoinColumn(name = "id") }, joinColumns = { @JoinColumn(name = "group_id") })
	public List<User> groupMembers;
}
