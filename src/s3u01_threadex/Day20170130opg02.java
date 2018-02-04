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
In this exercise you must create a simple Producer-Consumer design, with a number of producers which create random numbers, which are consumed by a single consumer process.

This figure illustrates what you have to implement.
P1-P4 represents four Producer Threads, each producing a number of random numbers.
Producers place all produced numbers in a shared data structure “Queue” (implemented via a BlockingQueue implementation). A single Consumer Thread consumes all the produced Numbers.

a) 
Before you start you should understand the general idea, behind the exercise as described above by answering the following questions.
1. If we need a “large” collection of random numbers, what is the advantage (if any) of introducing threads to “produce” the numbers?
2. Why does the exercise suggest 4 producer threads, and is that always the right  number?
3. Given that the Queue is a BlockingQueue implementation, how would you insert data into the Queue (add(), offer(), put() ) if it’s limited in capacity, and items are produced much faster than they are produced (Think: what happens when you insert into a full queue)?
    (https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/BlockingQueue.html)
4. Given that the Queue is a BlockingQueue implementation, how would you fetch data from the Queue (remove(), poll(), take() ) if Production is slow, compared to how we consume items

b)  Use the code provided in day2.rndnumberprodcon as start code for this exercise. Compile and run the main method in Tester.

c) Complete the run() method in the RandomNumberProducer class, by producing the required number of random numbers and insert them into the numbersProduced Queue (again, chose the right insert method).

d) Complete the run() method in the RandomNumberConsumer class so that the sumTotal variable is updated, and all consumed numbers are inserted into either the below50 or aboveOr50 Lists.

f) Run and “verify” the behaviour of the completed program 

*/

public class Day20170130opg02 {
    public void run() {
        System.out.println("Day20170130opg02:\n");
        System.out.println("se klassens kommentarer");
        
        
    }
}

/*
a.1)
Hvis man bruger tråde til at lave samlingen, kan hver tråd lave nogle af tallene hver,
og lægger så tallene ind i køen efterhånden som de bliver produceret.
der kan derfor laves flere tal på den samme tid, da der bruges flere tråde.

a.2)
nummeret af tråde er valgfrit. der kan sagtens være flere eller færre.
Jeg gætter på at de har valgt tallet 4 fordi 4 kernede computere
er almindelige i dag. 
Antallet af tråde man meningsfuldt kan bruge i programmet
er begrænset af antallet af kerner/processore i computeren
(1 tråd pr. processor).

hvis man bruger flere tråde end der er processore, skal de tråde der køre på den
samme processor løbende tage over fra hinanden.
Det kan være en god ting at køre lidt flere tråde
end der er processore, hvis nogle af trådene bliver blokkeret, eller 
der er flere prorammer der køre på computeren samtidig.
men hvis der kun er 1 program der køre,og ingen blokkering 
vil det være optimalt med 1 tråd pr. processor.

Moderne processore kan dog køre flere tråde på en gang uden at skifte mellem dem (context switching)

kilde: https://stackoverflow.com/questions/1718465/optimal-number-of-threads-per-core

a.3)
// https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/BlockingQueue.html
Jeg ville bruge put, fordi den blokker operationen indtil den kan lykkes,
dvs. indtil køen har plads til næste værdi.

a.4)
boolean remove(Object o)
Removes a single instance of the specified element from this queue, if it is present.

Error 	poll(long timeout, TimeUnit unit)
Retrieves and removes the head of this queue, waiting up to the specified wait time 
if necessary for an element to become available.

Error 	take()
Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.

hvis produktionen er langsom sammenlignet med konsumptionen,
ville jeg vaelge take(), fordi den venter paa at produktionen faar lavet en ny entry i queue.


*/

/*
b)
Use the code provided in day2.rndnumberprodcon as start code for this exercise. 
Compile and run the main method in Tester.

da run metoden i RandomNumberProducer ikke er lavet, får man en "ikke implementeret" fejl.

output (default code):
Exception in thread "pool-1-thread-2" Exception in thread "pool-1-thread-1" Exception in thread "pool-1-thread-3" java.lang.UnsupportedOperationException: You nedd to complete the run() method for this to work
	at day2.rndnumberprodcon.RandomNumberProducer.run(RandomNumberProducer.java:20)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
java.lang.UnsupportedOperationException: You nedd to complete the run() method for this to work
	at day2.rndnumberprodcon.RandomNumberProducer.run(RandomNumberProducer.java:20)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
Exception in thread "pool-1-thread-4" java.lang.UnsupportedOperationException: You nedd to complete the run() method for this to work
	at day2.rndnumberprodcon.RandomNumberProducer.run(RandomNumberProducer.java:20)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
java.lang.UnsupportedOperationException: You nedd to complete the run() method for this to work
	at day2.rndnumberprodcon.RandomNumberProducer.run(RandomNumberProducer.java:20)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
Total of all random numbers: 0
Number of random numbers below 50: 0
Number of random numbers >= 50: 0

*/

/*
c)
Complete the run() method in the RandomNumberProducer class, 
by producing the required number of random numbers and insert them into the numbersProduced Queue 
(again, chose the right insert method).

== talene er produceret, men man kan ikke se at man har gjort det rigtigt før 
man har lavet opgave d.

*/

/*
d)
Complete the run() method in the RandomNumberConsumer class so that the sumTotal variable is updated, 
and all consumed numbers are inserted into either the below50 or aboveOr50 Lists.

*/

/*
f) (der er ingen e)
output: 
Total of all random numbers: 400
Number of random numbers below 50: 216
Number of random numbers >= 50: 184

*/