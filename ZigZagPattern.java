package leetcode;

/*
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

zigzag pattern

|     |
|   / |   /
| /   | /
|     |
 * */
public class ZigZagPattern {
	public static String convert(String s, int nRows) {
        // Start typing your Java solution below
        // DO NOT write main() function
		if (s == null || s.length() == 0 || nRows <= 1)
			return s;
		
		char[] source = s.toCharArray();
		int increase = nRows + nRows - 2;
		int ratio = nRows - 2;
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nRows; i++) {
        	if (i == 0 || i == nRows-1) {
        		// there is not additional elements, if its the top or button line
        		for (int j = i; j < source.length; j = j + increase) {
        			sb.append(source[j]);
        		}
        	} else {
        		// need to deal with additional elements
        		for (int j = i; j < source.length; j = j + increase) {
        			sb.append(source[j]);
        			if (j + 2*ratio < source.length) {
        				sb.append(source[j + 2*ratio]);
        			}
        		}
        		ratio--;
        	}
        }
        
        return sb.toString();
    }
	
	public static void main(String[] args) {
		System.out.println(convert("googleishiring", 4));
	}
}
