package com.example.dictionary.fileConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class JsonToMapConverter {

    public Map<String, String> parseJsonFileToHashMap() {

        InputStream getLocalJsonLocalFile = null;
        HashMap<String, String> jsonMap = new HashMap<>();

        try {
            getLocalJsonLocalFile = new FileInputStream("src/main/resources/programData/dictionary.json");
        }catch (FileNotFoundException | IllegalArgumentException e){
            System.out.println("Błąd odczytu pliku : " + e );
        }

        try {
            jsonMap = new ObjectMapper().readValue(getLocalJsonLocalFile, HashMap.class);
            getLocalJsonLocalFile.close();
        }catch (IOException e){
            System.out.println("Błąd przemapowania pliku JSON do HashMap : " + e);
        }

        return jsonMap;
    }
}
