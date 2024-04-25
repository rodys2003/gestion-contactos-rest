package com.rersdev.gestioncontactos.models.repositories;

import com.rersdev.gestioncontactos.models.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICountryRepository extends CrudRepository<Country, String> {

    List<Country> findByNameContainingIgnoreCase(String name);
}
