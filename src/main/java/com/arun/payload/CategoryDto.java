package com.arun.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoryDto {
	
	private int id;
	@NotEmpty
	private String categoryTitle;
	@NotEmpty
	private String categoryContent;
	private String addedDate;
	

}
