package com.rersdev.gestioncontactos;

import com.rersdev.gestioncontactos.controllers.dto.ShowContactDataDTO;
import com.rersdev.gestioncontactos.models.entities.Contact;

import java.util.List;
import java.util.UUID;

public class DataProvider {

    public static List<Contact> contactListMock() {
        return List.of(
                new Contact(UUID.fromString("75d89889-0d4e-46ba-904b-e078dffc5690"), "img1.jpg", "John Doe", "Manager", "john.doe@example.com", "123-456-7890", "123 Main St", "Active"),
                new Contact(UUID.fromString("75d89889-0d4e-46ba-904b-e078dffc5691"), "img2.jpg", "Juan", "Friend", "juan@example.com", "300-729-7890", "123 Main St", "Active"),
                new Contact(UUID.fromString("75d89889-0d4e-46ba-904b-e078dffc5692"), "img3.jpg", "Jane Smith", "Developer", "jane.smith@example.com", "098-765-4321", "456 Elm St", "Inactive"),
                new Contact(UUID.fromString("75d89889-0d4e-46ba-904b-e078dffc5693"), "img4.jpg", "Carlos Smith", "Developer", "jane.smith@example.com", "456-765-4321", "456 Elm St", "Inactive")
        );
    }

    public static Contact contactMock() {
        return new Contact(UUID.fromString("75d89889-0d4e-46ba-904b-e078dffc5690"), "img1.jpg", "John Doe", "Manager", "john.doe@example.com", "123-456-7890", "123 Main St", "Active");
    }

    public static ShowContactDataDTO showContactMock() {
        return new ShowContactDataDTO(UUID.fromString("75d89889-0d4e-46ba-904b-e078dffc5690"), "img1.jpg", "John Doe", "Manager", "john.doe@example.com", "123-456-7890", "123 Main St", "Active");
    }

}
