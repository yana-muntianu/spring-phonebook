package com.phonebook.app.service;

import com.phonebook.app.controller.mvc.ContactController;
import com.phonebook.app.entity.Contact;
import com.phonebook.app.exception.ResourceNotFoundException;
import com.phonebook.app.repository.ContactInMemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactInMemoryRepository repository;

    @Override
    public List<Contact> getContacts() {
        return repository.findAll();
    }

    @Override
    public Contact saveContact(Contact contact) {
        if (contact != null)
            return repository.save(contact);
        return null;
    }

    @Override
    public Contact getContact(String lastName) throws ResourceNotFoundException {

        Optional<Contact> result = Optional.ofNullable(repository.findByName(lastName));
        Contact contact = null;

        if (result.isPresent()) {
            contact = result.get();
        } else {
            throw new ResourceNotFoundException("Name is not found");
        }
        return contact;
    }


    @Override
    public void deleteContact(String lastName) throws ResourceNotFoundException {
        if (lastName == null) {
            LOG.error("Something is wrong: Contact name is null");
        } else {
            if (repository.findByName(lastName) != null) {
                repository.delete(lastName);
            } else {
                throw new ResourceNotFoundException("Name is not found");
            }
        }
    }
}
