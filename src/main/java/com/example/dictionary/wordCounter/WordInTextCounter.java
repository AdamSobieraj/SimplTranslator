package com.example.dictionary.wordCounter;

import static java.util.stream.Collectors.toMap;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class WordInTextCounter {

    public Map<String, Integer> countWordsInTranslation(List<String> wordListFromTranslation){
        Map<String, Integer> wordCountInText = new HashMap<>();

        if (!wordListFromTranslation.isEmpty()){
            wordCountInText = wordListFromTranslation.stream()
                    .collect(toMap(
                            str -> str,
                            str -> 1,
                            Integer::sum));

            Map<String,Integer> sortedNewMap = wordCountInText.entrySet().stream()
                    .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));

            return sortedNewMap;
        }
        return wordCountInText;
    }

}
