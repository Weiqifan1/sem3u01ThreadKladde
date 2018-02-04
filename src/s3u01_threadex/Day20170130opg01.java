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
Thread Exercises - Day 2

Fork (or clone, and delete the .git folder) this repository (same as for day-1): 
https://github.com/Cphdat3sem2017f/startCodeForThreadExercises.git   to get the start code for exercises below

Exercise1 (Basic Threads, and measuring performance) 
Why is this exercise green? It’s green because it assumes you did the basic thread exercises from day-1. If, then there is really nothing new in this exercise, except that the problem is a “bit more realistic”.

Use the start code in day2.webscraper for this exercise. The example uses a library jsoup (see the pom-file) to web-scrape information from 3 different web-pages.
This library abstracts away most of the details with performing a programmatically request up against a web site and parse the received HTML.
Since accessing a remote URL involves a lot of blocking (we will talk more about this next week), the main purpose with this exercise is to see whether there is any (performance) benefit in performing the three request via three separate threads.

a)
Run the main method in the Tester class, and make sure you understand conceptually what happens. Especially you should note that these lines : tcX.run() probably takes a noticeable amount of time (why?)

b)
Refactor the TagCounter class to extend the Thread class. This should be very simple (why ?)

c)
Change the Tester class to not call run(), but start the three threads (what's the BIG difference?)
This will most likely mean that all your system.out’s will be empty or null (why?)
Fix the problem above

d)
Let's see whether we gained anything by executing the three calculations in parallel, or if we could have achieved the same result via sequential execution.

First lets see how many Kernels your system offers. Add this line to the beginning of your main():
System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());

Use the following skeleton to measure execution time for sequential execution (observe that we are calling the run() method, not start() to get sequential execution (one more time, make sure you understand the BIG difference).:

long start = System.nanoTime();
t1.run();
t2.run();
t3.run(); 
…
long end = System.nanoTime();
System.out.println("Time Sequential: "+(end-start));

Now use the same principle to measure execution time for parallel execution (don't get the end time before all threads has stopped)

Explain the results*

*Obviously this depends on the OS, 
    hardware, and the number of running processes. 
    If the numbers do not make sense, explain what you were expecting to see.


*/        

public class Day20170130opg01 {
    public void run() {
        System.out.println("Day20170130opg01:\n");
        System.out.println("se klassens kommentarer");
        
        
    }
}

/*
2017-01-30 kommentarer:
    kan ikke faa den kopierede kode til at virke. Maa skrive kommentarer til koden som den er i github projektet.
*/

/*
default output(hvis der er internet adgang.)

Title: FCK.DK | Officiel hjemmeside F.C. København | Danske mestre og pokalvindere 2017. | F.C. København
Div's: 498
Body's: 1
Title: Google
Div's: 206
Body's: 1
Title: Forsiden - politiken.dk
Div's: 751
Body's: 1
*/

/*
a) 
Run the main method in the Tester class, and make sure you understand conceptually what happens. 
Especially you should note that these lines : tcX.run() probably takes a noticeable amount of time (why?)

ifølge opgave teksten, kunne det skyldes at det at tilgå en fremmed Url involvere 
meget "blocking" som tager tid.
*/

/*
b)
Refactor the TagCounter class to extend the Thread class. This should be very simple (why ?)

Man behøver kun at udskifte "public class TagCounter {" med "public class TagCounter extends Thread {"
og 
tilføje "@Override" over "public void run(){".


*/

/*
c)
Change the Tester class to not call run(), but start the three threads (what's the BIG difference?)
This will most likely mean that all your system.out’s will be empty or null (why?)
Fix the problem above

3 eksempler på køretider med defailt kode. 
(der bruges ikke tråde, så programmet køre sekventielt)
4.04 s
3.85 s
7.02 s
3 eksempler på køre tider, når eg. "tc3.run();" er skiftet ud med "tc3.start();": 
2.68 s
2.65 s
2.64 s.

output med "extend Thread" ect:

Title: null
Div's: 0
Body's: 0
Title: null
Div's: 0
Body's: 0
Title: null
Div's: 0
Body's: 0

Output er tomt eller null, fordi main tråden køre sys.out inden 
tc1, tc2 og tc2 er færdige med at køre. det er altså et lost update problem.


Hvis man skal have sys.out til at virke, skal de lægges ind i trådenes run metode:
eller man skal skrive eg. tc1.join() inden sys.out. 

output:

Title: null
Div's: 0
Body's: 0
Title: null
Div's: 0
Body's: 0
Title: null
Div's: 0
Body's: 0
Title: FCK.DK | Officiel hjemmeside F.C. København | Danske mestre og pokalvindere 2017. | F.C. København
Div's: 498
Body's: 1
Title: Google
Div's: 206
Body's: 1
Title: Forsiden - politiken.dk
Div's: 751
Body's: 1

*/

/*
d)

output: parallel:
Available Processors: 4
Time Sequential: 1564682552

output: sequencial:
Available Processors: 4
Time Sequential: 2798554657

tiden ses i nano sekunder: 1,56 sekunder for parallel og 2,8 for sekventiel.
når koden køres parallelt, går det ca. dobbelt så hurtigt, da der bruges flere processore.


main-code: parallel:
// opg 1.d)
    System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());  
    long start = System.nanoTime();
            
    TagCounter tc1 = new TagCounter("http://www.fck.dk");
    tc1.start(); 
    //tc1.run();
    TagCounter tc2= new TagCounter("http://www.google.com");
    tc2.start();  
    //tc2.run();
    TagCounter tc3= new TagCounter("http://politiken.dk/");
    tc3.start();  
    //tc3.run();
    
    tc1.join();
    tc2.join();
    tc3.join();
    
    // opg 1.d)
    long end = System.nanoTime();
    System.out.println("Time Sequential: "+(end-start));

main-code: sequencial:
// opg 1.d)
    System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());  
    long start = System.nanoTime();
        
    
    TagCounter tc1 = new TagCounter("http://www.fck.dk");
    //tc1.start(); 
    tc1.run();
    TagCounter tc2= new TagCounter("http://www.google.com");
    //tc2.start();  
    tc2.run();
    TagCounter tc3= new TagCounter("http://politiken.dk/");
    //tc3.start();  
    tc3.run();
        
    // opg 1.d)
    long end = System.nanoTime();
    System.out.println("Time Sequential: "+(end-start));
*/