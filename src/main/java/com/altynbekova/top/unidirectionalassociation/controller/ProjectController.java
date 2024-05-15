package com.altynbekova.top.unidirectionalassociation.controller;

import com.altynbekova.top.unidirectionalassociation.entity.Project;
import com.altynbekova.top.unidirectionalassociation.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @PostMapping("/projects")
    public Project create(@RequestBody Project project) {
        return projectService.save(project);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        projectService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
