package com.rersdev.gestioncontactos.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CONTACTS")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String img;

    private String name;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_CODE")
    private Country country;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Address> addresses;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Phone> phones;
}
