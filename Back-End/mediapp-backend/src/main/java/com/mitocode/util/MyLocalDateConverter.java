package com.mitocode.util;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//local date time lo convierte a una fecha de tipo base de datos sql

@Converter(autoApply = true)//convierte a util.date
public class MyLocalDateConverter implements AttributeConverter<java.time.LocalDate , java.sql.Date>{

	@Override
	public Date convertToDatabaseColumn(java.time.LocalDate attribute) {
		// TODO Auto-generated method stub
		return attribute == null ? null :java.sql.Date.valueOf(attribute);
	}

	@Override
	public LocalDate convertToEntityAttribute(java.sql.Date dbData) {
		// TODO Auto-generated method stub
		return dbData == null ? null : dbData.toLocalDate();
	}
	

}
