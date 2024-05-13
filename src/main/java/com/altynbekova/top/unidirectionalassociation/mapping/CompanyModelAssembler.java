package com.altynbekova.top.unidirectionalassociation.mapping;

import com.altynbekova.top.unidirectionalassociation.entity.Company;
import com.altynbekova.top.unidirectionalassociation.controller.CompanyController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CompanyModelAssembler implements RepresentationModelAssembler<Company, EntityModel<Company>> {
    @Override
    public EntityModel<Company> toModel(Company entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(CompanyController.class).find(entity.getId())).withSelfRel(),
                linkTo(methodOn(CompanyController.class).all()).withRel("companies"));
    }
}
