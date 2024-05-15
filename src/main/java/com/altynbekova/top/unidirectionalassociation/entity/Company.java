package com.altynbekova.top.unidirectionalassociation.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Person> people = new HashSet<>();

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public Company(String name, Set<Person> people) {
        this.name = name;
        this.people = people;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPeople() {
        return people;
    }

    /*public void setPeople(Set<Person> people) {
        this.people = people;
    }*/

    public void addPerson(Person person) {
        people.add(person);
        person.setCompany(this);
    }

    public void removePerson(Person person){
        people.remove(person);
        person.setCompany(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", people=" + people +
                '}';
    }
}
