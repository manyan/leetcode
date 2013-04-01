package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*
 * You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.

For example, given:
S: "barfoothefoobarman"
L: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
 * */

/*
 * idea: from each point of S, we detect whether it contains every worlds of L
 * kind of brute force
 * */
public class SubstringWithConcatenationOfAllWords {
	public static ArrayList<Integer> substringWithConcatenationOfAllWords (String source, String[] patterns) {
		ArrayList<Integer> results = new ArrayList<Integer>();
		if (source == null || source.length() == 0) {
			return results;
		}
		
		if (patterns == null || patterns.length == 0) {
			// return 0 - source.lenght-1
			for (int i = 0; i < source.length(); i++) {
				results.add(i);
			}
			return results;
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>(); // map each String from patterns to the count
		Map<String, Integer> tempMap = new HashMap<String, Integer>();
		int wordL = patterns[0].length();
		int totalL = wordL*patterns.length;
		
		if (source.length() < totalL) {
			return results;
		}
		
		// init map
		for (String s : patterns) {
			if (map.containsKey(s)) {
				map.put(s, map.get(s)+1);
			} else {
				map.put(s, 1);
			}
		}
		for (int i = 0; i <= source.length()-totalL;i++) {
			tempMap.clear();
			for (int j = i; j<= i+totalL-wordL;j = j + wordL) {
				String word = source.substring(j,j+wordL);
				if (!map.containsKey(word)) {
					break;
				} else {
					if (tempMap.containsKey(word)) {
						tempMap.put(word, tempMap.get(word)+1);
					} else {
						tempMap.put(word, 1);
					}
					
					if (tempMap.get(word) > map.get(word)) {
						break;
					}
					
					if (j == i+totalL-wordL) {
						results.add(i);
					}
				}
			}
		}
		
		return results;
	}
	
	public static void main(String[] args) {
		String[] patterns = {"foo","bar"};
		String source = "barfoothefoobarman";
		ArrayList<Integer> results = substringWithConcatenationOfAllWords(source, patterns);
		
		for (Integer i : results) {
			System.out.println(i);
		}
	}
}
