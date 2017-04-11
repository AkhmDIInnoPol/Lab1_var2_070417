package com.company.SourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class get textLineNumber from textLineNumber file through http url.
 */
public class ReaderHttpTextFile implements Reader
{
    /** Read line near middle of textLineNumber because lines in the beginning are title
     * and contai only few words. */
    private static int textLineNumber = 74;

    /** It is exception container for save intermediate exception stack trace. */
    private Exception exceptionStore;

    /** Encoding type of http resources. */
    private static String sEncoding = "windows-1251";

    /**
     * Function get textLineNumber from url and from this textLineNumber get specified in
     * {@link #textLineNumber} row number.
     * @param reference - url of internet resource.
     * @return - piece of textLineNumber of this resource.
     */
    @Override
    public String getText(String reference) {

        int lineCounter = 0;


        try {
                URL oracle = new URL(reference);
                BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream(), sEncoding));

                String inputLine;
                while ((inputLine = in.readLine()) != null)
                {
                    if (lineCounter == textLineNumber)
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
                "It doesn't return textLineNumber from http url.");
    }




    public static void setEncoding(String encoding)
    {
        sEncoding = encoding;
    }


    public static void setTextLineNumber(int lineNum)
    {
        textLineNumber = lineNum;
    }

}
