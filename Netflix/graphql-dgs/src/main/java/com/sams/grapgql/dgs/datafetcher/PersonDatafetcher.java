package com.sams.grapgql.dgs.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.sams.grapgql.dgs.model.Person;
import com.sams.grapgql.dgs.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@DgsComponent
@Slf4j
public class PersonDatafetcher {

    /**
     * Data fetcher to fetch all person objects in the list.
     * @return List
     */
    @DgsQuery
    public List<Person> allPersons() {
        return PersonRepository.getPersonList();
    }

    /**
     * Data fetcher to search and return person with specific Id.
     * @param searchPersonId
     * @return Person
     */
    @DgsQuery
    public Person searchPerson(@InputArgument Integer searchPersonId) {
        log.info("searchPerson {} ",searchPersonId);
        return PersonRepository.getPersonList()
                .stream()
                .filter(record -> record.getPersonId()==searchPersonId)
                .findFirst().get();
    }
}
