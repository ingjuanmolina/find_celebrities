package com.globant.celebrity.finder.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.globant.celebrity.finder.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

//Source: https://www.baeldung.com/spring-app-setup-with-csv-files

public class CsvDataHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }

    public void write(Person person, String fileName){
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Person.class);
        try {
            ObjectWriter writer = mapper.writer(schema.withColumnSeparator(CsvSchema.DEFAULT_COLUMN_SEPARATOR));
            //String csv = mapper.writer(schema).writeValueAsString(person);
            File file = new ClassPathResource(fileName).getFile();
            OutputStream outstream = new FileOutputStream(file , true);
            writer.writeValue(outstream, person);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
