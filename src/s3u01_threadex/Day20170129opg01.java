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
    "S3u01_ThreadEX_2017-01-29_exercise_01" 
    Exercise 1 (create, start and end threads)

Create a program that starts 3 different parallel threads.

thread1: Compute and print the sum of all numbers from 1 to 1 billion
thread2: Print the numbers from 1 to 5. Pause for 2 seconds between each print.
thread3: Print all numbers from 10 and up. Pause for 3 seconds between each print.

The program should stop thread3 after 10 seconds.

Hint:     For the sum in thread1, use the a long data type, the result is about 5e+17
Hint2:     Let the main thread sleep for 10 seconds after starting thread3. The Thread class has a    static method “sleep(n)”, which takes an integer n, and makes the calling thread sleep for n milliseconds.
Hint3:     Use a Boolean value in the loop in thread3 to terminate task3 (let the main thread 
change the value of the boolean value).


Answer the following questions:
For thread3 we need to use the synchronization techniques we’ve learned in class.
a) Do you observe a need for synchronization techniques on any of the threads in practise, or does your program work by accident?
b) Regardless of whether you observe the problem on your machine, we need to handle it.
What is the problem I’m hinting at, and how can we solve it? (Since I’m such a Nice Guy™, here’s a hint: think about the VolatileExample class from the demo).
c) argue that your solution is correct (argue, don’t prove)

    
     */
public class Day20170129opg01 {
    public void run() {
        System.out.println("S3u01_ThreadEX_2017-01-29_exercise_01");

        Thread t1 = new Thread(() -> {
            long i = 0;
            for (int j = 0; j < 1e9; j++) {
                i += j;
            }
            System.out.println("t1: " + i);
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 1; i < 6; i++) {
                System.out.println("t2: " + i);
                if (i < 5) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(S3u01_ThreadEX.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        });
        
        //opgaven insinuere at time stamp, og testen "while (millis2 < millis+10000)" sker ud i main.
        //      hvis man gør det sådan, er der behov for synkronisering. prøv det!!!
        
        //long millis = System.currentTimeMillis();
        //long millis2 = millis;
        //long bignum = 10;
        Thread t3 = new Thread(() -> {
            long millis = System.currentTimeMillis();
            long millis2 = millis;
            long bignum = 10;
            
            while (millis2 < millis+10000) {
                millis2 = System.currentTimeMillis();
                System.out.println("t3: " + bignum++);
                try {
                        TimeUnit.MILLISECONDS.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(S3u01_ThreadEX.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            
            
        });
        
        t1.start();
        t2.start();
        t3.start();
    }
}
