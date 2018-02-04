/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

import java.util.ArrayList;

/**
 *
 * @author Christian
 */

/*
1) 
Start by just reading over the code, 
don’t change the code yet but think about what data/variables 
might be shared between threads calling our methods, 
what data may be changed or only read by different threads. 

forskellige traade der kalder metoderne paa den samme instans af klassen,
deler variablerne stack og size.

hvis flere traade arbejder paa:

metoden add():
da metoden laaser paa stack variablen, er traadene fri til at aendre paa stack objektet uden at 
vente paa hinanden. Det betyder at stack og size kan aendres af foeskellige traade 
samtidig, og dermed svare size ikke altid til stackens faktiske laengde. 
at laase paa stack har derfor kun den effekt at 2 traade ikke kan laegge noget til stack 
samtidig. 

Hvis oensket er at hvert traad skal vaere faerid med deres operation 
(tilfoeje et tal stil stack OG opdatere size) kan man loese problemet ved 
enten at udskifte 
"public void add(T e) { synchronized(stack){"
med 
"public void add(T e) { synchronized(this){"
eller med
"public synchronized void add(T e) {"

dog vil traadene stadig interlieve naar de laegger tallene ind i stacken, 
saa tallene vil sta i tilfaeldig raekkefoelge i stacken.

metoden remove():
her laases paa det element der ligger paa den sidste index plads i stack.
lige som i "public void add(T e) { synchronized(stack){", 
er der en riciko for at size og og stack ikke aendres samtidig, da det der laases paa
(et element i stack) ikke medfoere at stack og size begge laases.


metoden peek():
peek laaser paa this. Dermed laaaser den paa instansen af klassen (hvis man ikke har brugt static keyworded
naar man bruger klassen). Hvis flere traade bruger peek paa den samme instans, vil de derfor vente paa hinanden.


*/

/*
2)
The following lists some considerations for you that may or may not be actual problems. 
If you think you encounter a problem, fix it. 
 * Consider the ​add​, ​remove​, and ​peek​ methods. 
    Cyril has made sure they take locks, but is that sufficient is it a problem 
    that they do not lock on the same object, or is it enough that they all take 
    locks from inside the ConcurrentStack class? From inside the ArrayList? Argue your answer. 

som naevnt er det et problem at de ikke logger pa alt som metoden afh;nger af,
dvs. baade size og stack variablen. For at laase paa dem beege, kan som naevnt ovenfor laase paa this.

* The ​peek​ method does not actually mutate the data it operates on (it doesn’t write to the shared data, it only reads). Do we need to lock as Cyril does, or would visibility of that data be enough to make it threadsafe? Could we remove the ​syncronized 
​ statement if we made ​stack​ and ​size​ ​volatile? 
​ Argue your answer. 

formaalet med peek er at kigge paa det sidste element i stack, og 
anvender size for at finde det. Dermed afhaenger peek af baade size og stack.
(size skal svare til antallet af elementer i stack).

for at peek metoden skal give et korrekt resultat, er det derfor noedvendigt 
alle traade der er igang med at lave om paa size og stack, er koert faerdige,
saadan at size passer til stack.

hvis vi bare laver size og stack volatile, kan vi ricikere at en traad
bruger peek mens en anden traad er i gang med at aendre size og stack,
(en traad har taget laaasen fra add() eller remove() og vores peek traad
respektere ikke laasen, da peek() ikke tager en laas)
og vi ricikere at hente stack og size paa tidspunkter hvor de ikke er opdateret med hinanden.

Vi er derfor noedt til at laase peek ved at laase paa hele objektinstansen,
enten ved at laase paa this, eller ved at skrive "public synchronized T peek()". 
Derved sikre vi at naar vi bruger peek, er der ingen andre traade der samtidig er i gang med 
add() eller remove().

af ovenstaaende grund vil det ikke hjaelpe at goere 
size og stack volatile. 


*/

public class opgConstack<T> {
	private final ArrayList<T> stack;
	private int size;

	public opgConstack(){
		size=0;
		stack = new ArrayList<T>(size);
	}

         //2017-02-02 chr return size
        public int getSize() {
            return size;
        }        
        
        //2017-02-02 chr toString metode
        public String printStack() {
            String output = "";
            
            for (int i = 0; i < stack.size(); i++) {
                output += stack.get(i) + " ";
            }
            
            return output;
        }
        
        //nedenstaaende boer vaere synchronized for at faa det resultat vi (formentlig) gerne vil have.
	public void add(T e) {
		synchronized(this){//stack
			stack.add(e);
			size++;
                        System.out.println("input: size: "+ size +
                    " stack: " + this.printStack() + " placeInMemoty: " + stack);
		}
	}

	public T remove() {//T remove() {
		synchronized(this) {//stack.get(size-1)
			if (size > 0) {
                                //stack.remove(--size);
				return stack.remove(--size);   
                                //System.out.println("remove: size: "+ size + " stack: " + this.printStack());
                                
			} else {
				return null;
			}
		}
	}

	public T peek() {
		synchronized(this){
			int lastIndex=size-1;
			return stack.get(lastIndex);
		}
	}

	//Example usage of OneValueCache
	public static void main(String[] args) {
                                       
		opgConstack<Integer> stack = new opgConstack<>();
                
                //lav en raekke traade der laver samme operation.
                
                
                        
		for(int i=0;i<10;i++) {
			stack.add(i);
                        System.out.println("input: " + i);
		}
		for(int i=0;i<10;i++) {
			if (stack.peek() != null) {
				//System.out.println("removed: " + stack.remove());
                                System.out.println("removed: " + stack.printStack());
			}
		}
	}
}