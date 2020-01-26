package com.accolite.au.hibernate.assignment;

import com.accolite.au.hibernate.assignment.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfficeHibernateDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.mapping.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            OutsideCourse outsideCourse1 = new OutsideCourse("outsidecourse1","presenter1",1000);
            OutsideCourse outsideCourse2 = new OutsideCourse("outsidecourse2","presenter2",1200);

            List<String> notes = new ArrayList<>();
            notes.add("note1");
            notes.add("note2");
            SpecialCourse specialCourse1 = new SpecialCourse("splcourse1",notes);

            notes = new ArrayList<>();
            notes.add("note3");
            notes.add("node4");
            SpecialCourse specialCourse2 = new SpecialCourse("splcourse1",notes);

            List<Employee> employees = new ArrayList<>();
            Date date1 = new Date(2020,1,25);
            Date date2 = new Date(2020,1,26);

            Employee employee1 = new Employee("name1",date1,1500);
            Employee employee2 = new Employee("name2",date2,1700);
            List<Course> courses = new ArrayList<>();
            courses.add(outsideCourse1);
            courses.add(specialCourse1);
            employee1.setCourses(courses);

            courses = new ArrayList<>();
            courses.add(outsideCourse2);
            courses.add(specialCourse2);
            employee2.setCourses(courses);

            outsideCourse1.addEmployee(employee1);
            specialCourse1.addEmployee(employee1);
            outsideCourse2.addEmployee(employee2);
            specialCourse2.addEmployee(employee2);

            employees.add(employee1);
            employees.add(employee2);

            Office office = new Office(employees,"office1");
            session.beginTransaction();
            session.save(outsideCourse1);
            session.save(outsideCourse2);
            session.save(specialCourse1);
            session.save(specialCourse2);
            session.save(employee1);
            session.save(employee2);
            session.save(office);
            session.getTransaction().commit();
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }
}
