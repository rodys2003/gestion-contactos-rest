package com.rersdev.gestioncontactos.controllers;

import com.rersdev.gestioncontactos.configuration.util.LocalDirectory;
import com.rersdev.gestioncontactos.controllers.DTO.InsertContactDataDTO;
import com.rersdev.gestioncontactos.controllers.DTO.ShowContactDataDTO;
import com.rersdev.gestioncontactos.services.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final IContactService contactService;
    private final String imageDirectory;

    @Autowired
    public ContactController(IContactService contactService) {
        this.contactService = contactService;
        this.imageDirectory = LocalDirectory.getImageDirectory();
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<ShowContactDataDTO> getContactById(@PathVariable UUID id){
        return ResponseEntity.ok().body(contactService.getContactById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowContactDataDTO> updateContact(@PathVariable UUID id,
                                                            @RequestBody InsertContactDataDTO updatedContact){
        return ResponseEntity.ok().body(contactService.updateContact(updatedContact, id));
    }

    @PutMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam UUID id, @RequestParam MultipartFile file){
        return ResponseEntity.ok().body(contactService.uploadImage(file, id));
    }

    @GetMapping(path = "image/{filename}", produces = {IMAGE_PNG_VALUE,IMAGE_JPEG_VALUE})
    public byte[] getImage(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(imageDirectory + filename));
    }
}
