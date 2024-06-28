package com.rersdev.gestioncontactos.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "CONTACTS")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false)
    UUID id;

    String img;

    String name;

    String title;

    String email;

    String phone;

    String address;

    String status;
}
