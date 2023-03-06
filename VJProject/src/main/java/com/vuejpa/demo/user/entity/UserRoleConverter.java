package com.vuejpa.demo.user.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserRoleConverter implements AttributeConverter<Role, String>{

	@Override
	public String convertToDatabaseColumn(Role attribute) {
		return attribute.getValue();
	}

	@Override
	public Role convertToEntityAttribute(String dbData) {
		return Role.valueOf(dbData);
	}

}
