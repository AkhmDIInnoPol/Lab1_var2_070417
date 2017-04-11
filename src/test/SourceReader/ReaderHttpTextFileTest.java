package test.SourceReader;


import com.company.SourceReader.ReaderHttpTextFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReaderHttpTextFileTest
{

    @Test
    public void getTextTest()
    {
        String ref1 = "https://gist.githubusercontent.com/AkhmDIInnoPol/1a95e140d4b162bf14017d763502ceda/raw/7ddb5800fcc849b77e3caa6f2e186947eb8339d6/HttpTest1.txt";
        String ref2 = "https://gist.githubusercontent.com/AkhmDIInnoPol/7aa31e14728333903d9b85abf70ac7e8/raw/3d17f80fb73fafe89b22525ff5ed8b89dd4d7d4d/HttpTest2.txt";

        ReaderHttpTextFile.setEncoding("UTF-8");
        ReaderHttpTextFile.setTextLineNumber(0);
        ReaderHttpTextFile readerHttpTextFile = new ReaderHttpTextFile();

        String resText = readerHttpTextFile.getText(ref1);
        String trueRes = "Привет, мир. Кот Васька. Hellow world.";

        assertTrue(resText.equals(trueRes));

        String resText2 = readerHttpTextFile.getText(ref2);
        String trueRes2 = "Река течет.Ветер шамит. Машина едет.";

        assertTrue(resText2.equals(trueRes2));
    }






}
