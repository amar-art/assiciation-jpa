package com.ghazalaTc.association.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    private String address;
    private String telephone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "studentId")
    @JsonIgnore
    private Student student;

    public Contact(Long contactId, String address, String telephone, String email) {
        this.contactId = contactId;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public Contact() {
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
