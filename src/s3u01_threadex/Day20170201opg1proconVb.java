/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Christian
 * Opgave formuleringen kommer øverst.
 * De beskrivende (tekst) løsninger ligger i bunden af dokumentet.
 */

public class Day20170201opg1proconVb {
    
    private long fib(long n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fib(n-1) + fib(n-2);
        }
    }
    
    
    private Runnable createRunnable(long myNum, ArrayBlockingQueue<Integer> fibNumberQueue){ //final String paramStr
        Runnable myRunnable = new Runnable(){
            public void run(){
                //someFunc(paramStr);   
            }
        };
        return myRunnable;
    }
    
    public void run() throws InterruptedException {
        
        System.out.println("Day20170201opg1proconVb" );
        
        BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<Integer>(12);
        int[] myInts = new int[]{4,5,8,12,21,22,34,35,36,37,42};
        
        for (int i = 0; i < myInts.length; i++) {
            sharedQueue.put(myInts[i]);
        }
        
        Runnable producer = () -> {     
            
            /*
            try {
                while(true){
                    int rndnum = rnd.nextInt();
                    sharedQueue.put(rndnum);
                    
                }
            } catch (InterruptedException ex) {
                System.out.println("der er noget galt i producer");
            }
            */
        };
        
        Thread[] producers = new Thread[4];
        
        for (int i = 0; i < 4; i++) {
            producers[i] = new Thread(producer);          
        }
        
    }
    /*
    System.out.println("S3u01_ThreadEX_2017-02-01_exercise_01_ Exam Preparation Producer/Consumer ");
        
        System.out.println("bullet 1: When and why will we use Threads in our programs? "
                + "\n" + "2 grunde til at bruge tråde:"
                + "\n" + "performance == ting går hurtigere."
                + "\n" + "og responsiveness == man vil gerne kunne f.eks."
                + "\n" + "stoppe en process man har sat igang."
                + "");
        
        System.out.println("bullet 2: What is the Race Condition Problem and how can you solve it? "
                + "\n" + "race kondition == når vores programs udfald afhænger af den rækkefølge "
                + "\n" + "tråde køre i (thread interleavings), og vil ikke vil have det."
                + "\n" + "Lost update er et eksempel på en race kondition."
                + "\n" + "(even number incrementeren er et godt eksempel på lost update - se opgaverne fra 29/1)"
                + "\n"
                + "\n" + "vi løser problemet med race conditions, ved at bruge låse."
                + "\n" + "(det er det vi gør med synchronized keyworded)"
                + "\n");
        
        System.out.println("bullet 3: Explain the Producer/Consumer-problem and how to solve it in modern Java Programs"
                + "\n" + "Man har en eller flere producere der producere data til en kø (queue) (én data enhed af gangen)."
                + "\n" + "Derudover har man en eller flere consumere der tager dataen fra køen, (én data enhed af gangen)."
                + "\n" + "Udfordringen er at forhindre at producerne forsøger at lægge data over i en fuld kø,"
                + "\n" + "og at forhindre at consumerne forsøger at hente data fra en tom kø."
                + ""
                + "\n" + "til at håndtere producer/consumer probelmet kan man bruge,"
                + "\n" + "en implementation af blokingqueue interfaceet. Dette interface understøtter"
                + "\n" + "operationer der venter til køen ikke er tom, når der hentes fra køen,"
                + "<n" + "og venter til køen ikke er fuld, med at lægge data over i køen."
                + ""
                + "\n" + "et andet problem ved producer/consumer"
                + "\n" + "er at man skal finde ud af hvor mange tråde man skal bruge til en opgave."
                + "\n" + "hertil kan man bruge et executer framework (den bestemmer selv hvor mange antal tråde der skal"
                + "\n" + "bruges til en opgave, og laver dem, så vi ikke behøver at gøre det)."
                + "\n" + ""
                + "********************************skriv gerne noget mere her."
                + ""
                + "\n" + "undefviser tip: snak om executer frameworks -- læs hvad jeg kan finde om "
                + "producer consumer" 
                + "\n");
        */
        /*
        bullet 4: Explain what Busy Waiting is and why it's a bad thing in a modern software system. 
        
        hvis man kaller sleep() paa en tråd, f.eks. på 200 millisekunder,
        håber man at en anden tråd hopper ind og overtager t1's
        job i mellemtiden, til de 200 millisekunder er gået.

        busy waiting vil sige at få en tråd til at vente
        uden at indikere til java at en anden tråd 
        skal hoppe ind og overtage.
        Dt gør man typisk med et while loop,
        hvor man bare siger til tråden at den ike skal gøre noget.
        dvs. at tråden bare står og venter
        Det burde ikke ske hvis man kalder sleep/lock eller wait.
        */
        
        /*
        bullet 5: 
        Describe Java's BlockingQueue interface, 
        relevant implementations and methods relevant for the producer consumer problem.
        
        Dette interface understøtter operationer der venter til køen ikke er tom, 
        når der hentes fra køen,
        og venter til køen ikke er fuld, med at lægge data over i køen.
        
        BlockingQueue interfacet har to typer metoder der er særligt vigtige:
        metoder der lægger data over i køen, og metoder der tager data fra køen.
        Begge typer findes i 4 forskellige udgaver, afhængigt af
        hvordan de håndtere producer/consumer probelmet.
        add og remove kaster en exception hvis køen er fuld/tom,
        offer og poll returnere null eller false hvis køen er fuld/tom,
        put og take blokkere operationen, indtil den kan lykke, og 
        offer(e, time, unit) og poll(time, unit) venter en prriode,
        og stopper derefter med at prøve at udføre operationen
        hvis der går for lang tid.
        
        Den implementation af blokingQueue vi har brugt indtil nu hedder
        ArrayBlockingQueue.
        Den bruger et array til at opbevare data og bruger et først-ind-ført-ud princippet.
        Det der ligger først i arrayet er det der kom ind og fjernes først.
        nye elementer lægges ind baggerst i arrayet.        
        */
        
        /*
        
        */
}
