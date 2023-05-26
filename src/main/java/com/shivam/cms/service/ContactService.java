package com.shivam.cms.service;

import com.shivam.cms.dto.ContactDTO;
import com.shivam.cms.entities.Contact;
import com.shivam.cms.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ContactService {
    public ContactDTO addContact(ContactDTO contact);
    public ContactDTO updateContact(ContactDTO contact, Integer id);
    public ContactDTO deleteContact(Integer id);
    public ContactDTO findContact(Integer id);
    public List<ContactDTO> findContactByEmail(String email);
    public List<ContactDTO> findContactByFirstName(String firstName);
    public List<ContactDTO> findContactByLastName(String lastName);
    public List<ContactDTO> findAllContacts();
}
