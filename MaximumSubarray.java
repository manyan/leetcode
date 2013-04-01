package leetcode;

/*
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * 
 * */
public class MaximumSubarray {
	public static int maxSubArray(int[] num) {
		if (num == null || num.length == 0) {
			return 0;
		}

		int maxSum = 0;
		int max = 0;
		for (int i = 0; i < num.length; i++) {
			maxSum += num[i];
			if (maxSum < 0) {
				maxSum = 0;
			} else if (maxSum > max) {
				max = maxSum;
			}
		}

		if (max == 0) {
			// all elements in the array are negative, find the largest element out
			max = num[0];
			for (int i = 1; i < num.length; i++) {
				if (max < num[i]) {
					max = num[i];
				}
			}
		}
		
		return max;
	}
}
