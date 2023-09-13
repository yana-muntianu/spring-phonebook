package com.phonebook.app.controller.mvc;

import com.phonebook.app.entity.Contact;
import com.phonebook.app.exception.ResourceNotFoundException;
import com.phonebook.app.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("mvc")
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService service;

    @GetMapping("/list")
    public String listContacts(Model model) {
        LOG.debug("inside show customer-rest handler method");
        List<Contact> contacts = service.getContacts();

        model.addAttribute("contacts", contacts);
        return "contacts/contacts-list";
    }

    @GetMapping("/add")
    public String showFormForAdd(Model model) {

        Contact contact = new Contact();

        model.addAttribute("contact", contact);

        return "contacts/add-contact";
    }

    @GetMapping("/update/{lastName}")
    public String showFormForUpdate(@PathVariable String lastName,
                                    Model model) throws ResourceNotFoundException {

        model.addAttribute("contact", service.getContact(lastName));


        return "contacts/update-contact";
    }

    @PostMapping("/save/{lastName}")
    public String updateContact(@PathVariable String lastName, @ModelAttribute("contact") Contact updatedContact) throws ResourceNotFoundException {
        Contact contact = service.getContact(lastName);

        contact.setPhone(updatedContact.getPhone());
        service.saveContact(contact);

        return "redirect:/api/v1/contacts/list";
    }

    @PostMapping("/save")
    public String addContact(@ModelAttribute("contact") Contact contact) {
        LOG.debug("inside show customer-rest handler method");

        service.saveContact(contact);

        return "redirect:/api/v1/contacts/list";

    }

    @GetMapping("/delete/{lastName}")
    public String delete(@PathVariable String lastName) throws ResourceNotFoundException {

        service.deleteContact(lastName);

        return "redirect:/api/v1/contacts/list";

    }

}
