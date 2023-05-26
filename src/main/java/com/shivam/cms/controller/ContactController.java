package com.shivam.cms.controller;

import com.shivam.cms.dto.ContactDTO;
import com.shivam.cms.service.ContactService;
import com.shivam.cms.utils.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cms")
public class ContactController {

    @Autowired
    ContactService contactService;

    @Value("${algorithm}")
    private String property;
    @GetMapping("/contacts")
    public ResponseEntity<?> getContacts(){
        List<ContactDTO> contacts = contactService.findAllContacts();
        if(contacts.size()==0)
            return new ResponseEntity<>(new Response("No contacts are present in system","Success"),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(contacts,HttpStatus.OK);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<ContactDTO> getContact(@PathVariable Integer id){
        ContactDTO contact = null;
        contact = contactService.findContact(id);
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }

    @GetMapping("/contacts/email")
    public ResponseEntity<List<ContactDTO>> getContactByEmail(@RequestParam String email){
        List<ContactDTO> contacts = contactService.findContactByEmail(email);
        return new ResponseEntity<>(contacts,HttpStatus.OK);
    }
    @GetMapping("/contacts/firstName")
    public ResponseEntity<List<ContactDTO>> getContactByFirstName(@RequestParam String firstName){
        List<ContactDTO> contacts = contactService.findContactByFirstName(firstName);
        return new ResponseEntity<>(contacts,HttpStatus.OK);
    }

    @GetMapping("/contacts/lastName")
    public ResponseEntity<List<ContactDTO>> getContactByLastName(@RequestParam String lastName){
        List<ContactDTO> contacts = contactService.findContactByLastName(lastName);
        return new ResponseEntity<>(contacts,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ContactDTO> addContact(@Valid @RequestBody ContactDTO contactDto){
        ContactDTO contact = contactService.addContact(contactDto);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Integer id, @RequestBody ContactDTO contactDTO){
        ContactDTO contact = contactService.updateContact(contactDTO,id);
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Integer id){
        ContactDTO contact = contactService.deleteContact(id);
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }
}
