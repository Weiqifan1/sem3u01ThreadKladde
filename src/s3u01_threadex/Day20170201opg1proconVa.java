/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Christian
 */
public class Day20170201opg1proconVa {
        
    public void calculateFib(int threads) throws InterruptedException {
                
        System.out.println("Day20170201opg1proconVa" );
        System.out.println(" 4,5,8,12,21,22,34,35,36,37,42 ");
        long startTime = System.nanoTime();
        
        //BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<Integer>(12);
        opgProconProducer[] producers = new opgProconProducer[threads];
        ArrayBlockingQueue<Integer> numbersToUse = new ArrayBlockingQueue<Integer>(12);
        ArrayBlockingQueue<Long> fibNumbers = new ArrayBlockingQueue<Long>(12);
        
        
        int[] myInts = new int[]{4,5,8,12,21,22,34,35,36,37,42};
        
        for (int i = 0; i < myInts.length; i++) {
            numbersToUse.put(myInts[i]);
        }
        //opgProconProducer pro1 = new opgProconProducer(numbersToUse, fibNumbers);      
        
        for (int i = 0; i < threads; i++) {
            producers[i] = new opgProconProducer(numbersToUse, fibNumbers);
        }        
        opgProconConsumer con = new opgProconConsumer(fibNumbers, myInts.length);
        
        for (opgProconProducer pr : producers) {
            pr.start();
        }
        con.start();
        
        for (opgProconProducer pr : producers) {
            pr.join();
        }
        con.join();
        
        long endTime = System.nanoTime();
        
        double diff = endTime-startTime;
        diff = diff / 1000000000;
        
        System.out.println("Time using "+threads+" threads: "+diff + "seconds");
        /*
        public static void main(String[] args) throws InterruptedException {
        //This is the shared Counter used by all turnstilles
        TurnstileCounter sharedCounter = new TurnstileCounter();

        for (int i = 0; i < NUMBER_OF_TURNSTILES; i++) {
          turnStiles[i] = new Turnstile(sharedCounter);
        }

        //This example uses a ThreadPool to handle threads
        ExecutorService es = Executors.newCachedThreadPool();

        for (int i = 0; i < NUMBER_OF_TURNSTILES; i++) {
          es.execute(turnStiles[i]);
        }

        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("All turnstiles are done");
        //Print the updated value
        System.out.println(sharedCounter.getValue());
  }
        */
        
    }
    
    public void run() throws InterruptedException {
        
        //calculateFib(1); //Time using 1 threads: 2.956558684seconds
        //calculateFib(2); //Time using 2 threads: 2.635524126seconds
        //calculateFib(3); //Time using 3 threads: 2.598622937seconds
        calculateFib(4); //Time using 4 threads: 2.639773292seconds
        
        /*
        slutresultat: totalSum: 321961647        
        */        
        
        /*
        Resultatet skyldes at flere traade 
        */
        
    }
    
    /*
        
        opgProconProducer pro1 = new opgProconProducer(numbersToUse, fibNumbers);
        opgProconProducer pro2 = new opgProconProducer(numbersToUse, fibNumbers);
        opgProconProducer pro3 = new opgProconProducer(numbersToUse, fibNumbers);
        opgProconProducer pro4 = new opgProconProducer(numbersToUse, fibNumbers);
        
        opgProconConsumer con = new opgProconConsumer(fibNumbers, myInts.length);
        
        pro1.start();
        pro2.start();
        pro3.start();
        pro4.start();
        
        con.start();
        
        pro1.join();
        pro2.join();
        pro3.join();
        pro4.join();
        
        con.join();
    */
    
    
}
