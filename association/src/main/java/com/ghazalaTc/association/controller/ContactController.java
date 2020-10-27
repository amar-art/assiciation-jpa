package com.ghazalaTc.association.controller;

import com.ghazalaTc.association.exception.NotFoundException;
import com.ghazalaTc.association.jpa.ContactRepository;
import com.ghazalaTc.association.jpa.StudentRepository;
import com.ghazalaTc.association.model.Contact;
import com.ghazalaTc.association.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class ContactController  {

    ContactRepository contactRepository;
    StudentRepository studentRepository;

    public ContactController(ContactRepository contactRepository, StudentRepository studentRepository) {
        this.contactRepository = contactRepository;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/contact/{studentId}")
    public Contact addContact(@RequestBody Contact contact, @PathVariable Long studentId){

        Student student;
        Optional<Student> optional=studentRepository.findById(studentId);

        if (optional.isPresent()) {
            student = optional.get();

            contact.setStudent(student);

           contactRepository.save(contact);

        }else {
            throw new NotFoundException("erreur de creation contact");
        }
        return contact;
    }
@GetMapping("/contact/byId/{id}")
    public Contact afficheContact(@PathVariable Long id){
        Contact contact=new Contact();
        Optional<Contact> optionalContact= contactRepository.findById(id);
        if (optionalContact.isPresent())
            contact=optionalContact.get();
        return contact;
}

    @GetMapping("/contact/listestudent/{id}")

    public Set<Contact> afficheListContact(@PathVariable Long id){


   Optional<Student> student=studentRepository.findById(id);
   if (student.isPresent())

        return student.get().getContacts() ;

       else {
           throw new NotFoundException("");
   }

    }
    @PutMapping("/contact/update/{studentId}/{contactId}")
    public Contact updateContact(@PathVariable Long studentId,
                                 @PathVariable Long contactId,
                              @RequestBody Contact contactUpdated) {

        Contact contact;
        Optional<Contact> contactFromBase = contactRepository.findById(contactId);
        if (contactFromBase.isPresent()) {


            contact = contactFromBase.get();
            contact.setAddress(contactUpdated.getAddress());
            contact.setEmail(contactUpdated.getEmail());
            contact.setTelephone(contactUpdated.getTelephone());

            return contactRepository.save(contact);
        } else {
            throw new NotFoundException("erreur " + studentId);
        }


    }


    @DeleteMapping("contact/delete/{Id}")
    public  void deleteContact(@PathVariable Long Id ){


        Optional <Contact> contactOptional=contactRepository.findById(Id);

        if(contactOptional.isPresent()){
            contactRepository.delete(contactOptional.get());
            System.out.println("delete contact succsefully");

        }else
        {
            throw new NotFoundException("contact not fund");
        }


    }

}
