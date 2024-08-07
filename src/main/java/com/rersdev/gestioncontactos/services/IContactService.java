package com.rersdev.gestioncontactos.services;

import com.rersdev.gestioncontactos.controllers.dto.InsertContactDataDTO;
import com.rersdev.gestioncontactos.controllers.dto.ShowContactDataDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IContactService {

    void createContact(InsertContactDataDTO newContact);

    ShowContactDataDTO updateContact(InsertContactDataDTO updatedContact, UUID id);

    ShowContactDataDTO getContactById(UUID id);

    Page<ShowContactDataDTO> getAllContacts(int page, int size);

    void deleteContact(UUID id);

    String uploadImage(MultipartFile file, UUID id);
}
