package com.altynbekova.top.unidirectionalassociation.service;

import com.altynbekova.top.unidirectionalassociation.dao.ProjectRepository;
import com.altynbekova.top.unidirectionalassociation.entity.Person;
import com.altynbekova.top.unidirectionalassociation.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    @Transactional
    public Project save(Project project){
        return projectRepository.save(project);
    }

    @Transactional
    public void delete(long id) {
        projectRepository.deleteById(id);
    }
}
