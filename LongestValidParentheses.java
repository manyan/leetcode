package leetcode;

import java.util.Stack;


/*
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

sulution: similar to detect whether Parentheses are valid
we use a stack to keep track of Parentheses, we need 2 params: lastValidIndex(the leftest index of '(' of a valid Parentheses), max
1) if we meet "(", push it into stack
2) if we meet ")", 
if the current stack is empty, lastValidIndex = i + 1; because there is not "(" before the current ")", and i is the index of current ")", so the next potential valid start of Parentheses is i+1
if the stack is not empty, pop one element out of stack and store it to tempIndex, and this time, if the stack is already empty, max = i - lastValidIndex
if the stack is not empty after poping, max = i - tempIndex

so actually
lastIndex is used to handle : ()() (becuase we pop it out, if there is no lastIndex, we will lose track)
tempIndex is used to handle (()) (still keep track of everything)
 * */
public class LongestValidParentheses {
	public static int longestValidParentheses(String s) {
		char[] c = s.toCharArray();
		Stack<Integer> stack = new Stack<Integer>();
		int lastValidIndex = 0;
		int max = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '(') {
				stack.push(i);
			} else {
				if (stack.isEmpty()) {
					lastValidIndex = i+1;
				} else {
					stack.pop().intValue();
					if (stack.isEmpty()) { // if its alrady empty, can be this case ()()(), so we need to calculate the sum with lastValidIndex
						max = (i-lastValidIndex+1) > max ? (i-lastValidIndex+1) : max;
					} else { // if its not empty, can be (()(), lets say current i is 4(last element), stack.peek == "last valid element"(which is index 1 in this case) - 1
						// so the max should be i - "last valid element" + 1 , which means max = i - stack.peek
						// the peek is always "last valid element"(which is index 1 in this case) - 1, can be proved by contradition
						// say peek is not "last valid element" -1, it could be "last valid element" - 2, so in this case "last valid element"  - 1 can only be ')', which will pop 
						// "last valid element" - 2 out, contraidtion
						max = (i-stack.peek()) > max ? (i-stack.peek()) : max;
					}
				}
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		System.out.println(longestValidParentheses("(()()"));
	}
}
