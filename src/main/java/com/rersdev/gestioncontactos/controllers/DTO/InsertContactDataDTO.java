package com.rersdev.gestioncontactos.controllers.DTO;

import java.io.Serializable;

public record InsertContactDataDTO(
        String name,
        String title,
        String email,
        String phone,
        String address,
        String status) implements Serializable {
}
