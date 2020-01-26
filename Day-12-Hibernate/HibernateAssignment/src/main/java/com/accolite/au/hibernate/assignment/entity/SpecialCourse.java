package com.accolite.au.hibernate.assignment.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SpecialCourse extends Course {

    @ElementCollection
    private List<String> notes;

    public SpecialCourse() { super(); }

    public SpecialCourse(String courseName, List<String> notes) {
        this.courseName = courseName;
        this.notes = notes;
        this.employees = new ArrayList<>();
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public void addEmployee(Employee e){
        this.employees.add(e);
    }
}
