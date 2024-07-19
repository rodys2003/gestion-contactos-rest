package com.rersdev.gestioncontactos.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record InsertContactDataDTO(

        @NotBlank(message = "Contact name is required")
        @Size(min = 2, message = "The name must be at least 2 characters long")
        @Size(max = 30, message = "The name cannot exceed 30 characters")
        String name,

        @Size(min = 5, message = "The title must be at least 10 characters long")
        @Size(max = 30, message = "The title cannot exceed 30 characters")
        String title,

        @Pattern(regexp = "(?i)^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}+$", message = "Invalid email")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^(\\+)?\\d{10,13}$", message = "Phone number must be between 10 and 13 digits")
        String phone,

        @Size(min = 10, message = "The address must be at least 10 characters long")
        @Size(max = 40, message = "The address cannot exceed 40 characters")
        String address,

        @Size(min = 5, max = 15, message = "It must be at least 5 characters long and it cannot exceed 15 characters")
        String status

) implements Serializable {
    public InsertContactDataDTO {
        if (status == null || status.isEmpty()) {
            status = "Active";
        }
    }
}
