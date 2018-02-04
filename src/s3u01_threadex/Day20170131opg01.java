/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Christian
 */

/*

*/

public class Day20170131opg01 {
    public void run() {
        System.out.println("Day20170131opg01:\n");
        
        //se sequential pinger: SequentialPinger
        
        //Time using 0 threads: 9.79761526seconds
        // Time using 0 threads: 9.675047956seconds
        //Time using 0 threads: 8.950833168seconds
        
    }
    
    public void ParrallelPinger() {
        String[] hostList = { "http://crunchify.com", "http://yahoo.com",

                "http://www.ebay.com", "http://google.com",

                "http://www.example.co", "https://paypal.com",

                "http://bing.com/", "http://techcrunch.com/",

                "http://mashable.com/", "http://thenextweb.com/",

                "http://wordpress.com/", "http://cphbusiness.dk/",

                "http://example.com/", "http://sjsu.edu/",

                "http://ebay.co.uk/", "http://google.co.uk/",

                "http://www.wikipedia.org/",

                "http://dr.dk","http://pol.dk","https://www.google.dk",

                "http://phoronix.com" ,"http://www.webupd8.org/",

                "https://studypoint-plaul.rhcloud.com/", "http://stackoverflow.com",

                "http://docs.oracle.com","https://fronter.com",

                "http://imgur.com/", "http://www.imagemagick.org"

                };
        
        ArrayBlockingQueue<String> urls = new ArrayBlockingQueue(hostList.length);
        
        ExecutorService es = Executors.newCachedThreadPool();
        
        
        
    }
    
}
