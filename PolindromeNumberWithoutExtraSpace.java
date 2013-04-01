package leetcode;

/*
 * detech a number is a polindrome or not without using extra space:
 * eg: 333 is a polindrome number, 334 is not
 * 
 * solution:
 * find the first digit and the last digit of the number, if match, continue
 * if not return false
 * 
 * */
public class PolindromeNumberWithoutExtraSpace {
	public static boolean isPolindromeNumber(int number) {
		if (number < 0)
			return false;
		
		int d = 1; 
		// d means how many digit of a number, say 333 has 3 digit, 
		// then we make d = 100, so the first digit can be retrieve by number / digit
		
		while (number / d >= 10) {
			d *= 10;
		}
		
		while (number >= 10) {
			int firstDigit = number / d;
			int lastDigit = number % 10;
			if (firstDigit != lastDigit) {
				return false;
			}

			number = (number % d) / 10;
			// number % d will remove the first digit
			// / 10 will remove the last digit
			d = d / 100; // since we remove 2 digits , d also need to / 100 
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(isPolindromeNumber(545));
	}
}
