package com.rersdev.gestioncontactos.controllers;

import com.rersdev.gestioncontactos.configuration.util.LocalDirectory;
import com.rersdev.gestioncontactos.controllers.dto.InsertContactDataDTO;
import com.rersdev.gestioncontactos.controllers.dto.ShowContactDataDTO;
import com.rersdev.gestioncontactos.services.IContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Tag(name = "Contacts", description = "Manage all endpoints about contacts")

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final IContactService contactService;

    @Operation(
            summary = "Register contacts"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Contact registered successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = InsertContactDataDTO.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = {@Content}),
                    @ApiResponse(responseCode = "409", description = "Phone number already exists", content = {@Content}),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = {@Content})
            }
    )
    @PostMapping
    public ResponseEntity<String> registerContact(@RequestBody @Valid InsertContactDataDTO contactDataDTO) throws URISyntaxException {
        contactService.createContact(contactDataDTO);
        return ResponseEntity
                .created(new URI("api/v1/contacts"))
                .body("Contact registered successfully");
    }

    @Operation(
            summary = "Get all registered contacts",
            description = "Get all contacts in a paginated list"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contact list successfully generated"),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = {@Content}),
            }
    )
    @Parameters({
            @Parameter(name = "page", description = "Page number", example = "0"),
            @Parameter(name = "size", description = "Size of the page", example = "10")
    })
    @GetMapping
    public ResponseEntity<Page<ShowContactDataDTO>> getAllContacts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                   @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok().body(contactService.getAllContacts(page, size));
    }

    @Operation(
            summary = "Get contact by id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contact found successfully",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShowContactDataDTO.class))}),
                    @ApiResponse(responseCode = "404", description = "Contact not found", content = {@Content}),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = {@Content})
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ShowContactDataDTO> getContactById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(contactService.getContactById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowContactDataDTO> updateContact(@PathVariable UUID id,
                                                            @RequestBody @Valid InsertContactDataDTO updatedContact) {
        return ResponseEntity.ok().body(contactService.updateContact(updatedContact, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactById(@PathVariable UUID id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam UUID id, @RequestParam MultipartFile file) {
        return ResponseEntity.ok().body(contactService.uploadImage(file, id));
    }

    @GetMapping(path = "image/{filename}", produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})
    public byte[] getImage(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(LocalDirectory.getImageDirectory() + filename));
    }
}
