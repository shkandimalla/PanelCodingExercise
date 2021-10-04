package com.darklantha;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class App 
{
    public static void main( String[] args )
    {
        Processor processor = new Processor();
        try {
            System.out.println("Start of file processing...");
            long startTime = System.nanoTime();
            processor.process(new ClassPathResource("data").getFile());
            long endTime = System.nanoTime();
            System.out.println("End of file processing... duration = "+(endTime - startTime)/1000000 +" s");

            System.out.println("Start of Word counting Sorting and printing...");
            startTime = System.nanoTime();
            processor.printWordsInDescendingOrderOfFrequency();
            endTime = System.nanoTime();
            System.out.println("End of Word counting Sorting and printing... duration = "+(endTime - startTime)/1000000 +" s");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
