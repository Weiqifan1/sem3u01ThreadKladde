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
public class opgProconProducer  extends Thread {//implements Runnable

    ArrayBlockingQueue<Integer> numbersToUse;
    ArrayBlockingQueue<Long> fibNumbers;
    
    public opgProconProducer(ArrayBlockingQueue<Integer> numbersToUse, ArrayBlockingQueue<Long> fibNumbers) {
        this.numbersToUse = numbersToUse;
        this.fibNumbers = fibNumbers;
    }    
    
    @Override
    public void run() {
        
        Integer numToUse = null;
        Long fibNum = null;
        
        while (numbersToUse.peek() != null) {
            
            numToUse = numbersToUse.poll();                
            
            fibNum = fib(numToUse);
            
            try {
                fibNumbers.put(fibNum);
            } catch (InterruptedException ex) {
                //Logger.getLogger(opgProconProducer.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("opgProconProducer: run(): fejl i fibNumbers.put(fibNum);" );
            }
            
            System.out.println("startnum: " + numToUse + " current fib: " + fibNum);
        }
        
              
        
        
        
    }
    
    private long fib(long n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fib(n-1) + fib(n-2);
        }
    }
    
}
