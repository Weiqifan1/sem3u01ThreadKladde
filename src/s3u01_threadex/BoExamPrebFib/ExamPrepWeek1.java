/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex.BoExamPrebFib;

/**
 *  2017-02-04 kl. 15.11
 * @author Bo (kopi til review)
 */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Bo
 */
public class ExamPrepWeek1 {

    public static void main(String[] args) {

        //Laver en kø af integers FIFO. Er thread safe. 
        //De tal som skal udregnes som Fibernacci tal.
        ArrayBlockingQueue<Integer> s1 = new ArrayBlockingQueue(12);

        //Tilføjer numre til s1
        int[] numbersToProducer = {42, 37, 36, 35, 34, 22, 21, 12, 8, 5, 4}; //{4, 5, 8, 12, 21, 22, 34, 35, 36, 37, 42};

        for (int i = 0; i < numbersToProducer.length; i++) {
            s1.add(numbersToProducer[i]);
        }

        //De udregnede Fibonacci tal. s2
        ArrayBlockingQueue<Integer> producedNumbers = new ArrayBlockingQueue(12);

        //Creates a thread pool that creates new threads as needed, 
        //but will reuse previously constructed threads when they are available.
        ExecutorService es = Executors.newCachedThreadPool();

        //Laver og starter de 4 producere. P1 til P4
        es.execute(new FibernacciProducer(s1, producedNumbers));
        es.execute(new FibernacciProducer(s1, producedNumbers));
        es.execute(new FibernacciProducer(s1, producedNumbers));
        es.execute(new FibernacciProducer(s1, producedNumbers));

        //Laver en consumer og starter den
        FibernacciConsumer consumer = new FibernacciConsumer(producedNumbers);
        es.execute(consumer);

        System.out.println("faerdig 1");
        es.shutdown();
        System.out.println("faerdig 2");
        /*
        try {
            es.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("Totalsummen er " + consumer.getTotalFibSum());
            System.out.println("Closing Down. Wait 10 secounds...");
        } catch (InterruptedException e) {
            System.out.println("Fejl ved closing down i main");
        }
        */
    }
}

//2017-02-04: kommentarer: Christian Lykke:
// bo Totalsummen:   321961647
// chr totalSum:     321961647
/*
    Bo's klasser: se mappen: "Bo exam prep fib"

    Chr tilsvarende opgave:
        Day20170201opg1proconVa  //indeholder bedste traad program af de 2
        Day20170201opg1proconVb // indeholder chr's svar paa teksterne

*/

/*
General Part:
    bullet 1)
    When and why will we use Threads in our programs?

        Når der er store datamængder, så kan hver tråd arbejde på en del af datamængden. 
        Eller ved     program med GUI – en tråd venter på svar fra brugeren, og andre tråde arbejder i programmet.
        Why: Performance – Programmet bliver hurtigere og responsiveness – GUI eksempel.

    **** kommentar:
    --------------- 

    bullet 2)
    What is the Race Condition Problem and how can you solve it?

        Race condition opstår når 2 eller flere tråde har adgang til delte variabler, 
        og de prøver at     ændre den samme variabel på samme tid. Eksempel if statement check-then-act.
        Løsning: For at undgå race condition. 4 muligheder.
        De 3 føste er vigtige:
        Holder flere låse på en gang.
        deler resoucer
        cykliske låsereferance
        programmet kan ikke løse deadlock.

    *** kommentar:
    Jeg ved ikke helt hvad der deles med deler ressourcer. Er det f.eks.
    at dele en liste med tal der skal behandles, saa hver traad faar en del liste, istedet for
    at de arbejder paa den samme?

    "cykliske låsereferance" -- dem husker jeg ikke. Hvad er det ?

    
    bullet 3)
    Explain the Producer/Consumer-problem and how to solve it in modern Java Programs

        Producer consumer problemet er 2 processor som deler en kø.
        Producerens job er at generer data og putte det ind i køen, og fortsætte med det.
        Consumerens job er at bruge dataene dvs fjerne dem fra køen. En ad gangen.
        Problemet opstår når produceren vil putte data i en kø, 
        der er fuld eller consumeren henter     data fra en tom kø.
        Løsning: sleep eller smid data væk. Ved sleep: producer sleep. 
        Næste gang consumeren     fjerne noget fra køen notifier den produceren og omvendt.

    *** kommentar:
    -----------------------

    bullet 4)
    Explain what Busy Waiting is and why it's a bad thing in a modern software system.
    
        Når tråden venter optager den tråden dvs den venter aktivt. 
        Spærer for brug af en tråd, som ville kunne gøre noget andet i ventetiden. 
        Sleep, lock og wait swapper processen ud dvs laver noget andet i ventetiden.

    *** kommentar:
    ---------------------------

    bullet 5)
    Describe Java's ​BlockingQueue
​  interface, relevant implementations and methods relevant for the producer consumer problem.    
        
        Er en kø, som er thread safe at putte data i og tage data ud fra.
        Har 4 forskellige metoder for insætte, fjerne og undersøge elementerne i køen.
        Hver sæt af metoder opfører sig forskellige, hvis den ønskede funktion ikke kan udføres.
        Implementations: Da det er et interfaces skal en af de forskellige implamentationer bruges 
        eks.vis. ArrayBlockingQueue eller LinkedBlockingQueue.

    *** kommentar:
    ---------------------------

Practical part:
    
    1)
        A thread (in this case the main-thread) must initially fill a shared data structure 
        S1 with numbers from which the corresponding Fibonacci numbers should be calculated.

        *** kommentar: 
        opgaven udfoeres med linjerne (i main):
            //Laver en kø af integers FIFO. Er thread safe. 
            //De tal som skal udregnes som Fibernacci tal.
            ArrayBlockingQueue<Integer> s1 = new ArrayBlockingQueue(12);

            //Tilføjer numre til s1
            int[] numbersToProducer = {42, 37, 36, 35, 34, 22, 21, 12, 8, 5, 4}; //{4, 5, 8, 12, 21, 22, 34, 35, 36, 37, 42};

            for (int i = 0; i < numbersToProducer.length; i++) {
                s1.add(numbersToProducer[i]);
            }
*/
/*
    2)
        The Main thread should start the four Producer threads (P1 - P4), 
        that all uses the shared data structure S1 to retrieve values 
        for which they should calculate the corresponding Fibonacci number. 
        When a thread has finished the calculation, 
        it should add the result to the shared data structure S2, 
        and continue with the next number in S1. If S1 is empty, 
        the producer should stop (not sleep or wait).

        *** kommentar:
        i FibernacciProducer, sikre boolean moreNumbersToFetch
        at traaden stopper.
        Executor service stopper via loopet i linje 53-56 i ExamPrepWeek1, :
            es.shutdown();        
            try {
                es.awaitTermination(10, TimeUnit.SECONDS);
        
        Executoren stopper ikke naar arbejdet er faerdigt, fordi 
        fibonacciConsumer linje 37:  fibNumber = producedNumbers.poll(10, TimeUnit.SECONDS);
        venter paa at der kommer nye tal at "summe op".

        Naar ventetiden er slut, burde executorService stoppe,
        men af en eller anden grund er det noedvendigt med 
        linje 56: es.awaitTermination(10, TimeUnit.SECONDS);


        

        i FibernacciProducer linje 36-39, faar man en long fra fib funktionen.
        den castes til en int:
                    long fibNumber = fib(number);
                    //Skal castes til Integer
                    Integer fibInt = (int) (long) fibNumber;
                    producedNumbers.put(fibInt);
        Hvorfor det?
        Da fibonacci tal kan blive meget store, og int kun gaar til 
        2,147,483,647, 
        (fib 46 == 1,836,311,903
         fib 47 == 2,971,215,073 )
         betyder det at man ikke faar korrekte vaerdier
         naar man begeregner fibonacci paa tal over 46.

         long gaar til 
         9,223,372,036,854,775,807

        (fib 92 ==  7,540,113,804,746,346,429 
         fib 93 == 12,200,160,415,121,876,738 )
        
         saa men long kan man ikke tage fibbonacci af tal stoerre end 92.
         
         skal man bruge stoerre tal end det, kan man bruge BigInteger 
         der kan vaere lige saa stor som man har ram til.

         
         3)
         Just after the main thread have started the four Producer threads, 
        it should start the Consumer-thread C1, which continuously retrieves the calculated figures, 
        prints them to the console and keep track of the total sum.

        *** kommentar: 
        ------------------

        4)
         When all threads have finished their jobs, print the sum of all the calculated Fibonacci numbers.  
         
         ****kommentar:
         ----------------------

        5)
        Refactor all code in to a method which takes the number of Producer 
        threads to use as an argument. Execute the method with 1, 2, 3, and 4 as argument. 
        Calculate the time it takes for each execution and explain (as well as you can) the result.

        **** kommentar:
        Mangler?


*/  