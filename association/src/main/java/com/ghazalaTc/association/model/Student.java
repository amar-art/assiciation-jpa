package com.ghazalaTc.association.model;


import javax.persistence.*;
import java.util.Set;


@Entity
 public class Student {

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
  // @Column(name = "Nom student")
    private String name;
    private int  age;

    @OneToMany(mappedBy = "student" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private   Set<Contact> contacts;

    public Student(Long studentId, String name, int age) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
