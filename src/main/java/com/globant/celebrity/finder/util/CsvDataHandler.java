package com.globant.celebrity.finder.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

//Source: https://www.baeldung.com/spring-app-setup-with-csv-files

public class CsvDataHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private CsvMapper mapper = new CsvMapper();
    private CsvSchema schema = CsvSchema.emptySchema().withHeader();

    public <T> List<T> getListFromCsv(Class<T> type, String fileName) {
        try {
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(schema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }

    public Map<Integer, Set<Integer>> getIntegerMapOfSetFromCsv(String fileName) {
        Map<Integer, Set<Integer>> output = new HashMap<>();
        MappingIterator<Map<String, String>> it = null;
        try {
            File file = new ClassPathResource(fileName).getFile();
            it = mapper.readerFor(Map.class)
                    .with(schema)
                    .readValues(file);
        } catch (IOException e) {
            logger.error(String.format("Error accessing %s.csv file",fileName));
        }
        Integer key = null;
        Integer value = null;
        while (it.hasNext()) {
            Map<String, String> rowAsMap = it.next();
            key = Integer.parseInt(rowAsMap.get("subject_id"));
            value = Integer.parseInt(rowAsMap.get("known_id"));
            if(Objects.isNull(output.get(key))){
                Set<Integer> valueCollection = new HashSet<>();
                valueCollection.add(value);
                output.put(key, valueCollection);
            }else{
                output.get(key).add(value);
            }
        }
        return output;
    }

    public <T> void write(T type, String fileName){
        try {
            Class typeClass = type.getClass();
            CsvSchema schemaByClass = mapper.schemaFor(typeClass);
            ObjectWriter writer = mapper.writer(schemaByClass.withColumnSeparator(CsvSchema.DEFAULT_COLUMN_SEPARATOR).withoutHeader());
            File file = new ClassPathResource(fileName).getFile();
            OutputStream outStream = new FileOutputStream(file , true);
            writer.writeValue(outStream, type);
        } catch (JsonProcessingException e) {
            logger.error("Error trying to process CSV file.");
        } catch (IOException e) {
            logger.error("Error trying to access CSV file.");
        }
    }
}
