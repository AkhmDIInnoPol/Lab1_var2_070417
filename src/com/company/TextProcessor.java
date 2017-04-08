package com.company;

import com.company.SourceReader.Reader;
import com.company.SourceReader.ReaderFromFile;

import java.util.Set;
import java.util.TreeSet;

/**
 * Class run processing of external source and find from it russian words.
 * Than it save them in global store {@link #wordsStore}.
 */
public class TextProcessor implements Runnable {

    /** Reference to external source (file name, http link or something else). */
    private String reference;

    /** General store that store new words. */
    private static Set<String> wordsStore = new TreeSet<>();

    /** General flag that show to all threads to do termination. */
    private static boolean isDuplicationMet;

    /** Id for debug. Identify every thread. */
    private final int threadId;

    private static int threadDoneCounter = 0;

    private static int totalThreadNum;

    private Object mainThreadNotifier;


    public TextProcessor(String reference, int threadId, Object mainThreadNotifier,
                            int totalThreadNum) {
        this.reference = reference;
        this.threadId = threadId;
        this.mainThreadNotifier = mainThreadNotifier;
        this.totalThreadNum = totalThreadNum;
    }

    @Override
    public void run() {

        String text = getTextFromSource();

        String[] words = getWordsFromText(text);

        for (String word:words
                ) {

            if (isDuplicationMet)
            {
                return;
            }

            isWordDuplicated(word);

            putNewWordInStore(word);
        }

        notifyThisThreadDone();
    }


    /**
     * Get text from some source. It may be file or http page
     * or somthing else.
     * @return text from this source.
     */
    private String getTextFromSource()
    {
        Reader reader =new ReaderFromFile();
        return reader.getText(reference);
    }

    /**
     * Get only russian words and ignore other symbols.
     * @param text - whole text that may contain russian words.
     * @return - array of russian words.
     */
    private String[] getWordsFromText(String text)
    {
        return TextParser.parseText(text);
    }


    /**
     * Check if this word already encountered in total store.
     * @param word - word that need to check on duplication.
     */
    private void isWordDuplicated(String word)
    {
        synchronized (wordsStore)
        {
            if (wordsStore.contains(word))
            {
                isDuplicationMet = true;
                System.out.println("Word " + word + " is duplicated !!!.");

                synchronized (mainThreadNotifier)
                {
                    mainThreadNotifier.notify();
                }

                return;
            }
        }
    }


    /**
     * Put new word in total store.
     * @param word - new word that need to put to store.
     */
    private void putNewWordInStore(String word)
    {
        synchronized (wordsStore)
        {
            wordsStore.add(word);
        }
    }

    /**
     * Function increase counter of thread that done their work.
     * When number of thread that finished work wil equal to
     * total started threads (all thread ended their work)
     * than last thread notify about it other external thread.
     */
    private void notifyThisThreadDone()
    {
        synchronized (wordsStore)
        {
            threadDoneCounter++;
            if (threadDoneCounter == totalThreadNum)
            {
                synchronized (mainThreadNotifier)
                {
                    mainThreadNotifier.notify();
                }
            }
        }
    }

    /**
     * Function return main store of word.
     * @return store of new russian words.
     */
    public static Set<String> getWordsStore()
    {
        return wordsStore;
    }

    /**
     * Check if all thread ended their work.
     * @return if true than all threads ended their work.
     */
    public static boolean isAllThreadDone()
    {
        if (threadDoneCounter == totalThreadNum)
        {
            return true;
        }
        else
            return false;
    }

}
