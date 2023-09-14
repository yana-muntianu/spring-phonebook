package com.phonebook.app.controller.rest;

import com.phonebook.app.entity.Contact;
import com.phonebook.app.exception.ResourceNotFoundException;
import com.phonebook.app.service.ContactService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("rest")
@RequestMapping("/api/v1")
public class ContactController {

    @PostConstruct
    private void loadData() {
        Contact contact1 = new Contact("Test", "User1", "6244290004");
        Contact contact2 = new Contact("Test", "User2", "0274720812");
        Contact contact3 = new Contact("Test", "User3", "0294726623");
        Contact contact4 = new Contact("Test", "User4", "9987651300");
        Contact contact5 = new Contact("Test", "User5", "0098765154");

        service.saveContact(contact1);
        service.saveContact(contact2);
        service.saveContact(contact3);
        service.saveContact(contact4);
        service.saveContact(contact5);
    }

    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/contacts")
    public List<Contact> listContacts() {
        LOG.debug("inside show customer-rest handler method");
        return service.getContacts();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/contacts")
    public Contact createContact(@RequestBody Contact contact) {
        return service.saveContact(contact);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/contacts/{lastName}")
    public Contact getContact(@PathVariable String lastName) throws ResourceNotFoundException {
        return service.getContact(lastName);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/contacts/{lastName}")
    public Contact updateContact(@PathVariable String lastName, @RequestBody Contact updatedContact) throws ResourceNotFoundException {
        Contact contact = service.getContact(lastName);

        contact.setFirstName(updatedContact.getFirstName());
        contact.setLastName(updatedContact.getLastName());
        contact.setPhone(updatedContact.getPhone());

        return contact;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/contacts/{lastName}")
    public void deleteContact(@PathVariable String lastName) throws ResourceNotFoundException {
        service.deleteContact(lastName);
    }
}
