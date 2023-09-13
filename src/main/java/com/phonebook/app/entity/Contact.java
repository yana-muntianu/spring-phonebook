package com.phonebook.app.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Contact {

    @Size(min = 1)
    private String id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String phone;

    public Contact() {

    }

    public Contact(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Contact(String id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

}
