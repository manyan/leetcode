package leetcode;

public class LongestSubStringWithoutDeplicatedChar {
	public static int getLongestSubStringWithoutDeplicatedChar(char[] source) {
		if (source == null)
			return -1;
		
		if (source.length <= 1)
			return source.length;
		
		int start = 0;
		int end = 0;
		int max = 0;
		int[] existTable = new int[256];
		
		while (end < source.length) {
			if (existTable[source[end]] == 0) {
				existTable[source[end]] = 1;
				end++;
			} else {
				// there is a duplicate char at source[j]
				// so source[start....end-1] might be the max
				max = Math.max(end - start, max);
				// trim from the start
				while (source[start] != source[end]) {
					existTable[source[start]] = 0;
					start++;
				}
				// at this point, source[start] == source[end];
				start++;
				end++;
			}
		}
		
		return Math.max(max, end-start);
	}
	
	public static void main(String[] args) {
		String s = "abcddcbae";
		char[] source = s.toCharArray();
		System.out.println(getLongestSubStringWithoutDeplicatedChar(source));
	}
}
