package leetcode;

/*
 * Given an unsorted integer array, find the first missing positive integer.
For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.
Your algorithm should run in O(n) time and uses constant space.

solution: we need to rearrange each element into "correct" position, say A[i] == i+1
take [1,2,0] as an example
A[0] = 1, correct
A[2] = 2, correct
A[3] = 0, incorrect, then we know that the first missing element is 3

 * */
public class FirstMissingPositive {
	public static int firstMissingPositive(int[] num) {
		if (num == null || num.length == 0)
			return 1;
		
		for (int i = 0; i < num.length; i++) {
			while (num[i] != i+1) {
				if (num[i] <= 0 || num[i] > num.length || num[i] == num[num[i]-1]) {
					// ignore 0 and negative num, ignore num that larger than num.length
					// if num[i] == num[num[i]-1], no need to swap
					break;
				}
				
				// swap
				// say num[i] = k, so this element should sit in num[k-1]
				int k = num[i];
				num[i] = num[k-1];
				num[k-1] = k;
			}
		}
		
		for (int i = 0; i < num.length; i++) {
			if (num[i] != i+1) {
				return i+1;
			}
		}
		
		return num.length + 1;
	}
}
