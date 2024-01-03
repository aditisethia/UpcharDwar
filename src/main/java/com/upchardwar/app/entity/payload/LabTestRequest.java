package com.upchardwar.app.entity.payload;

import com.upchardwar.app.entity.status.AppConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabTestRequest {
  
    private Long id;
	
	private String testName;
	
    private Integer ratings;
    
    private String description;
    
    private Boolean isDelete=false;
    
	private Long rates; 
	
	private Boolean availability;
	
	private Long labId;
}
