package com.accolite.au.hibernate.assignment.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Course")
@Inheritance(strategy = InheritanceType.JOINED)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CR_ID")
    protected int courseId;

    protected String courseName;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @ManyToMany(mappedBy = "courses")
    protected List<Employee> employees;
}
