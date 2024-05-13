package com.altynbekova.top.unidirectionalassociation.controller;

import com.altynbekova.top.unidirectionalassociation.entity.Person;
import com.altynbekova.top.unidirectionalassociation.mapping.PersonModelAssembler;
import com.altynbekova.top.unidirectionalassociation.service.PersonService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PersonController {
    //    private PersonRepository repository;
    private PersonService personService;

    private PersonModelAssembler personModelAssembler;

    public PersonController(/*PersonRepository repository,*/
            PersonService service,
            PersonModelAssembler personModelAssembler) {
//        this.repository = repository;
        this.personService = service;
        this.personModelAssembler = personModelAssembler;
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<Person>> all() {
        List<EntityModel<Person>> people = personService.findAll().stream()
                .map(person ->
                        personModelAssembler.toModel(person)
                ).collect(Collectors.toList());

        return CollectionModel.of(people,
                linkTo(methodOn(PersonController.class).all()).withRel("users"));
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestBody Person person) {
        EntityModel<Person> entityModel = personModelAssembler.toModel(personService.save(person));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/users/{id}")
    public EntityModel<Person> find(@PathVariable long id) {
        Person person = personService.findById(id);

        return personModelAssembler.toModel(person);
    }

}
