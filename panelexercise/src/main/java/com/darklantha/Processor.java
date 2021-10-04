package com.darklantha;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.stream.Collectors.toMap;

@Component
public class Processor {
    Map<String, Long> hmap;
    Processor() {
       hmap = new HashMap<>();
    }

    /** Read Data from files and Calculate Word Frequency **/
    public void process(File directory) {

        for (File file : directory.listFiles()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] words = line.split("\\s+");

                    for(String str : words) {
                        str = str.toLowerCase();
                        /** Removing special character Word Endings **/
                        if(str.endsWith(",")||str.endsWith(".")) {
                            str = str.substring(0, str.length() - 1);
                        }

                        /** After truncating special character checking for empty string **/
                        if(!str.isEmpty())
                            hmap.put(str, hmap.getOrDefault(str, 0L) + 1);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void printWordsInDescendingOrderOfFrequency() {

        Map<String, Long> sorted = hmap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        sorted.entrySet().stream().forEach(
                e -> System.out.println(e.getValue() +"  "+e.getKey())
        );
    }
}
