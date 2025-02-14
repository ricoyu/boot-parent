package com.loserico.jpa.converter;

import com.loserico.common.lang.utils.EnumUtils;
import com.loserico.jpa.enums.EquipmentType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EquipmentTypeConverter implements AttributeConverter<EquipmentType, String> {
	
	@Override
	public String convertToDatabaseColumn(EquipmentType attribute) {
		return attribute.getTypeId();
	}
	
	@Override
	public EquipmentType convertToEntityAttribute(String dbData) {
		return EnumUtils.toEnum(EquipmentType.class, dbData, "typeId");
	}
}
