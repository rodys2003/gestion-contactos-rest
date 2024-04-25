package com.rersdev.gestioncontactos.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String department;

    private String city;

    @Column(name = "STREET_NUMBER")
    private String streetNumber;

    @Column(name = "REFERENCE_POINT")
    private String referencePoint;

    @ManyToOne
    @JoinColumn(name = "CONTACT_ID")
    private Contact contact;
}
