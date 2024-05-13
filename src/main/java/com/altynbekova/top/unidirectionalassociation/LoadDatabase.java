package com.altynbekova.top.unidirectionalassociation;

import com.altynbekova.top.unidirectionalassociation.dao.CompanyRepository;
import com.altynbekova.top.unidirectionalassociation.dao.PersonRepository;
import com.altynbekova.top.unidirectionalassociation.entity.Company;
import com.altynbekova.top.unidirectionalassociation.entity.Person;
import com.altynbekova.top.unidirectionalassociation.entity.Project;
import com.altynbekova.top.unidirectionalassociation.service.CompanyService;
import com.altynbekova.top.unidirectionalassociation.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class LoadDatabase {
    private static final Logger LOG = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PersonService personService, CompanyService companyService){
        return args -> {
            Person person = new Person("User1",
                    34, "City1 Street1 Building1");
            Company company = new Company("Company1", new HashSet<>());

            Project project = new Project("Project1");
            project.setPeople(new HashSet<>());
            Person person2 = new Person("User3", 22, "C3 Str3 Bld3");

            LOG.info("Preloading "+ personService.save(new Person("User2",
                    87, "City2 Street2 Building2")));
            LOG.info("Preloading "+ companyService.save(company, person));
            LOG.info("Preloading "+ personService.save(project, person2));
        };
    }
}
