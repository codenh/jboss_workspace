package com.adam.ejb.sessionbeans.stateless;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.adam.ejb.model.Person;

@Stateless
@Remote(AddPrefix.class)
public class AddPrefixBean implements AddPrefix {
	private static int count = 1;
	private int id;
	
	public AddPrefixBean() {
		id = count++;
	}
	
	@Override
	public Person addTitle(Person person, String title) {
		person.setName(title + " " + person.getName() + " Bean(" + id + ")");
		return person;
	}
}
