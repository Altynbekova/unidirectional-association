package com.altynbekova.top.unidirectionalassociation.service;

import com.altynbekova.top.unidirectionalassociation.dao.PersonRepository;
import com.altynbekova.top.unidirectionalassociation.dao.ProjectRepository;
import com.altynbekova.top.unidirectionalassociation.entity.Person;
import com.altynbekova.top.unidirectionalassociation.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person findById(long id) {
        return personRepository.findById(id).
                orElseThrow(() -> new RuntimeException());
    }

    @Transactional
    public Person save(Project project, Person person) {
        /*if (person.getProjects() == null) {
            person.setProjects(new HashSet<>());
        }*/
        person.addProject(project);
        return personRepository.save(person);
    }

    @Transactional
    public Person addProject(long personId, Project project) {
        Person person = personRepository.findById(personId).orElseThrow();
        person.addProject(project);
        return personRepository.save(person);
    }

    @Transactional
    public Person removeProject(long personId, Project project){
        Person person = personRepository.findById(personId).orElseThrow();
        person.removeProject(project);
        Person saved = personRepository.save(person);
        return saved;

    }

    @Transactional
    public Person removeProject(long personId, long projectId) {
        Person person = personRepository.findById(personId).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();
        person.removeProject(project);
        Person saved = personRepository.save(person);
        return saved;
    }
}
