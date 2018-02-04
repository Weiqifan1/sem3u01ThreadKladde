/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s3u01_threadex;

import java.io.IOException;
//import org.jsoup.Jsoup; //      undervisers bud:
                        //fejlen sker fordi programmet kun kan se jsoup via pom filen
                        // og pom filer findes kun i maven projekter. Mit projekt
                        // skal derfor være et maven projekt for at jsoup skal læses
                        // (det er dog mærkeligt at der ikke er en dependensies mappe. det burde der være)
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;

public class TagCounter {
  String url;
  String title;
  int h1Count,h2Count, divCount, bodyCount;
  TagCounter(String url){
    this.url = url;
  }
  
  
  //Connect to the URL and count the number of h1, h2, div and body Tags
  /*
  public void run(){
    Document doc;
    try {
      doc = Jsoup.connect(url).get();
      // get page title
      this.title = doc.title();
      Elements h1s = doc.select("h1");
      this.h1Count = h1s.size();
      Elements h2s = doc.select("h2");
      this.h2Count = h2s.size();
      Elements divs = doc.select("div");
      this.divCount = divs.size();
      Elements bodys = doc.select("body");
      this.bodyCount = bodys.size();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
    */
  public String getTitle() {
    return title;
  }

  public int getH1Count() {
    return h1Count;
  }

  public int getH2Count() {
    return h2Count;
  }

  public int getDivCount() {
    return divCount;
  }

  public int getBodyCount() {
    return bodyCount;
  }
  
}
