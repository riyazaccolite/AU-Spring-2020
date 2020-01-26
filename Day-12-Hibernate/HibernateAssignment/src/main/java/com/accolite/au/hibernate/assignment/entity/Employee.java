package com.accolite.au.hibernate.assignment.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Employee {
    public Employee() {}

    public Employee(String name, Date doj, int salary) {
        this.name = name;
        this.dateOfJoining = doj;
        this.salary = salary;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID")
    private int employeeId;

    @Temporal(TemporalType.DATE)
    private Date dateOfJoining;

    private int salary;
    private String name;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Employee_Courset",
            joinColumns = { @JoinColumn(name = "EMP_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CR_ID") }
    )
    private List<Course> courses;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
