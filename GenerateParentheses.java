package leetcode;

import java.util.ArrayList;
import java.util.HashSet;

public class GenerateParentheses {

	/*
	 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
	 * 
	 * dp
	 * 
	 * n = 1 , ()
	 * n = 2, ()() (())
	 * which means, n+1 = each element from the previsou set, and put "()" + element , element + "()", "(" + element + ")"
	 * 
	 * */
	public static HashSet<String> generateParentheses(int n) {
		if (n < 0) {
			return null;
		}
		ArrayList<HashSet<String>> results = new ArrayList<HashSet<String>>();
		HashSet<String> set = new HashSet<String>();
		set.add("");
		results.add(set);
		
		for (int i = 1; i <= n; i++) {
			HashSet<String> tempSet = new HashSet<String>();
			HashSet<String> previousSet = results.get(i-1);
			for (String s : previousSet) {
				tempSet.add("()" + s);
				tempSet.add(s + "()");
				tempSet.add("(" + s + ")");
			}
			
			results.add(tempSet);
		}
		
		return results.get(n);
	}
	
	public static void main(String[] args) {
		HashSet<String> result = generateParentheses(3);
		for (String s:result) {
			System.out.println(s);
		}
	}
}