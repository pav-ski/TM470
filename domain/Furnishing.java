package com.TM470.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@DiscriminatorValue("2")
@Table (name= "elements" )
@Proxy(lazy=false)
public class Furnishing extends Element {
	
	//Auto-Generated getters and setters
	//
	//
	

	//
	//
	//End of Auto-Generated getters and setters

}
