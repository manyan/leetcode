package leetcode;

public class ReverseInteger {
	// reverse a interger
	// eg: 123 -> 321
	// -123 -> -321
	public static int reverse(int source) {
		try {
			int signal = source >= 0 ? 1 : -1;
			source = Math.abs(source);
			int rev = 0;
			while (source > 0) {
				rev = (source % 10) + (rev * 10);
				source = source  / 10;
			} 
			
			return rev * signal;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return -1;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(reverse(-123));
	}
}
