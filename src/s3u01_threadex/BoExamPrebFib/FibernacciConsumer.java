/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex.BoExamPrebFib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bo
 */
public class FibernacciConsumer implements Runnable {

    int totalFibSum;
    List<Integer> fibernacciNumbers = new ArrayList();
    ArrayBlockingQueue<Integer> producedNumbers;
    private volatile boolean moreNumbersToConsume = true;

    public FibernacciConsumer(ArrayBlockingQueue<Integer> producedNumbers) {
        this.producedNumbers = producedNumbers;
    }

    @Override
    public void run() {
        Integer fibNumber;

        while (moreNumbersToConsume) {
            try {
                //Stopper programmet for tidligt så ændre timeouten
                fibNumber = producedNumbers.poll(10, TimeUnit.SECONDS);
                if (fibNumber != null) {
                    System.out.println("Fibernacci number is: " + fibNumber);
                    totalFibSum += fibNumber;
                    //System.out.println("Totalsummen er " + totalFibSum);//Hvis der skal printes subtotal
                } else {
                    moreNumbersToConsume = false;
                }
            } catch (InterruptedException e) {
                System.out.println("Fejl i consumer!");
            }
        }
    }

    public int getTotalFibSum() {
        return totalFibSum;
    }

}