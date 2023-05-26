package com.shivam.cms.repository;

import com.shivam.cms.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Integer> {

    @Query("from Contact c where upper(c.firstName) = :firstName")
    public List<Contact> findByFirstName(String firstName);
    @Query("from Contact c where upper(c.lastName) = :lastName")
    public List<Contact> findByLastName(String lastName);
    @Query("from Contact c where upper(c.email) = :email")
    public List<Contact> findByEmail(String email);
}
