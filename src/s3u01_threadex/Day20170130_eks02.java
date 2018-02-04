/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian
 */

// eksempel på producer consumer pattern.
// (der er exceptions over det hele. Det er fordi der altid er en riciko for at det er interopted.
   // det skal vi ikke tage os af for der er intet vi kan gøre ved det hvis det liver interopted)

//(min kode virker ikke, den er fejlkopieret)

public class Day20170130_eks02 {
    public void run() {
        
        BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<Integer>(5);
        
        
        Runnable producer = () -> {
            Random rnd = new Random();
            
            try {
                while(true){
                    int rndnum = rnd.nextInt();
                    sharedQueue.put(rndnum);
                    
                }
            } catch (InterruptedException ex) {
                System.out.println("der er noget galt i producer");
            }
        };
        
        Runnable consumer = () -> {
            try {
                while(true) {
                    int num = sharedQueue.take();
                }
                
            } catch (InterruptedException ex) {
                System.out.println("der er noget galt i consumer");
            }
        };
        
        Thread[] consumers = new Thread[10];
        Thread[] producers = new Thread[10];
        
        for (int i = 0; i < 10; i++) {
            consumers[i] = new Thread(consumer);
            producers[i] = new Thread(producer);
            
            
        }
        
        for (Thread t : consumers) {
            t.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("fejl i sleep");
            }
        }
        
        for (Thread t : producers) {
            t.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("fejl i sleep");
            }
        }
        
        /*
        Thread p1 = new Thread(producer);
        Thread p2 = new Thread(producer);
        
        Thread c1 = new Thread(consumer);
        Thread c2 = new Thread(consumer);
        
        c2.start();
        c1.start();
        
        
        p1.start();
        p2.start();
        */
    }
}
