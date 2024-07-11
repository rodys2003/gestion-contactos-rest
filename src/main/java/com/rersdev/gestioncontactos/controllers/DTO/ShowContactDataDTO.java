package com.rersdev.gestioncontactos.controllers.DTO;

import java.io.Serializable;
import java.util.UUID;

public record ShowContactDataDTO(
        UUID id,
        String img,
        String name,
        String title,
        String email,
        String phone,
        String address,
        String status) implements Serializable {
}
