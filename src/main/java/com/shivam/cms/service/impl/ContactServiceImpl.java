package com.shivam.cms.service.impl;

import com.shivam.cms.dto.ContactDTO;
import com.shivam.cms.entities.Contact;
import com.shivam.cms.exceptions.ResourceNotFoundException;
import com.shivam.cms.repository.ContactRepository;
import com.shivam.cms.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContactDTO addContact(ContactDTO contact){
        Contact contactEntity = modelMapper.map(contact,Contact.class);
        Contact savedContact = contactRepository.save(contactEntity);
        contact = modelMapper.map(savedContact,ContactDTO.class);
        return contact;
    }

    @Override
    public ContactDTO updateContact(ContactDTO contact, Integer id){
        Contact entity = contactRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Contact having id "+id+" was not found","Failed"));
        entity.setFirstName(contact.getFirstName());
        entity.setLastName(contact.getLastName());
        entity.setEmail(contact.getEmail());
        entity.setPhone(contact.getPhone());
        contactRepository.save(entity);
        return modelMapper.map(entity,ContactDTO.class);
    }

    @Override
    public ContactDTO deleteContact(Integer id){
        Contact entity = contactRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Contact having id "+id+" was not found","Failed"));
        contactRepository.delete(entity);
        return modelMapper.map(entity,ContactDTO.class);
    }
    @Override
    public ContactDTO findContact(Integer id){
        Contact entity = contactRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Contact having id "+id+" was not found","Failed"));
        System.out.println(contactRepository.findByEmail("marythesmith@gmail.com"));
        return modelMapper.map(entity,ContactDTO.class);
    }

    @Override
    public List<ContactDTO> findContactByEmail(String email) {
        List<Contact> contacts = contactRepository.findByEmail(email.toUpperCase());
        if(contacts.isEmpty())
            throw new ResourceNotFoundException("No contacts found for email: "+email,"Completed");
        return contacts.stream().map(contact -> {
            return modelMapper.map(contact,ContactDTO.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<ContactDTO> findContactByFirstName(String firstName) {
        List<Contact> contacts = contactRepository.findByFirstName(firstName.toUpperCase());
        if(contacts.isEmpty())
            throw new ResourceNotFoundException("No contacts found for first name: "+firstName,"Completed");
        return contacts.stream().map(contact -> {
            return modelMapper.map(contact,ContactDTO.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<ContactDTO> findContactByLastName(String lastName) {
        List<Contact> contacts = contactRepository.findByLastName(lastName.toUpperCase());
        if(contacts.isEmpty())
            throw new ResourceNotFoundException("No contacts found for last name: "+lastName,"Completed");
        return contacts.stream().map(contact -> {
            return modelMapper.map(contact,ContactDTO.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<ContactDTO> findAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream().map(contact -> {
            return modelMapper.map(contact,ContactDTO.class);
        }).collect(Collectors.toList());
    }
}
