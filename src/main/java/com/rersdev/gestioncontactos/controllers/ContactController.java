package com.rersdev.gestioncontactos.controllers;

import com.rersdev.gestioncontactos.controllers.DTO.InsertContactDataDTO;
import com.rersdev.gestioncontactos.controllers.DTO.ShowContactDataDTO;
import com.rersdev.gestioncontactos.services.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<ShowContactDataDTO>> getAllContacts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                   @RequestParam(value = "size", defaultValue = "5") int size){
        return ResponseEntity.ok().body(contactService.getAllContacts(page, size));
    }

    @GetMapping("/{id} ")
    public ResponseEntity<ShowContactDataDTO> getContactById(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(contactService.getContactById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowContactDataDTO> updateContact(@PathVariable("id") UUID id,
                                                            @RequestBody InsertContactDataDTO updatedContact){
        return ResponseEntity.ok().body(contactService.updateContact(updatedContact, id));
    }
}
