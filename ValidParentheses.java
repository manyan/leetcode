package leetcode;

import java.util.Stack;

public class ValidParentheses {
	public static boolean validParenthese(char[] p) {
		Stack<Character> stack = new Stack<Character>();
		
		for (int i = 0; i < p.length; i++) {
			if (p[i] == '(' || p[i] == '[' || p[i] == '{') {
				stack.push(p[i]);
			} else {
				if (stack.isEmpty()) {
					return false;
				} else {
					if ((p[i] == ')' && stack.pop().charValue() != '(') 
							|| (p[i] == ']' && stack.pop().charValue() != '[') 
							|| (p[i] == '}' && stack.pop().charValue() != '{')) {
						return false;
					}
				}
			}
		}
		
		if (stack.isEmpty()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		char[] p = {'{','[',']','}','(',')','[',']'};
		System.out.println(validParenthese(p));
	}
}
