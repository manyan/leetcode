package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Given an array of strings, return all groups of strings that are anagrams.

 Note: All inputs will be in lower-case.

 eg:
 Anagrams have the same character counts mapping: “ate”, “eat” and “tea” have the same mapping: {‘a’:1, ‘e’:1, ‘t’:1}. 
 So for each string in the group, we treat it as a mapping between chars to ints. 
 If there are  mappings that are the same, we know there are  anagrams. 
 Therefore our algorithm works this way: in the 1st scan of the group, we build two hash-table: for each string, 
 we connect it with its character counts mapping (this is for later quick reference); 
 for each character counts mapping, we accumulate its count. At the 2nd scan of the group, 
 for each string, we use the 1st hash-table to quickly find its character counts mapping. 
 When we get the mapping, we plug it into the second hash-table and fetch its count, 
 if the count is greater than 1, we know this string is a member of the anagrams so we add it to the solution array. 
 Hence it’s a O(n) algorithm.
 * 
 * 
 * */
public class Anagrams {
	public static ArrayList<String> anagrams(String[] strs) {
		HashMap<String, HashMap<Character, Integer>> strToCharCount = new HashMap<String, HashMap<Character, Integer>>();
		HashMap<HashMap<Character, Integer>, Integer> charCountToCount = new HashMap<HashMap<Character, Integer>, Integer>();
		for (String str : strs) {
			HashMap<Character, Integer> charToCount = new HashMap<Character, Integer>();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (charToCount.containsKey(c)) {
					charToCount.put(c, charToCount.get(c) + 1);
				} else {
					charToCount.put(c, 1);
				}
			}
			
			strToCharCount.put(str, charToCount);
			if (charCountToCount.containsKey(charToCount)) {
				charCountToCount.put(charToCount, charCountToCount.get(charToCount)+1);
			} else {
				charCountToCount.put(charToCount, 1);
			}
		}
		ArrayList<String> result = new ArrayList<String>();
		for (String str : strs) {
			if (charCountToCount.get(strToCharCount.get(str)) > 1) {
				result.add(str);
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		HashMap<Character, Integer> chars1 = new HashMap<Character, Integer>();
		chars1.put('a', 1);
		chars1.put('b', 1);

		HashMap<Character, Integer> chars2 = new HashMap<Character, Integer>();
		chars2.put('b', 1);
		chars2.put('a', 1);

		HashMap<HashMap<Character, Integer>, Integer> results = new HashMap<HashMap<Character, Integer>, Integer>();
		results.put(chars1, 1);
		System.out.println(results.containsKey(chars2));
	}
}
