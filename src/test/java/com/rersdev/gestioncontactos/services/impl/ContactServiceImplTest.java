package com.rersdev.gestioncontactos.services.impl;

import com.rersdev.gestioncontactos.configuration.exception.contact.ContactNotFoundException;
import com.rersdev.gestioncontactos.controllers.dto.ShowContactDataDTO;
import com.rersdev.gestioncontactos.controllers.mappers.IContactMapper;
import com.rersdev.gestioncontactos.models.entities.Contact;
import com.rersdev.gestioncontactos.models.repositories.IContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.UUID;

import static com.rersdev.gestioncontactos.DataProvider.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock
    IContactRepository contactRepository;
    @Mock
    ImageServiceImpl imageService;
    @Mock
    IContactMapper contactMapper;

    @InjectMocks
    ContactServiceImpl contactService;

    @Test
    void TestFindContact_NotNull() {
        UUID contactId = UUID.fromString("75d89889-0d4e-46ba-904b-e078dffc5690");
        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contactMock()));
        when(contactMapper.toDTO(any(Contact.class))).thenReturn(showContactMock());

        ShowContactDataDTO result = contactService.getContactById(contactId);

        assertNotNull(result);
    }

    @Test
    void TestFindContactById() {
        UUID contactId = UUID.fromString("75d89889-0d4e-46ba-904b-e078dffc5690");
        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contactMock()));
        when(contactMapper.toDTO(any(Contact.class))).thenReturn(showContactMock());

        ShowContactDataDTO result = contactService.getContactById(contactId);

        assertEquals(contactId, result.id());
        verify(contactRepository, times(1)).findById(contactId);
    }

    @Test
    void TestFindContactById_NotFount() {
        UUID contactId = UUID.fromString("75d89889-0d4e-46ba-904b-e078dffc5690");
        when(contactRepository.findById(contactId)).thenReturn(Optional.empty());

        assertThrows(ContactNotFoundException.class, () -> contactService.getContactById(contactId));
    }

    @Test
    void testDeleteContact() {
        UUID contactId = UUID.fromString("75d89889-0d4e-46ba-904b-e078dffc5690");
        when(contactRepository.existsById(contactId)).thenReturn(true);

        contactService.deleteContact(contactId);

        ArgumentCaptor<UUID> argumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(contactRepository).deleteById(argumentCaptor.capture());
        assertEquals(contactId, argumentCaptor.getValue());
    }

    @Test
    void testDeleteContact_NotFount() {
        UUID contactId = UUID.fromString("75d89889-0d4e-46ba-904b-e078dffc5690");
        when(contactRepository.existsById(contactId)).thenReturn(false);

        assertThrows(ContactNotFoundException.class, () -> contactService.deleteContact(contactId));
    }

    @Test
    void testGetAllContacts() {
        Page<Contact> contactPage = new PageImpl<>(contactListMock());
        when(contactRepository.findAll(PageRequest.of(0, 5, Sort.by("name")))).thenReturn(contactPage);

        Page<ShowContactDataDTO> result = contactService.getAllContacts(0, 5);
        assertEquals(4, result.getTotalElements());
    }
}
