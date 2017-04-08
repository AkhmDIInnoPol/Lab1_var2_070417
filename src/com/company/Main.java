package com.company;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    private static Object thisThreadNotifier = new Object();

    private static String[] externalRefs = {"textFile1.txt", "textFile2.txt", "textFile3.txt"};


    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(externalRefs.length);

        for (int i = 0; i < externalRefs.length; i++)
        {
            Runnable runnable = new TextProcessor(
                    externalRefs[i], thisThreadNotifier, externalRefs.length);
            executor.execute(runnable);
        }

        synchronized (thisThreadNotifier)
        {
            try {
                thisThreadNotifier.wait();
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }

        if (TextProcessor.isAllThreadDone())
        {
            Set<String> newWordsStore = TextProcessor.getWordsStore();

            System.out.println("New words: \n");

            for (String word:newWordsStore
                 ) {
                System.out.println(word);
            }
        }

    }
}
