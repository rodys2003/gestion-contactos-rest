package com.rersdev.gestioncontactos.controllers;

import com.rersdev.gestioncontactos.controllers.DTO.InsertContactDataDTO;
import com.rersdev.gestioncontactos.services.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final IContactService contactService;

    @PostMapping
    public ResponseEntity<String> registerContact(@RequestBody InsertContactDataDTO contactDataDTO) throws URISyntaxException {
        contactService.createContact(contactDataDTO);
        return ResponseEntity
                .created(new URI("api/v1/contacts"))
                .body("Contact registered successfully");
    }
}
