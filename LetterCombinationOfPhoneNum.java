package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LetterCombinationOfPhoneNum {
	public static final Map<Character, String> digitLetterMap;	
	static {
		digitLetterMap = new HashMap<Character, String>();
		digitLetterMap.put('0', "");
		digitLetterMap.put('1', "");
		digitLetterMap.put('2', "A");
		digitLetterMap.put('3', "DE");
		digitLetterMap.put('4', "GHI");
		digitLetterMap.put('5', "JKL");
		digitLetterMap.put('6', "MNO");
		digitLetterMap.put('7', "PQRS");
		digitLetterMap.put('8', "TUV");
		digitLetterMap.put('9', "WXYZ");
	}
	
	public static ArrayList<String> letterCombinations(String digits) {
        // Start typing your Java solution below
        // DO NOT write main() function
		ArrayList<String> results = new ArrayList<String>();
		doLetterCombinations(digits, 0, "", results);
		
		return results;
    }
	
	private static void doLetterCombinations (String digits, int cp, String combanation, ArrayList<String> results) {
		if (combanation.length() == digits.length()) {
			results.add(combanation);
		} else {
			for (int i = cp; i < digits.length(); i++) {
				String letters = digitLetterMap.get(digits.charAt(cp));
				for (int j = 0; j < letters.length(); j++) {
					doLetterCombinations(digits, i+1, combanation + letters.charAt(j), results);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ArrayList<String> results = letterCombinations("23");
		for (String result : results) {
			System.out.println(result);
		}
	}
}
