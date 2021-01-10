package com.example.dictionary.tests;

import com.example.dictionary.fileConverter.JsonToMapConverter;
import com.example.dictionary.inversDictionary.InversDictionary;
import com.example.dictionary.translateText.TranslateSimple;
import com.example.dictionary.wordCounter.WordInTextCounter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class junitTestClasses {

    @Autowired
    private JsonToMapConverter jsonToMapConverter;

    @Autowired
    private TranslateSimple translateSimple;

    @Autowired
    private InversDictionary inversDictionary;

    @Autowired
    private WordInTextCounter wordInTextCounter;

    @Test
    public void testJsonConvertFromFile(){

        Map<String, String> testDictionaryMap;

        testDictionaryMap = jsonToMapConverter.parseJsonFileToHashMap();

        testDictionaryMap.forEach((key, value) -> System.out.println(key + " - " + value));

        Assert.assertTrue(true);
    }

    @Test
    public void testTranslateSimplePlToEn() {
        //Given
        String stringToTranslate = "kota walcz ma";
        String stringToCompare = ("a cat fight has");
        String stringResult = null;

        Map<String, String> dictionary;
        dictionary = jsonToMapConverter.parseJsonFileToHashMap();

        //When
        stringResult = translateSimple.translatedSimpleText(stringToTranslate, dictionary, false);

        //Then
        assertTrue(stringToCompare.equalsIgnoreCase(stringResult));
    }

    @Test
    public void testTranslateSimpleEnToPl() {
        //Given
        String stringToTranslate = "a cat fight has";
        String stringToCompare = ("kota walcz ma");
        String stringResult = null;

        Map<String, String> dictionary;
        dictionary = jsonToMapConverter.parseJsonFileToHashMap();
        dictionary = inversDictionary.reverseKeyValue(dictionary);

        //When
        stringResult = translateSimple.translatedSimpleText(stringToTranslate, dictionary, false);

        //Then
        assertTrue(stringToCompare.equalsIgnoreCase(stringResult));

    }

    @Test
    public void testTranslateSimplePlToEnInQuotes() {
        //Given
        String stringToTranslate = "kota walcz ma";
        String stringToCompare = ("\"a cat\" \"fight\" \"has\"");
        String stringResult = null;

        Map<String, String> dictionary;
        dictionary = jsonToMapConverter.parseJsonFileToHashMap();

        //When
        stringResult = translateSimple.translatedSimpleText(stringToTranslate, dictionary, true);
        //Then
        assertTrue(stringToCompare.equalsIgnoreCase(stringResult));
    }

    @Test
    public void testTranslateSimpleEnToPlInQuotes() {
        //Given
        String stringToTranslate = "a cat fight has";
        String stringToCompare = ("\"kota\" \"walcz\" \"ma\"");
        String stringResult = null;

        Map<String, String> dictionary;
        dictionary = jsonToMapConverter.parseJsonFileToHashMap();
        dictionary = inversDictionary.reverseKeyValue(dictionary);

        //When
        stringResult = translateSimple.translatedSimpleText(stringToTranslate, dictionary, true);

        //Then
        assertTrue(stringToCompare.equalsIgnoreCase(stringResult));

    }

    @Test
    public void testWordCount() {
        //Given
        String stringToTranslate = "kota kota walcz walcz walcz ma";
        String stringResult = null;
        Map<String, Integer> toCompareWithResult =  new HashMap<>();
        Map<String, Integer> result =  new HashMap<>();
        toCompareWithResult.put("fight", 3);
        toCompareWithResult.put("a cat", 2);
        toCompareWithResult.put("has", 1);

        Map<String, String> dictionary;
        dictionary = jsonToMapConverter.parseJsonFileToHashMap();

        //When
        stringResult = translateSimple.translatedSimpleText(stringToTranslate, dictionary, false);
        result = wordInTextCounter.countWordsInTranslation(translateSimple.getWordListFromTranslation());
        //Then
        assertEquals(toCompareWithResult,result );
    }
}
