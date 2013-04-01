package leetcode;

/*
 * Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") ? false
isMatch("aa","aa") ? true
isMatch("aaa","aa") ? false
isMatch("aa", "*") ? true
isMatch("aa", "a*") ? true
isMatch("ab", "?*") ? true
isMatch("aab", "c*a*b") ? false
 * */
public class WildcardMatching {	
	/**
	 * dp: eg: source = abcabc, pattern: *ca*c
	 *   * c a * c
	 * a 1 0 0 0 0
	 * b 1 0 0 0 0
	 * c 1 1 0 0 0
	 * a 1 0 1 1 0
	 * b 1 0 0 1 0
	 * c 1 1 0 1 1
	 * 
	 * matrix[i][j] 表示source[0...i] 和 pattern[0...j]的匹配情况
	 * eg: matrix[2][1] = 1(true) 即abc 和 *c的匹配情况
	 * matrix[2][2] = 0 (false),即abc 和 *ca的匹配情况
	 * 所以我们从左往右, 从上到下的填这个matrix, 最后matrix[source.length-1][pattern.length-1]的值就是匹配的最终结果
	 * 为了方便填表, 我们可以先在source 和 pattern的前头个加一个" "(space), 这样matrix[0][0] = true, matrix[0][i] = false ( 1 <= i <= source.legnth, 因为多加了一个字符, 所有最后一个是source.length)
	 * 在开始填写某一列时, 从前面一列的值种开始检查, 前一列第一个为1的位置记为lastTruePosition开始往下写, 如果当pattern的字符是*, 则当前列从lastTruePosition到最后一个位置都是1
	 * 如果当前字符不是*, 从source[lastTruePosition]开始找, 如果source[i] == pattern[j] 同时前一列的i-1是true, 则当前列i位置是true, 否则为false   
	 * 从填表可以知道, 我们只需要知道当前一列就是判断出当前列的状态, 所以, 其实我们不需要一个matrix, 我们只需要两列
	 * 再缩小一下空间, 如果我们从下往上填表, (即不会覆盖前一列的前一个状态) 所以我们只需要一个列 (或者说已经退化到一行)来代表
	 * 而这一行的初始化是
	 * boolean[] matrix = new int[source.length];
	 * matrix[0] = true;
	 * 
	 */
	
	public static boolean isMatchDP(String source, String pattern) {
		if (source == null || pattern == null) {
			return false;
		}
		
		if (source.equals("")) {
			for (int j = 0; j < pattern.length(); j++) {
				if (pattern.charAt(j) != '*')
					return false;
			}
			return true;
		}
		
		boolean[] result = new boolean[source.length()];
		result[0] = true;
		int lastTruePosition = 0;
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) == '*') {
				for (int j = lastTruePosition; j < source.length(); j++) {
					result[j] = true;
				}
			} else {
				// fill current result
				int currentLastTrueP = Integer.MAX_VALUE;
				for (int j = source.length()-1; j >= (lastTruePosition >= 1? lastTruePosition : 1); j--) {
					if (result[j-1] && (pattern.charAt(i) == source.charAt(j) || pattern.charAt(i) == '?')) {
						result[j] = true;
						if (j < currentLastTrueP) {
							currentLastTrueP = j;
						}
					} else {
						result[j] = false;
					}
				}
				
				if (currentLastTrueP == Integer.MAX_VALUE) {
					return false;
				}
				
				lastTruePosition = currentLastTrueP;
			}
		}
		
		return result[source.length()-1];
	}
	
	
	/*
	 * 类似kpm的思想, 就是pattern的指针不回溯到最前面, 而是退回到上次"*"的位置+1
	 * 如果匹配失败, 把source的指针回溯到上次遇到pattern 上的"*"时候的位置+1
	 * 
	 * */
	public static boolean isMatchGreedy(String source, String pattern) {
		if (source == null || pattern == null) {
			return false;
		}
		
		if (source.length() == 0) {
			return pattern.length() == 0;
		}
		
		int i = 0; // pointer of source
		int j = 0; // pointer of patter
		int star = -1; // "*" position
		int sp = 0; // key of the solution: sp is the start position in source , and i is the current position of source when we trying to match a wild card "*"
		// so if there is no "*", sp will always 0, otherwise we will need to update it, when greedy match fail
		
		while (i < source.length()) {
			// one * and mutilpal continuous * are the same
			while (j < pattern.length() && pattern.charAt(j) == '*') {
				// log the last position of last "*"
				// call it last position is because there might be mutil continuous '*'
				// one * and mutil continuous '*' are the same
				star = j++;
				sp = i;
			}
			
			if (j == pattern.length() || pattern.charAt(j) != source.charAt(i)) {
				if (star < 0) {
					return false;
				} else {
					j = star+1;
					i = ++sp;
				}
			} else {
				i++;
				j++;
			}
		}
		
		while (j < pattern.length() && pattern.charAt(j) == '*') {
			j++;
		}
		
		return j == pattern.length();
	}
	
	public static void main(String[] args) {
		String source = "a";
		String pattern = "?*";
		
		System.out.println(isMatchDP(source, pattern));
	}
}
