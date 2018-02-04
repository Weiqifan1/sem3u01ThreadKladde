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
public class Day20170130_opg5 {
    public void run() {
        System.out.println("Day20170130opg05:\n");
        
        //klasser der bruges af denne opgave: 
        //TesterDag2Opg5.java
        //ResourceUser1.java
        //ResourceUser2.java
        //ResourceContainer.java
        //DeadLockDetector
        
        
        // a)
        /*
        Det man oensker af programmet er at de 2 traade uhindret kan laegge nye navne
        ind i words arrayListen og numre ind i numbers arrayListen. De skal kunne goere det 
        saadan at hvis traad 1 arbejder paa numbers, saa maa traad ikke samtidig arbejde paa numbers,
        men maa gerne arbejde paa words og vise versa. 
        
        Det der sker er at traad 1 starter med at arbejde paa words. Operationen tager lang tid 
        (over 1 millisekund, simuleret ved Thread.sleep(1)).
        omtent samtidig arbejder traad 2 med Numbers. denne operation tager ogsaa over 1 milllisekund.
        
        Naar traad et er faerdig med words, oensker den at arbejde med numbers, men uden at frigive 
        words noegle objektet foer efter den har arbejdet med words.
        
        naar traad 2 er faerdig med numbers, oensker den modsat at begynde at arbejde med words, 
        uden at frigive numbers noeglen.
        Begge traad venter saa paa den noegle den anden traad ikke vil frigive.                      
        
        */
        
        //a.c)
        /*
        i traad 1, tages word arrayListens noegle ved linje: 
        "List<String> words = resource.getResourceWords();"
        
        ved "List<Integer> numbers = resource.getResourceNumbers();"
        for soeger traad 1 at tage numbers noeglen.
        
        Foerst efter traad 1 har arbejdet faerdig med baade words og numbers,
        frigiver den begge noegler ved linjerne:
        "
        resource.releaseResourceNumbers();
        resource.releaseResourceWords();
        "
        Traad 2 goer det samme i modsat raekkefoelge.
        
        */
        
        //b)
        /*
        
        DeadLockDetector.java er lavet og er udvidddet til at kunne detectere 
        deadlock og printe en fejlbesked.
        
        */
        
        //c.1)
        
        /*
        se beskrivelsen i spoergsmaal a og spoergsmaal a.c
        
        */
        
        //c.2)
        
        /*
        problemet kan loese ved at at opdele koden
        i de to traade i en word - del og en numbers -del.
        Naar f.eks. worddelen er afsluttet, skal traaden frigive den tilhoerende laas,
        til brug for den anden traad.
        
        (se aendringerne til Ressourceuser 1 og Ressourceuser2).
        */
        
    }
}
