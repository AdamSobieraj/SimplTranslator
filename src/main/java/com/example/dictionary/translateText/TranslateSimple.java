package com.example.dictionary.translateText;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TranslateSimple {

    private List<String> wordListFromTranslation = new ArrayList<String>();

    public String translatedSimpleText(String translate, Map<String, String> dictionary, Boolean inQuotes){
//        wordListFromTranslation.clear();
        String wordSeparator = inQuotes ? "\"" : "";

        StringBuilder result = new StringBuilder();

        StringTokenizer st = new StringTokenizer(translate, " ");
        while (st.hasMoreTokens()) {

            //get word
            String key = st.nextToken() ;
            key = (key.length() == 1 ? (key + " " + st.nextToken() ) : key);

            String translatedWord = dictionary.get(key);
            wordListFromTranslation.add (translatedWord);
            if (translatedWord != null) {
                result.append((wordSeparator + translatedWord + wordSeparator));
            } else {
                result.append("*" + key + "*"); // Unknown word
            }
            result.append(" ");
        }

        return result.toString().trim();
    }

    public List<String> getWordListFromTranslation() {
        return wordListFromTranslation;
    }

}
