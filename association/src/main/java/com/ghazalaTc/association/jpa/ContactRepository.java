package com.ghazalaTc.association.jpa;

import com.ghazalaTc.association.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ContactRepository extends JpaRepository<Contact,Long> {
}
