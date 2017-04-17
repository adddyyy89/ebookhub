package com.ebookhub.hibernate.model;

public abstract class GenericModel<DTO, Entity> {

	abstract DTO toDTO(Entity entity);

	abstract Entity toEntity(DTO dto);

}
