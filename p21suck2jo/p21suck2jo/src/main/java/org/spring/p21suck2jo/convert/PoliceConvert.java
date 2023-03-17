package org.spring.p21suck2jo.convert;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PoliceConvert implements AttributeConverter<String,Integer> {


    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        return null;
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        return null;
    }
}
