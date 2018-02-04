/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

/**
 *
 * @author Christian
 */

/*
Exercise 4 (race condition)

The method next() in the class Even should always return an even number (see code snippet below). 
You will implement a program that demonstrates that this is not always true in a multithreaded program.

Create at least two threads, 
which both should call the next() method on the same Even object and test if the return value is even. 
Experiment with the number of calls each thread makes to next().

public class Even
{
  private int n = 0;
  public int next()
  {
    n++;
    n++;
    return n;
  }
}

a) Are you able to provoke the expected error on your machine?
b) How often does the problem occur?
c) Use the synchronization techniques you’ve learned today, to make next() method atomic with respect to itself.
d) Argue that your solution is correct (argue, don’t prove)


*/

/*

a1) Kun t3 og t4 fremprovokere fejlen, for de er de eneste der arbejder paa det samme objekt. 

a2) fejlen sker kun hvis n (i Even klassen) er volatile. 
    Fejlen sker ikke hvis Even klassen er volatile, men n ikke er.

b)  hvis baade t3 og t4 kun kalder Even objektet 500 gange hver, sker fejlen minst 1 gang ca. 50% af tiden.

c)  ved at saette koden i traadene ind i synchronized(objectidentifier) blokken,
    goeres next() metoden atomisk,

d)  naar e3.nect() laegges ind i en synchronized block, kan andre traade ikke tilgaa e3 objektet
    foer koden i traadens synchronized block er koert faerdig. Det betyder ogsaa at hvis
    den efterfoelgende if-statement:
        if (e3.n < 10 || e3.n % 2 != 0) {
            System.out.println("t3: value of e3's n: " + e3.n);
        } 
    placeres uden for synchronized blocken, kan andre traade have aendret objektet
    i mellemtiden. (og den forventede fejl i spoergsmaal a sker faktisk hvis if-statementet
    placeres uden for synchronized blokken).

*/
public class Day20170129opg04 {
    
    private class Even {
        private volatile int n = 0;
        public int next() {
          n++;
          n++;
          return n;
        }
    }
    
    public Even e3 = new Even();
    
    public void run() {
        System.out.println("Day20170129opg04:\n");
        
        Thread t1 = new Thread(() -> {
            Even e1 = new Even();
            e1.next();
            System.out.println("t1: value of e1's n: " + e1.n);
            
        });
        
        Thread t2 = new Thread(() -> {
            Even e2 = new Even();
            e2.next();
            System.out.println("t2: value of e2's n: " + e2.n);
        });
        
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                
                synchronized(e3) { //the synchronized(e3) block is added to create atomicity
                    e3.next();
                    if (e3.n < 10 || e3.n % 2 != 0) {
                        System.out.println("t3: value of e3's n: " + e3.n);
                    }  
                };
                
                
            }
        });
        
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                
                synchronized(e3) { //the synchronized(e3) block is added to create atomicity
                    e3.next();
                    if (e3.n < 10 || e3.n % 2 != 0) {
                        System.out.println("t4: value of e3's n: " + e3.n);
                    }  
                };
                              
            }
        });
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        
    }
}
