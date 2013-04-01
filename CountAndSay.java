package leetcode;

/*
 * The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
 * 
 * */
public class CountAndSay {
	public static String countAndSay(int n) {
		String result = "1";
		
		if (n == 1)
			return result;

		StringBuilder sb = null;
		for (int i = 1; i < n; i++) {
			sb = new StringBuilder();
			char currentChar = result.toCharArray()[0];
			int counter = 1;
			for (int j = 1; j < result.toCharArray().length; j++) {
				if (currentChar == result.toCharArray()[j]) {
					counter++;
				} else {
					sb.append(counter + "" + currentChar);
					currentChar = result.toCharArray()[j];
					counter = 1;
				}
			}
			sb.append(counter + "" + currentChar);
			result = sb.toString();
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		for (int i = 2; i <=8; i++)
			System.out.println(countAndSay(i));
	}
}
