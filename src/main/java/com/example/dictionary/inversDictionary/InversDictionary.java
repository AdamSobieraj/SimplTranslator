package com.example.dictionary.inversDictionary;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InversDictionary {

    public Map<String, String> reverseKeyValue(Map<String, String> inputData){

        Map<String, String> result =  new HashMap<>();

            for (Map.Entry<String, String> entry : inputData.entrySet()){
                result.put(entry.getValue(), entry.getKey());
            }

        return result;
    }
}
