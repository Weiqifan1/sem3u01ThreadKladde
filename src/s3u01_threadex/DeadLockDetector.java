/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 *
 * @author Christian
 */
public class DeadLockDetector extends Thread {
    
    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
    long[] deadThreadIds = null;
    boolean keepRunning = true; 
    boolean someThreadsAlive = true; //tjekker om de relevante traade stadig koere    
    Thread[] possibleDeadlocks; //lav en array der indeholder alle de relevante traade;

    public DeadLockDetector(Thread[] possibleDeadlocks) {
        this.possibleDeadlocks = possibleDeadlocks;
    } 
      
    
    public void run() {
        while (keepRunning) {
            deadThreadIds = bean.findDeadlockedThreads();
            //...            
            if (deadThreadIds != null) {
                ThreadInfo threadInfo[] = bean.getThreadInfo(deadThreadIds); //tilfoejet for at se deadlocked threads
                for (ThreadInfo tf : threadInfo){
                        System.out.println("the following thread is in deadlock: " + 
                                tf.getThreadName());    //Printer navnene paa alle deadlocked threads
                }
                keepRunning = false;
            }
            
            //foelgende stopper traaden naar der ikke er relevante levende traade tilbage:
            someThreadsAlive = false;
            for (Thread thread : possibleDeadlocks) {
                if (thread.isAlive()) {
                    someThreadsAlive = true;
                }
            }
            if (someThreadsAlive == false) {
                keepRunning = false;
            }
                        
        }
    }
    
    
}
