package com.TM470.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("2")
@Table (name= "elements" )
public class Furnishing extends Element {
	
	//Auto-Generated getters and setters
	//
	//
	

	//
	//
	//End of Auto-Generated getters and setters

}
