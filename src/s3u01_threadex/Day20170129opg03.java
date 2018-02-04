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
Exercise 3 (race condition)
In your own words, explain the difference between the two java keywords “synchronized” and “volatile”.

*/
public class Day20170129opg03 {
    public void run() {
        System.out.println(
                "the keyword synchronized: "
                        + ""
                        + "\n kilde: https://www.tutorialspoint.com/java/java_thread_synchronization.htm"
                        + "\n "
                        + "\n synchronization, i form af en synchronized(objectidentifier) -statement,"
                        + "\n sikre at kun en traad af gangen kan arbejde med objektet. Dette sker ved at objektets"
                        + "\n monitor's (alle objekter har en monitor tilknyttet) laas, er laast naar en traad"
                        + "\n anvender objektet. Naar traaden er faerdig med objektet, vil monitoren laas"
                        + "\n blive laast op, og et andet objekt kan saa aendre paa objektet, hvorved "
                        + "\n laaas igen vil blive laast."
                        + "\n"
                        + "\n at objektet's laas bliver laast kaldes ogsaa at objektet bliver blokkeret (block)"
        
        
        );
        
        System.out.println();
        
        System.out.println(
                "the keyword volatile: "
                        + ""
                        + "\n https://stackoverflow.com/questions/3519664/difference-between-volatile-and-synchronized-in-java/"
                        + "\n"
                        + "\n at en variabel bliver erklaeret som volatil, betyder at"
                        + "\n hvis en traad arbejder med variablen, saa er det ikke nok at variablen opdateres i"
                        + "\n processorens cache, fordi en anden traaad i en anden processor saa ikke vil kunne se den."
                        + "\n Derfor vil variablen blive gemt i rammen, og naar en traad oensker at laese eller overskrive"
                        + "\n variablen, gaar traaden altid over i rammen istedet for i dens processors cache."
                        + "\n Modsat synchronized, sikre volatile ikke at to traade kun"
                        + "\n kan arbejder atomart med en variabel. Man kan derfor stadig ricikere "
                        + "\n lost update fejl. hvor en traads aendringer af variablen "
                        + "\n utilsigtet bliver overskrevet af en anden traads aendringer."
                        + "\n"
                        + "\n volatile er nyttigt, hvis det er vigtigt at andre traade kan se den korrekte "
                        + "\n (nuvaerende) vaerdi af en variabel, men det ikke er vigtigt at "
                        + "\n beskytte variablen mod at blive brugt af flere traade paa en gang."
                        + ""
                        + ""
        
        
        );
    }
            
}
