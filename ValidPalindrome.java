package leetcode;

public class ValidPalindrome {
	public static boolean isValidPalindrome(String source) {
		if (source == null || source.length() <= 1) {
			return true;
		}
		
		char[] s = source.toCharArray();
		boolean[][] matrix = new boolean[s.length][s.length];
		
		// init matrix[i][j] = true, if i >= j , single char or empty char is already a palindrome
		// if char[i][j] i <= j is a palindrome, lets say char[i][j] = P
		// xPy is palindrome only if x == y
		// so we need to start from the shortest substring, then extend to a larger one
		for (int length = 1; length <= s.length; length++) {
			for (int i = 0; i < s.length - length; i++) {
				int j = i + length;
				if (s[i] == s[j]) {
					if (i+1 >= j-1 || matrix[i+1][j-1]) {
						matrix[i][j] = true;
					} else {
						matrix[i][j] = false;
					}
				} else {
					matrix[i][j] = false;
				}
			}
		}
		
		return matrix[0][s.length-1];
	}
	
	public static void main(String[] args) {
		System.out.println(isValidPalindrome("ollo"));
	}
}
