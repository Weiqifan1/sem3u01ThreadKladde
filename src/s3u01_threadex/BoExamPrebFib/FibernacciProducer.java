/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex.BoExamPrebFib;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Bo
 */
public class FibernacciProducer implements Runnable {

    BlockingQueue<Integer> numbersToUse;
    BlockingQueue<Integer> producedNumbers;
    private volatile boolean moreNumbersToFetch = true;

    public FibernacciProducer(BlockingQueue<Integer> numbersToUse, BlockingQueue<Integer> producedNumbers) {
        this.numbersToUse = numbersToUse;
        this.producedNumbers = producedNumbers;
    }

    @Override
    public void run() {

        while (moreNumbersToFetch) {

            try {
                Integer number = numbersToUse.poll();

                if (number == null) {
                    moreNumbersToFetch = false;
                } else {
                    long fibNumber = fib(number);
                    //Skal castes til Integer
                    Integer fibInt = (int) (long) fibNumber;
                    producedNumbers.put(fibInt);
                }
            } catch (InterruptedException e) {
                System.out.println("Fejl i producer run metoden!");
            }
        }

    }

    //Metoden er ikke synkroniseret, da det ikke har betydning
    //for resultatet om n ændrer sig mellem tråde. 
    private long fib(long n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
    
}