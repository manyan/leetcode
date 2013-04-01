package leetcode;

/*
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

solution: similar to jump game two
just need to detect that, whether the max lenght position's jump step is 0
 * */
public class JumpGame {
	public static boolean canJump(int[] num) {
		if (num == null || num.length <= 1) {
			return true;
		}
		
		int counter = 0;
		int position = 0;
		while (position < num.length && counter < num.length) {
			counter++;
			int maxJumpDisntance = num[position] + position;
			if (maxJumpDisntance >= num.length-1) {
				return true;
			}
			
			int nextMax = 0;
			for (int i = position+1; i < num.length && i <= maxJumpDisntance; i++) {
				int temp = i + num[i];
				if (temp > nextMax) {
					nextMax = temp;
					position = i;
				}
			}
			
			if (num[position] == 0) {
				return false;
			}
		}
		
		if (counter == num.length) {
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		int[] num = {2,0,0};
		System.out.println(canJump(num));
	}
}
