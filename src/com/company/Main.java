package com.company;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    private static Object thisThreadNotifier = new Object();

//    private static String[] externalRefs = {"textFile1.txt", "textFile2.txt", "textFile3.txt"};

    private static String[] externalRefs = {"http://vojnaimir.ru/files/book1.txt", "http://lib.ru/INPROZ/BLUM_E/ruchej.txt",
            "http://www.e-reading.club/txt.php/47096/%D0%9F%D1%83%D1%88%D0%BA%D0%B8%D0%BD_" +
            "-_%D0%9A%D0%B0%D0%BF%D0%B8%D1%82%D0%B0%D0%BD%D1%81%D0%BA%D0%B0%D1%8F_%D0%B4%D0%BE%D1%87%D0%BA%D0%B0.txt"};

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(externalRefs.length);

        for (int i = 0; i < externalRefs.length; i++)
        {
            Runnable runnable = new TextProcessor(
                    externalRefs[i], thisThreadNotifier, externalRefs.length);
            executor.execute(runnable);
        }

        executor.shutdown();

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
