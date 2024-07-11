package com.rersdev.gestioncontactos.services;

import com.rersdev.gestioncontactos.controllers.DTO.InsertContactDataDTO;
import com.rersdev.gestioncontactos.controllers.DTO.ShowContactDataDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IContactService {

    void createContact(InsertContactDataDTO newContact);

    ShowContactDataDTO updateContact(InsertContactDataDTO updatedContact, UUID id);

    ShowContactDataDTO getContactById(UUID id);

    Page<ShowContactDataDTO> getAllContacts(int page, int size);

    void deleteContact(UUID id);
}
