package test.testStopAllThreads;

import com.company.TextProcessor;
import org.junit.jupiter.api.Test;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Di on 11.04.2017.
 */
public class testStopAllThreads
{




    @Test
    public void stopAllThreadsTest()
    {

        String[] externalRefs = {"./src/test/testStopAllThreads/testStopAllThreads1.txt",
                                    "./src/test/testStopAllThreads/testStopAllThreads2.txt",
                                    "./src/test/testStopAllThreads/testStopAllThreads3.txt"};

        Object thisThreadNotifier = new Object();

        ExecutorService executor = Executors.newFixedThreadPool(externalRefs.length);



        for (int i = 0; i < externalRefs.length; i++)
        {
            Runnable runnable = new TextProcessor(
                    externalRefs[i], thisThreadNotifier, externalRefs.length);
            executor.execute(runnable);
            new TextProcessor(
                    externalRefs[i], thisThreadNotifier, externalRefs.length);
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
        
        assertTrue(TextProcessor.isAllThreadDone() == false);

        executor.shutdown();
    }






}
