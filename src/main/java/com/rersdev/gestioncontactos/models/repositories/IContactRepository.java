package com.rersdev.gestioncontactos.models.repositories;

import com.rersdev.gestioncontactos.models.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IContactRepository extends JpaRepository<Contact, UUID> {
}
