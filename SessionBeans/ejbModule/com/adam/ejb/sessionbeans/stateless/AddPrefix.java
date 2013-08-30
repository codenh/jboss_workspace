package com.adam.ejb.sessionbeans.stateless;

import com.adam.ejb.model.Person;

public interface AddPrefix {
    Person addTitle(Person person, String title);
}
