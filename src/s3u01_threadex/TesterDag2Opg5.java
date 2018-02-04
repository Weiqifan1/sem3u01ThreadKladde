package s3u01_threadex;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TesterDag2Opg5 {
  public static void main(String[] args) { 
    
    
    try {
      ResourceContainer resources = new ResourceContainer();
      ResourceUser1 t1 = new ResourceUser1(resources);
      ResourceUser2 t2 = new ResourceUser2(resources);
      DeadLockDetector dd = new DeadLockDetector(new Thread[]{t1,t2});
      
      t1.start();
      t2.start();
      
      dd.run();  
      
      t1.join();
      t2.join();
      
      System.out.println("Done");
      System.out.println("Words produced: "+resources.getResourceWords().size());
      System.out.println("Numbers produced: "+resources.getResourceNumbers().size());
    } catch (InterruptedException ex) {
      Logger.getLogger(TesterDag2Opg5.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
