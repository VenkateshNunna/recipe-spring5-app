package com.example.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(exclude= {"recipe"})
@ToString(exclude= {"recipe"})
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private Float amount;
	
	@ManyToOne
	private Recipe recipe;
	
	@OneToOne(fetch=FetchType.EAGER)
	private UnitOfMeasure uom;
	
}
