/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian
 */
public class opgProconConsumer extends Thread {
    
    ArrayBlockingQueue<Long> fibNumbers;
    long totalSum = 0;
    int fibsExpected = 0;
    
    public opgProconConsumer(ArrayBlockingQueue<Long> fibNumbers, int fibsExpected) {
        this.fibNumbers = fibNumbers;
        this.fibsExpected = fibsExpected;
    } 
    
    @Override
    public void run() {
        Long currentLong = null;
        
        System.out.println("fibNumbers isEmpty: " + fibNumbers.isEmpty()); 
        // lav et while loop der iterere ind til alle fibsExpected er koert. vent hvis  fibNumbers er tom.
        
        while (fibsExpected > 0) {
            //while (fibNumbers.peek() != null) {
                try {
                    currentLong = fibNumbers.take();
                    totalSum += currentLong;
                    --fibsExpected;
                    System.out.println("current fib: " + currentLong);     
                } catch (InterruptedException ex) {
                    //Logger.getLogger(opgProconConsumer.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("opgProconConsumer: run(): fejl i currentLong = fibNumbers.take();" );
                }                                              
            //}
        }
        
        
        
        System.out.println("totalSum: " + totalSum);     
        System.out.println("fibNumbers isEmpty: " + fibNumbers.isEmpty()); 
    }
    
}
