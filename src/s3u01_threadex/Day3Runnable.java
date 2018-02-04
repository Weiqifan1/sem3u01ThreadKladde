/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Christian
 */
public class Day3Runnable implements Runnable {

    ArrayBlockingQueue<String> urls;
    
    public Day3Runnable(ArrayBlockingQueue<String> urls) {
        this.urls = urls;
    }
    
    
    
    @Override
    public void run() {
        
    }
    
}
