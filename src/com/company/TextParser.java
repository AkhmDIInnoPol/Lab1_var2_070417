package com.company;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        text = text.replaceAll("[^а-яА-Я| ]|", "");
        String[] words = text.split("\\s+");  //

        return words;
    }






}
