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
//public class opgConstackRunnable {
//}
public class OpgConstackThread extends Thread {

    private opgConstack<Integer> stack;
    private final int ID;

    public OpgConstackThread(opgConstack<Integer> stack, int ID) {
        this.stack = stack;
        this.ID = ID;
    }
    
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            stack.add(i);
            //System.out.println("thread "+ ID +": input: " + i + " size: "+stack.getSize()+
            //        " stack: " + stack.printStack() + " placeInMemoty: " + stack);
            //System.out.println("    ID "+ID + " add: stack: " + stack);
        }
        for (int i = 0; i < 10; i++) {
            if (stack.peek() != null) {
                stack.remove();
                //System.out.println("thread "+ ID +": removed: " + stack.remove() + " size: "+stack.getSize()+
                //        " stack: " + stack.printStack() + " placeInMemoty: " + stack);
                //System.out.println("    ID "+ID+ " remove: stack: " + stack);//+ "stack: " + stack
            }
        }

    }
}
