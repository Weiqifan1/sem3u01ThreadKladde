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
Exercise 3 
This exercise is marked as Green because of the amount of provided code and hints. 
At the exam, you could be asked to complete something similar, with none or very little start code. 
In order to understand this exercise, you must have completed exercise-1 given above.

Exercise 1 can easily be improved to be much more generic. 
This can be done in several ways, here we will use a Producer-Consumer solution using a BlockingQueue implementation.
We will change exercise-1 as follows:

* Instead of the hardcoded 3 URL’s we will introduce a Queue (Q1 below) 
    which initially should include all the url’s we want to use. 
* The TagCounter class in exercise-1 did two things. 
    It fetched the remote page and created a Document instance from the URL. After that it read the tags from the Document as sketched below:
    //Get a Document instance representing the web-page
        doc = Jsoup.connect(url).get();
    // Do a lot of “stuff” with the doc
* We will change this in a way where you should start four Producer threads (why four), 
    which should should retrieve, and remove, a url-string from Q1, 
    use the Jsoup-library to fetch and create a Document and put this Document into Q2. 
    If Q1 is empty (no more urls) the thread should terminate.
* Yet another thread C1 (the Consumer) should take the Documents from Q2, 
    read the title, and the number of Divs. When all Documents are read, and not before,  
    it should print: all titles+the number of divs for each title, followed by the sum of all div’s

a) Execute the Tester code in day2.webscrapprodcon. It should compile and run, but don’t do “anything”. Make sure you understand how the provided code maps to the diagram and text above.

b) Complete the class DocumentProducer (look for the TODO’s). Again; first make sure you understand it’s purpose in the descriptions given above.

c) Complete the class DocumentConsumer (look for the TODO’s)

d) Note the order in which the consumer prints the results.
Is this the order, in which we added the URL’s to Q1?
If not, explain why
If not, can you print results in the right order?
Day-3 will provide you a way to “return” values from your threads and control the order

e)
Step c) gave you a hint of how to let the consumer could detect that all producers were done.
This was not very efficient however, since we always would have to wait 10 (unnecessary) seconds before we are done.
Come up with a way (for example via a shared and synchronized object) to let the Producers indicate that there are no more items to produce.

*/

public class Day20170130opg03 {
    
}
