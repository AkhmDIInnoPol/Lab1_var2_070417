package com.company;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class parse text and return russian words from it.
 */
public class TextParser {





    /**
     * Take whole text, find russian words and return
     * array of russian words.
     * Russian words it is group of symbols or symbol divided by space
     * symbol orsome other symbol.
     * @param text - text for processing
     * @return - array of russian words.
     */
    public static String[] parseText(String text)
    {
        text = text.replaceAll("[^\\p{L}|\\-|_| ]", "");  //(?U)[^\w|-| ]  // [^а-яА-Я|Ё|ё|\d|a-zA-Z| ]|

        List<String> wordsList = new ArrayList<>(Arrays.asList(text.split("\\s+")));  //

        wordsList.remove( "");

        String[] words = Arrays.copyOf(wordsList.toArray(), wordsList.size(), String[].class);

        words = filterWordsWithDash(words);

        return words;
    }


    /**
     * Function filter words with dash.
     * If word view as "word-word" than remain this word.
     * If word view as "-word" ("word-") or other cases than delete all dashes in it.
     * @param rawWords - raw words before processing.
     * @return words after filter.
     */
    private static String[] filterWordsWithDash(String[] rawWords)
    {
//        Pattern pDashForward = Pattern.compile("^-+(\\p{L})+$");
//        Pattern pDashBack = Pattern.compile("^+(\\p{L})+-$");
        Pattern pDashBetween = Pattern.compile("^[а-яёА-ЯЁ]+-{1}+[а-яёА-ЯЁ]+$");

        String[] processedWords = new String[rawWords.length];

        for (int i = 0; i < rawWords.length; i++) {
            Matcher mDashBetween  = pDashBetween.matcher(rawWords[i]);
            if (!mDashBetween.matches())
            {
                processedWords[i] = rawWords[i].replaceAll("-*","");
            }
            else
            {
                processedWords[i] = rawWords[i];
            }
        }

        return processedWords;
    }




    /** Function return true if it is russian word, else (if foreign)
     * return false.
     * @param word - word for checking.
     * @return true - russian word, false - foreign.
     */
    public static boolean isRussianWord(String word)
    {
        Pattern pattern = Pattern.compile("[а-яА-Я|\\d|\\-|]*");
        Matcher matcher = pattern.matcher(word);

        return matcher.matches();
    }




}
