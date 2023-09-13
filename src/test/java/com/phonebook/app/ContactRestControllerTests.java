package com.phonebook.app;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonebook.app.entity.Contact;
import com.phonebook.app.service.ContactService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ContactRestControllerTests {

    @MockBean
    private ContactService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /contacts/{lastName} success")
    void testGetContactSuccess() throws Exception {
        Contact contact = new Contact("Test", "User1", "6244290004");
        doReturn(contact).when(service).getContact("User1");

        mockMvc.perform(get("/api/v1/contacts/{lastName}", "User1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.firstName", is("Test")))
                .andExpect(jsonPath("$.phone", is("6244290004")));

    }

    @Test
    @DisplayName("POST /contacts success")
    void testCreateContactSuccess() throws Exception {
        Contact contactToPost = new Contact("Test", "User1", "6244290004");
        Contact contactToReturn = new Contact("Test", "User1", "6244290004");

        doReturn(contactToReturn).when(service).saveContact(any());

        mockMvc.perform(post("/api/v1/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(contactToPost)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.firstName", is("Test")))
                .andExpect(jsonPath("$.phone", is("6244290004")));

    }

    @Test
    @DisplayName("DELETE /contacts/{lastName} success")
    void testDeleteContactSuccess() throws Exception {
        Contact contact = new Contact("Test", "User1", "6244290004");
        doReturn(contact).when(service).getContact("User1");

        mockMvc.perform(delete("/api/v1/contacts/{lastName}", "User1"))
                .andExpect(status().isOk());

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}