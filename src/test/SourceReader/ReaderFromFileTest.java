package test.SourceReader;


import com.company.SourceReader.Reader;
import com.company.SourceReader.ReaderFromFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReaderFromFileTest
{


    @Test
    public void getTextTest()
    {
        String ref = "./src/test/SourceReader/testText1.txt";
        Reader reader = new ReaderFromFile();
        String text = reader.getText(ref);

        String trueText = " Зима лето осень весна.";

        assertTrue(text.equals(trueText));
    }



}
