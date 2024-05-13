package com.altynbekova.top.unidirectionalassociation.service;

import com.altynbekova.top.unidirectionalassociation.dao.CompanyRepository;
import com.altynbekova.top.unidirectionalassociation.dao.PersonRepository;
import com.altynbekova.top.unidirectionalassociation.entity.Company;
import com.altynbekova.top.unidirectionalassociation.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(long id) {
        return companyRepository.findById(id).
                orElseThrow(() -> new RuntimeException());
    }

    @Transactional
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    public Company save(Company company, Person person) {
        company.addPerson(person);
        return companyRepository.save(company);
    }

    @Transactional
    public Company addPerson(long companyId, Person person) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException());

        company.addPerson(person);

        return companyRepository.save(company);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Company removePerson(long companyId, Person person){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException());

        company.removePerson(person);
        Company savedCompany = companyRepository.save(company);
        return savedCompany;
    }
}
