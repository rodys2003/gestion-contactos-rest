package com.rersdev.gestioncontactos.services.impl;

import com.rersdev.gestioncontactos.configuration.exception.contact.ContactNotFoundException;
import com.rersdev.gestioncontactos.configuration.exception.contact.PhoneAlreadyExistException;
import com.rersdev.gestioncontactos.controllers.dto.InsertContactDataDTO;
import com.rersdev.gestioncontactos.controllers.dto.ShowContactDataDTO;
import com.rersdev.gestioncontactos.controllers.mappers.IContactMapper;
import com.rersdev.gestioncontactos.models.entities.Contact;
import com.rersdev.gestioncontactos.models.repositories.IContactRepository;
import com.rersdev.gestioncontactos.services.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor

@Transactional
@Service
public class ContactServiceImpl implements IContactService {

    private final IContactRepository contactRepository;
    private final IContactMapper contactMapper;
    private final ImageServiceImpl imageService;

    @Override
    public void createContact(InsertContactDataDTO newContact) {
        this.phoneExists(newContact.phone());
        contactRepository.save(contactMapper.toEntity(newContact));
    }

    @Override
    public ShowContactDataDTO updateContact(InsertContactDataDTO updatedContact, UUID id) {
        Contact contactDB = this.validateContactById(id);

        contactMapper.updateContactFromDTO(contactDB, updatedContact);
        return contactMapper.toDTO(contactRepository.save(contactDB));
    }

    @Transactional(readOnly = true)
    @Override
    public ShowContactDataDTO getContactById(UUID id) {
        return contactMapper.toDTO(this.validateContactById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ShowContactDataDTO> getAllContacts(int page, int size) {
        return contactRepository.findAll(PageRequest.of(page, size, Sort.by("name"))).map(contactMapper::toDTO);
    }

    @Override
    public void deleteContact(UUID id) {
        this.contactExists(id);
        contactRepository.deleteById(id);
    }

    @Override
    public String uploadImage(MultipartFile file, UUID id) {
        Contact contact = validateContactById(id);

        String imageUrl = imageService.storeImage(id, file);
        contact.setImg(imageUrl);
        contactRepository.save(contact);
        return imageUrl;
    }

    private Contact validateContactById(UUID id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));
    }

    private void phoneExists(String phone) {
        if (contactRepository.existsByPhone(phone)) {
            throw new PhoneAlreadyExistException("Phone " + phone + " already exist");
        }
    }

    private void contactExists(UUID id) {
        if (!contactRepository.existsById(id)) {
            throw new ContactNotFoundException("Contact does not exist with id: " + id);
        }
    }
}
