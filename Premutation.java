package leetcode;

import java.util.ArrayList;

/*
 * eg: char[] s = {1,2,3};
 * return:
 * 123
 * 132
 * 213
 * 231
 * 312
 * 321
 * 
 * */
public class Premutation {
	public static ArrayList<String> premutation(char[] s) {
		ArrayList<String> results = new ArrayList<String>();
		getPremutation(s,0,results);
		return results;
	}
	
	private static void getPremutation(char[] s, int cp, ArrayList<String> results) {
		if (cp == s.length) {
			results.add(String.valueOf(s));
		} else {
			for (int i = cp; i < s.length;i++) {
				char temp = s[i];
				s[i] = s[cp];
				s[cp] = temp;
				
				getPremutation(s, cp+1, results);
				
				temp = s[i];
				s[i] = s[cp];
				s[cp] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		char[] s = {'1','2','3'};
		ArrayList<String> results = premutation(s);
		for(String result : results) {
			System.out.println(result);
		}
	}
}
