package com.sams.grapgql.dgs.repository;

import com.sams.grapgql.dgs.model.Person;
import com.sams.grapgql.dgs.model.PersonAddress;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    static List<Person> personList = new ArrayList<>();

    /**
     * Method to create dummy data for personList
     * @return
     */
    public static List<Person> getPersonList () {
        if(personList.isEmpty()) {
            for(int i=0;i<5;i++) {
                PersonAddress personAddress = new PersonAddress("streetName" + i, "houseNumber" + i, "city" + i, "country" + i);
                Person person = new Person(i,"personName" + i, personAddress);
                personList.add(person);
            }
        }
        return personList;
    }



}
