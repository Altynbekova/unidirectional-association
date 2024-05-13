package com.altynbekova.top.unidirectionalassociation.controller;

import com.altynbekova.top.unidirectionalassociation.entity.Project;
import com.altynbekova.top.unidirectionalassociation.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/allprojects")
    public List<Project> findAll(){
        return projectService.findAll();
    }
}
