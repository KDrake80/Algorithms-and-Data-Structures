/*
 * Kevin Drake
 * 2/16/22
 * This Counts the occurrence of words. Then stores them into an ArrayList. Then sorts the arraylist
 *  and displays the occurence from lowest to highest
 */
import java.util.*;
public class CountOccurrenceOfWords {
	public static void main(String[] args) {
		// Set text in a string
		String text = "Good morning. Have a good class. " +
				"Have a good visit. Have fun!";
		// Create a TreeMap to hold words as key and count as value
		Map<String, Integer> map = new TreeMap<>();
		String[] words = text.split("[\\s+\\p{P}]");
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();
			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				}
				else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}
		// Display key and value for each entry
		map.forEach((k, v) -> System.out.println(k + "\t" + v));
		System.out.println();
		
		List<WordOccurrence> list = new ArrayList<>();
		map.forEach((k, v) -> {
			WordOccurrence w = new WordOccurrence(k, v);
			list.add(w);
		});
		Collections.sort(list);
		list.forEach(e -> System.out.print(e.getWord() + " " + e.getCount() + " "));
		/*
		 *  If you tried to use the Sort method on the TreeSet it would throw a UnsupportedOperationException
		 */
	}
}
class WordOccurrence implements Comparable<WordOccurrence> {
	private String word;
	private Integer count;
	
	public WordOccurrence(String w, Integer c) {
		word = w;
		count = c;
	}
	public int getCount() {
		return count;
	}
	public String getWord() {
		return word;
	}

	@Override
	public int compareTo(WordOccurrence o) {
		if (count > o.getCount())
		return 1;
		
		else if (count == o.count)
			return 0;
		
		else
			return -1;
	}
}