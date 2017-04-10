package test;


import com.company.TextParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextParserTest
{
    @Test
    public void parseTextTest()
    {
        String text = "123моеСлово даДа,  \n\n слово34 ДАА И ЕщЕ_Слово \t вотОно вотЭтОСЛоВо.";
        String[] words= TextParser.parseText(text);

        String[] trueWords = {"123моеСлово", "даДа", "слово34", "ДАА", "ЕщЕ_Слово",
                                    "И", "вотОно", "вотЭтОСЛоВо"};

        Set<String> wordsSet = new HashSet(Arrays.asList(words));

        for (String word:trueWords
             ) {
            assertTrue(wordsSet.contains(word));
        }

        assertTrue(wordsSet.size() == trueWords.length);
    }





}
