package com.accolite.au.hibernate.assignment.entity;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class OutsideCourse extends Course {

    private String presenter;
    private int cost;

    public OutsideCourse() {}

    public OutsideCourse(String courseName, String presenter, int cost) {
        this.presenter = presenter;
        this.courseName = courseName;
        this.cost = cost;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee e){
        this.employees.add(e);
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
