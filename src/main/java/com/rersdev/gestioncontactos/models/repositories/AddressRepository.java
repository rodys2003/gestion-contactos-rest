package com.rersdev.gestioncontactos.models.repositories;

import com.rersdev.gestioncontactos.models.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends CrudRepository<Address, UUID> {
}
