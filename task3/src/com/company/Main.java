//+This task is about processing a text file and do some statistics on it. You have to print on the console the following data about the file:
//	- number of sentences
//	- which is the longest sentence (print it) and how many words it has.
//	- which are the 10 most used words in the file - print them with the number of their appearances.
//	- number of unique words and which are they.
//
//The file is a simple text. The words are separated by spaces or comas. The sentences are separated by points (.), questions marks (?) or exclamation marks (!)
//
//For this task " and ' can be ignored.

package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    public static void main(String[] args) throws Exception {

        LinkedHashMap<String, Integer> fjalet = new LinkedHashMap<>();
        int nrOfSentences = 0;
        int nrOfWords = 0;
        int maxChars = 0;
        int maxCharsEver = 0;
        int maxNrOfWords = 0;
        String fjala = "";
        String fjalia = "";
        String longestSentence = "";
        FileReader fr =
                new FileReader("C:\\Users\\User\\Desktop\\data\\TheGreenDoor.txt");
        int i;
        while ((i = fr.read()) != -1) {

            if (i == '.' || i == '!' || i == '?') {
                if (maxChars >= maxCharsEver) {
                    maxCharsEver = maxChars;
                    maxNrOfWords = nrOfWords;
                    longestSentence = fjalia;
                }
                nrOfSentences++;
                fjalia = "";
                maxChars = 0;
                nrOfWords = 0;
            }
            else {
                maxChars++;
                fjalia += (char) i;
            }

            if (i == ' ' || i == ',' || i=='.') {
                nrOfWords++;
                if (fjalet.get(fjala) == null) {
                    fjalet.put(fjala, 1);
                } else {
                    int nr = fjalet.get(fjala);
                    fjalet.remove(fjala);
                    fjalet.put(fjala, ++nr);
                }
                fjala = "";
            }
            else {
                fjala += (char) i;
            }

        }
        System.out.println("Teksti ka "+nrOfSentences + " fjali.");
        System.out.println("Fjalia me e gjate eshte "+ longestSentence);
        System.out.println("Kjo fjali ka "+ maxCharsEver+ " karaktere");
        System.out.println("Kjo fjali ka "+ maxNrOfWords+ " fjale");

        Map<String, Integer> hm1 = sortByValue(fjalet);

        int printuar=0;
        System.out.println("Dhjete fjalet me te perdourura");
        for (Map.Entry<String, Integer> en : hm1.entrySet()) {
            printuar++;
            System.out.println("Fjala " + en.getKey() +
                    ", Perseritur " + en.getValue()+ " here");
            if(printuar==10) break;
        }
        int nrFjaleUnike=0;

        System.out.println("Fjalet unike");
        for (String j : fjalet.keySet()) {
            if(fjalet.get(j)==1)
            {System.out.print(j+",");
            nrFjaleUnike++;
            }
        }
        System.out.println("Jane ne tekst "+ nrFjaleUnike+ " fjale unike.");


    }


}
