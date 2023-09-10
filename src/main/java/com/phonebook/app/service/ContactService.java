package com.phonebook.app.service;

import com.phonebook.app.entity.Contact;
import com.phonebook.app.exception.ResourceNotFoundException;

import java.util.List;

public interface ContactService {

    List<Contact> getContacts();

    Contact saveContact(Contact contact);

    Contact getContact(String lastName) throws ResourceNotFoundException;

    void deleteContact(String lastName) throws ResourceNotFoundException;


}
