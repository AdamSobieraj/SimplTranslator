package com.example.dictionary.controller;

import com.example.dictionary.fileConverter.JsonToMapConverter;
import com.example.dictionary.inversDictionary.InversDictionary;
import com.example.dictionary.translateText.TranslateSimple;
import com.example.dictionary.wordCounter.WordInTextCounter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")//allow no security
@RequestMapping("/v1/trans")
public class TranslatorController {

    private final JsonToMapConverter jsonToMapConverter;

    private final InversDictionary inversDictionary;

    private final TranslateSimple translateSimple;

    private final WordInTextCounter wordInTextCounter;

    @Autowired
    public TranslatorController(JsonToMapConverter jsonToMapConverter, InversDictionary inversDictionary, TranslateSimple translateSimple, WordInTextCounter wordInTextCounter) {
        this.jsonToMapConverter = jsonToMapConverter;
        this.inversDictionary = inversDictionary;
        this.translateSimple = translateSimple;
        this.wordInTextCounter = wordInTextCounter;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/transentopl/{worden}/{inquotes}")
    public String getEnToPlTrans(@PathVariable String worden, @PathVariable Boolean inquotes){
        Map<String, String> dictionary;
        dictionary = jsonToMapConverter.parseJsonFileToHashMap();
        return translateSimple.translatedSimpleText(worden, dictionary, inquotes);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "transpltoen/{wordpl}/{inquotes}")
    public String getPlToEnTrans(@PathVariable String wordpl, @PathVariable Boolean inquotes){
        Map<String, String> dictionary;
        dictionary = jsonToMapConverter.parseJsonFileToHashMap();
        dictionary = inversDictionary.reverseKeyValue(dictionary);
        return translateSimple.translatedSimpleText(wordpl, dictionary, inquotes);
    }

    @RequestMapping(method = RequestMethod.GET, value ="getelementcount" )
    public String  getElementCount(){
        Map<String, Integer> result;
        result = wordInTextCounter.countWordsInTranslation(translateSimple.getWordListFromTranslation());
        GsonBuilder gsonMapBuilder = new GsonBuilder();
        Gson gsonObject = gsonMapBuilder.create();
        return gsonObject.toJson(result);
    }

}
