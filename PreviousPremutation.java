package leetcode;

/*
 * simliar to next premutation
 * but in a oppsite way
 * 
 * solution:
 * 1) find the first desc pair from the end of the current premutation, num[i] > num[i+1]
 * 2) according to num[i], from i+1 to num.length-1, find the element num[j] that slictly smaller than num[i]
 * 3) swap num[i] and num[j]
 * 4) reverse i+1 to num.length-1
 * 
 * */
public class PreviousPremutation {
	public static int[] previousPremutation(int[] num) {
		if (num == null || num.length == 0) {
			return new int[0];
		}
		
		if (num.length == 1) {
			return num;
		}
		
		int firstDescPosition = 0;
		for (int i = num.length-2; i >= 0; i--) {
			if (num[i] > num[i+1]) {
				firstDescPosition = i;
				break;
			}
		}
		
		if (firstDescPosition == 0) {
			reverse(num, 0);
			return num;
		}
		
		int smallerPosition = firstDescPosition+1;
		for (int i = firstDescPosition+1; i < num.length; i++) {
			if (num[i] < num[firstDescPosition] && num[i] > num[smallerPosition]) {
				smallerPosition = i;
			}
		}
		
		// swap num[smallerPosition] and num[firstDescPosition]
		int temp = num[smallerPosition];
		num[smallerPosition] = num[firstDescPosition];
		num[firstDescPosition] = temp;
		
		reverse(num, firstDescPosition+1);
		
		return num;
	}
	
	public static void reverse(int[] num, int fromPosition) {
		int start = fromPosition;
		int end = num.length-1;
		while (start < end) {
			int temp = num[start];
			num[start] = num[end];
			num[end] = temp;
			start++;
			end--;
		}
	}
	
	public static void main(String[] args) {
		int[] num = {3,4,2,1};
		previousPremutation(num);
		for (int i : num) {
			System.out.print(i + " ");
		}
	}
}
