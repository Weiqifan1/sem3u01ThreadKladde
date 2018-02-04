/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian
 */

/*
Exercise 2 
Write a function that takes an int n as parameter, and creates n threads, running at the same time.
Each thread 1,..(n-1),n should print the numbers from 1 to n*100, including the “thread id” (n) on each line.
a) describe the output. Is it what you expected?
b) If the threads do not interleave, can you make them, by making the threads sleep for a short period of time? (think milliseconds).

 */

/*
2017-01-30:
output viser at trådene starter i tilfældig rækkefølge og printer deres output "interleaved" mellem
de andre trådes output. Det var det resultat jeg forventede. Hvis jeg tilføjer "sleep" forstaerkes effekten,
og trådene bliver mere "interleaved".
*/
public class Day20170129opg02 {
    
    private void writeThreadOutput(int n) {
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            final int j = i;
            threads[i] = new Thread(() -> {
                for (int k = 0; k < n*100; k++) {
                    System.out.println("thread " + j + ": " + k);
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000); // hvis man bruger sleep, interleaver de perfekt
                                                            //(alle "1'ere" laver før alle "2'ere" ect.)
                    } catch (InterruptedException ex) {
                        System.out.println("error: problem med sleep");
                    }
                }                              
            });
        }
        
        for (Thread t : threads) {
            t.start();
        }
    }

    public void run() {
        writeThreadOutput(10);
    }
    
}
