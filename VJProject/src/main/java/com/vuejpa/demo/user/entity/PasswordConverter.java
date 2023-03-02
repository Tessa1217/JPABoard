package com.vuejpa.demo.user.entity;

import com.vuejpa.demo.common.util.Aes256Utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PasswordConverter implements AttributeConverter<String, String> {
	
	@Override
	public String convertToDatabaseColumn(String attribute) {
		return Aes256Utils.encode(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return Aes256Utils.decode(dbData);
	}

}
