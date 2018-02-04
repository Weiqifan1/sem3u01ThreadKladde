/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian
 */

// illustration af wait - notify problemet
// 
public class Day20170130_eks01 {
    
    static boolean valueIsSet = false;
    static int value = 0;
    
    public void run() throws InterruptedException {
        
        Object monitor = new Object(); // det er en konvention at kalde det monitor.

        Thread t1 = new Thread(() -> {
                synchronized(monitor){  //vi bruger monitor som vores lås
                        System.out.println("tl starts");
                        while(!valueIsSet) {
                            try {
                                monitor.wait(); //når wait bliver kaldt, skal der være noget der kalder notify
                            } catch (InterruptedException ex) {
                                System.out.println("noget gik galt");
                            }
                        }
                        System.out.println("t1 can now use value, and terminate");
                }
        });
        
        t1.start();
        Thread.sleep(1500);
        synchronized(monitor) {
            value = 42;
            valueIsSet = true;
            monitor.notify(); // her bliver notify kaldt. Når "synchronized(monitor)" blokken er slut, 
            //begyndet t1 igen, og vi når ned til linje 37.
        }
        
        
    }
}
