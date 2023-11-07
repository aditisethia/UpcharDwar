package com.upchardwar.app.entity.pharma;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

	@Id
	private Long id;
	private String ordername;
	private Float orderAmmount;
	private Integer Quantity;
	
}
