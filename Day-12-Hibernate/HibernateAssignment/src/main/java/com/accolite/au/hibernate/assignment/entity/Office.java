package com.accolite.au.hibernate.assignment.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Office")
public class Office {

    public Office() {}

    public Office(List<Employee> employees, String name) {
        this.employees = employees;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int officeId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employees;

    private String name;

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
