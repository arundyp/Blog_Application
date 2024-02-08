package com.arun.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String categoryTitle;
	private String categoryContent;
	private String addedDate;
	@OneToMany(mappedBy ="category",cascade = CascadeType.ALL )
	private List<Post>post= new ArrayList<>();

}
