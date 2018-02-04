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
public class Day20170201opg2constack {

    public void run() {
        System.out.println("Day20170201opg2constack");

        // proev med en enkelt traad (main traaden):
        //opgConstack<Integer> stack1 = new opgConstack<>();
        //myMethod(stack1);
        
        
        //lav en raekke traade der laver samme operation.
        opgConstack<Integer> stack2 = new opgConstack<>();
        OpgConstackThread[] producers = new OpgConstackThread[5];
        
        for (int i = 0; i < 5; i++) {
            producers[i] = new OpgConstackThread(stack2, i);
        }   
        
        for (OpgConstackThread pr : producers) {
            pr.start();
        }
        for (OpgConstackThread pr : producers) {
            try {
                pr.join();
            } catch (InterruptedException ex) {
                //Logger.getLogger(Day20170201opg2constack.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("fejl:  Day20170201opg2constack:"
                        + "  for (OpgConstackThread pr : producers): pr.join();");
            }
        }
    
    }

    public void myMethod(opgConstack<Integer> stack) {
        for (int i = 0; i < 10; i++) {
            stack.add(i);
            System.out.println("input: " + i);
        }
        for (int i = 0; i < 10; i++) {
            if (stack.peek() != null) {
                //System.out.println("removed: " + stack.remove());
                System.out.println("removed: " + stack.printStack());
            }
        }
    }
}

//lav en raekke traade der laver samme operation.
                /*
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        
                    }
                };
                */
