package com.company.SourceReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Determine what type of object return on base of reference view.
 */
public class ExternalSourceSelector
{


    /**
     * Function select appropriate handler for given reference.
     * @param reference - reference to external source.
     * @return - type of appropriate handler of reference.
     *          Handler is IO class for get text from external source.
     */
    public static Reader getAppropriateReader(String reference)
    {
        if (isHttpTxtUrl(reference))
        {
            return new ReaderHttpTextFile();
        }

        if (isTxtFile(reference))
        {
            return new ReaderFromFile();
        }

        throw new IllegalArgumentException("Uknown reference type");
    }


    /**
     * Check if this url for text file.
     * @param ref - reference for checking.
     * @return - if true than it is text file.
     */
    private static boolean isTxtFile(String ref)
    {
        Pattern pattern = Pattern.compile(".*.\\.txt*$");
        Matcher matcher = pattern.matcher(ref);

        return matcher.matches();
    }


    /**
     * Check if this url for for http text URL.
     * @param ref - reference for checking.
     * @return - if true than it is http text URL.
     */
    private static boolean isHttpTxtUrl(String ref)
    {
        Pattern pattern = Pattern.compile("^http.*.txt*$");
        Matcher matcher = pattern.matcher(ref);

        return matcher.matches();
    }




}
