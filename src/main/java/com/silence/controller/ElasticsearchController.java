package com.silence.controller;

import com.silence.enetity.Person;
import com.silence.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author silence
 * @since 2025/3/21 16:26
 **/
@RestController
@RequestMapping("/api/es")
public class ElasticsearchController {

    @Autowired
    private ElasticsearchService elasticsearchService;

    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return elasticsearchService.createPerson(person);
    }

    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable String id) {
        return elasticsearchService.getPersonById(id);
    }

    @GetMapping("/person/search")
    public Iterable<Person> searchByName(@RequestParam String name) {
        return elasticsearchService.searchByName(name);
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable String id, @RequestBody Person person) {
        return elasticsearchService.updatePerson(id, person);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable String id) {
        elasticsearchService.deletePerson(id);
    }
}
