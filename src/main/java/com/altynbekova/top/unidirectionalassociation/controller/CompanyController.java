package com.altynbekova.top.unidirectionalassociation.controller;

import com.altynbekova.top.unidirectionalassociation.entity.Company;
import com.altynbekova.top.unidirectionalassociation.entity.Person;
import com.altynbekova.top.unidirectionalassociation.mapping.CompanyModelAssembler;
import com.altynbekova.top.unidirectionalassociation.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyModelAssembler companyModelAssembler;

    @GetMapping("/companies")
    public CollectionModel<EntityModel<Company>> all() {
        List<EntityModel<Company>> people = companyService.findAll().stream()
                .map(company ->
                        companyModelAssembler.toModel(company)
                ).collect(Collectors.toList());

        return CollectionModel.of(people,
                linkTo(methodOn(CompanyController.class).all()).withRel("companies"));
    }

    @GetMapping("/companies/{id}")
    public EntityModel<Company> find(@PathVariable long id) {
        return companyModelAssembler.toModel(companyService.findById(id));
    }

    @PostMapping("/companies")
    public EntityModel<Company> create(@RequestBody Company company) {
        return companyModelAssembler.toModel(companyService.save(company));
    }



    @PostMapping("/companies/{id}/user")
    public ResponseEntity<?> addPerson(
            @RequestBody Person person, @PathVariable long id){
        EntityModel<Company> entityModel = companyModelAssembler.toModel(
                companyService.addPerson(id, person));

        return ResponseEntity.created(
                entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/companies/{id}/user")
    public ResponseEntity<?> removePerson(
            @RequestBody Person person, @PathVariable long id){
        EntityModel<Company> entityModel = companyModelAssembler.toModel(
                companyService.removePerson(id, person));

        return ResponseEntity.created(
                        entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
