package com.company.SourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class get text from text file through http url.
 */
public class ReaderHttpTextFile implements Reader
{
    /** Read line near middle of text because lines in the beginning are title
     * and contai only few words. */
    private final int TEXT_LINE_NUMBER = 96;

    /** Itis exception container for save intermediate exception stack trace. */
    private Exception exceptionStore;

    /**
     * Function get text from url and from this tect get specified in
     * {@link #TEXT_LINE_NUMBER} row number.
     * @param reference - url of internet resource.
     * @return - piece of text of this resource.
     */
    @Override
    public String getText(String reference) {

        int lineCounter = 0;


        try {
                URL oracle = new URL(reference);
                BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream(), "windows-1251"));

                String inputLine;
                while ((inputLine = in.readLine()) != null)
                {
                    if (lineCounter == TEXT_LINE_NUMBER)
                    {
                        in.close();
                        return inputLine;
                    }

                    lineCounter++;
                }

                in.close();
        }
        catch (MalformedURLException exception)
        {
            exception.printStackTrace();
            exceptionStore = exception;
        }
        catch (IOException ex)
        {
            ex.addSuppressed(exceptionStore);
            ex.printStackTrace();
        }


        throw new IllegalStateException("Something wrong with getText method. " +
                "It doesn't return text from http url.");
    }
}
