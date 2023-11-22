package com.upchardwar.app.entity.payload;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityResponse {
    private Long id;
	
	private String spName;
	
	private String spDescription;
}
