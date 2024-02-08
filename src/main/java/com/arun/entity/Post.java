package com.arun.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter

public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pId;
	private String title;
	private String content;
	private String image;
	private Date date;
	@ManyToOne
	private User user;
	@ManyToOne
	private Category category;
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> comment= new HashSet<>();

}
