package com.accolite.AU.SpringAssignment.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "users")
@XmlAccessorType (XmlAccessType.FIELD)
public class Users 
{
    @XmlElement(name = "user")
    private List<User> users = null;

    public Users() {
        this.users = new ArrayList<>();
    }
 
    public List<User> getUsers() {
        return users;
    }
 
    public void setUsers(List<User> users) {
        this.users = users;
    }
}