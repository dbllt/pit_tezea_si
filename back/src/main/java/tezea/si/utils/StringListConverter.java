package tezea.si.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * This AttributeConverter is used to save granted authorities in the user
 * table. It saves all the authorities as a single string with all authorities
 * separated by a semicolon (;)
 * 
 * If the list of authorities is empty, is puts a <b>NULL</b> in the table
 * 
 * @author Nils Richard
 *
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        return stringList != null ? String.join(SPLIT_CHAR, stringList) : null;
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) : emptyList();
    }

    private List<String> emptyList() {
        return new ArrayList<>();
    }
}