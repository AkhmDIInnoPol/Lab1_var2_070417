package test;



import com.company.SourceReader.ExternalSourceSelector;
import com.company.SourceReader.ReaderFromFile;
import com.company.SourceReader.ReaderHttpTextFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by Di on 10.04.2017.
 */

public class ExternalSourceSelectorTest
{

    @Test
    public void getAppropriateReaderTest()
    {
        String textFileRef = "name_12.txt";
        assertTrue(ExternalSourceSelector.getAppropriateReader(textFileRef) instanceof ReaderFromFile);

        String textUrlRef = "http://site.com/someFolder/subFolder/someName_23.txt";
        assertTrue(ExternalSourceSelector.getAppropriateReader(textUrlRef) instanceof ReaderHttpTextFile);

        String unknownRef = "host:someSite/something/sub/end.doc";
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->
                            ExternalSourceSelector.getAppropriateReader(unknownRef));
        assertTrue(exception.getMessage().equals("Unknown reference type"));
    }









}
