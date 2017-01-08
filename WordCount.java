/* 
* Counts the occurrences of words in the file (ignoring case). It then reports the
* frequencies using a number provided by the user that limits the output to a certain minimum frequency.
*/

import java.util.*;
import java.io.*;

public class WordCount {
	static int minFrequency = 10;
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "wordCount.txt";
        Scanner input = new Scanner(new File(fileName));

        // count occurrences
        Map<String, Integer> wordCounts = new TreeMap<String, Integer>();
        while (input.hasNext()) {
            String next = input.next().toLowerCase();
            if (!wordCounts.containsKey(next)) {
                wordCounts.put(next, 1);
            } else {
                wordCounts.put(next, wordCounts.get(next) + 1);
            }
        }

        // get cutoff and report frequencies
        System.out.println("Total words = " + wordCounts.size());

        System.out.println("\nSorted Map......By Key");
        Map<String, Integer> treeMap = sortByValue(wordCounts);
        
        printMap(treeMap);
    }
    
    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // Switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static <K, V> void printMap(Map<K, V> map) {
    	int counter = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
        	if((int)entry.getValue() > minFrequency)
        		System.out.println(counter++ +" " + entry.getKey() + " - Count: " + entry.getValue());
        }
    }
}
