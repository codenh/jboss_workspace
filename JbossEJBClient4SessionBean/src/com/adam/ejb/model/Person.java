package com.adam.ejb.model;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    
    public void setName(String name) {
		this.name = name;
	}
    
    public String getName() {
		return name;
	}
    
    public String toString() {
    	return name;
    }
}
