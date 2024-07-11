package com.rersdev.gestioncontactos.controllers.mappers;

import com.rersdev.gestioncontactos.controllers.DTO.InsertContactDataDTO;
import com.rersdev.gestioncontactos.controllers.DTO.ShowContactDataDTO;
import com.rersdev.gestioncontactos.models.entities.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IContactMapper {

    Contact toEntity(InsertContactDataDTO insertContactDataDTO);

    ShowContactDataDTO toDTO(Contact contact);

    Contact toEntity(ShowContactDataDTO showContactDataDTO);

    void updateContactFromDTO(@MappingTarget Contact contact, InsertContactDataDTO updatedContact);
}
