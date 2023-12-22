package com.upchardwar.app.entity.payload;

import com.upchardwar.app.entity.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabRequest {
	private Long id;

	private String labName;

	private String email;

	private String password;

	private Boolean isApproved = false;

	private String phone;

	private Location location;

}
