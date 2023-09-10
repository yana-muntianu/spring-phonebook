package com.phonebook.app.repository;

import com.phonebook.app.entity.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ContactInMemoryRepository {

    private final Map<String, Contact> contacts = new ConcurrentHashMap<>();

    public Contact save(Contact contact) {
        if (contact.getId() == null) {
            contact.setId(UUID.randomUUID().toString());
        }
        contacts.put(contact.getId(), contact);

        return contact;
    }

    public List<Contact> findAll() {
        return contacts.values().stream().toList();
    }

    public Contact findById(String id) {
        return contacts.get(id);
    }

    public Contact findByName(String name) {
        Contact result = null;
        List<Contact> results = contacts.values().stream().toList();
        for (Contact contact : results) {
            if (contact.getLastName().equals(name)) {
                result = contact;
                break;
            }
        }
        return result;
    }

    public Contact updateContact(String name, String phone) {
        Contact contact = findByName(name);
        contact.setPhone(phone);
        return contact;
    }

    public void delete(String name) {
        Contact contact = findByName(name);
        contacts.remove(contact.getId());
    }

}
