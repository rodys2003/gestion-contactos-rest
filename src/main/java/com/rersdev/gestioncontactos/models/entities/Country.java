package com.rersdev.gestioncontactos.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "COUNTRIES")
public class Country {

    @Id
    private String code;

    private String name;
}
