package com.silence.service;

import com.silence.enetity.Person;

/**
 * @author silence
 * @since 2025/3/21 16:40
 **/
public interface ElasticsearchService {

    Person createPerson(Person person);

    Person getPersonById(String id);

    Iterable<Person> searchByName(String name);

    Person updatePerson(String id, Person person);

    void deletePerson(String id);
}
