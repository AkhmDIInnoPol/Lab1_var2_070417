package com.company.SourceReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class get text from specified in reference file.
 */
public class ReaderFromFile implements Reader {


    /**
     * Get text from file specified in reference link.
     * @param reference - link to file.
     * @return - text from this file.
     */
    @Override
    public String getText(String reference) {

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(reference))) {
            String tempStr = "";

            Stream<String> lines = bufferedReader.lines();
            List<String> linesList = lines.map(s -> tempStr + s).collect(Collectors.toList());

            String totalLine = "";
            for (String line : linesList
                    ) {
                totalLine += " " + line;
            }


            byte[] bytes = totalLine.getBytes("UTF-8");
            String text = new String(bytes, "UTF-8");

            return text;
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }


        throw new IllegalStateException("Something wrong with getText method. " +
                                            "It doesn't return text from file.");

    }
}
