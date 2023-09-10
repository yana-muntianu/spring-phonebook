package com.phonebook.app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Contact() {

    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Contact(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
                   @JsonProperty("phone") String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Contact(@JsonProperty("id") String id, @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
                   @JsonProperty("phone") String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

}
