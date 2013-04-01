package leetcode;

/*
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 For example:
 Given array A = [2,3,1,1,4]

 The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * 
 * solution:DP
 * we have matrix:
 *   2 3 1 1 4
 * 2 1 1 1 0 0
 * 3 0 1 1 1 1  
 * 1 0 0 1 1 0
 * 1 0 0 0 1 1
 * 4 0 0 0 0 1 
 * 
 * 找出最后一列,最早一个出现1的位置, 是第一行
 * 然后找出第一列, 最好一个出现1的位置, 是第0行, 找到目的
 * 一共经历两步
 * 
 * 优化, 只要第一次到达最后一列, 以后的matrix都不需要画了
 * 因为最优的跳, 肯定不会往回跳, 所以填每一行的时候, 只需要从该列对应的位置开始填
 * 
 * */
public class JumpGameTwo {
	public static int jumpDP(int[] num) {
		if (num == null || num.length <= 1) {
			return 0;
		}

		boolean[][] matrix = new boolean[num.length][num.length];
		int earliestP = 0;
		boolean breakSignal = false;
		for (int i = 0; i < num.length; i++) {
			if (breakSignal) {
				break;
			}
			for (int j = i; j <= i + num[i]; j++) {
				if (j == num.length - 1) {
					matrix[i][j] = true;
					earliestP = i;
					breakSignal = true;
					break;
				} else {
					matrix[i][j] = true;
				}
			}
		}

		int count = 1;
		while (earliestP > 0) {
			for (int i = 0; i <= earliestP; i++) {
				if (matrix[i][earliestP]) {
					earliestP = i;
					break;
				}
			}
			count++;
		}

		return count;
	}

	public static int jumpGreedy(int[] num) {
		if (num == null || num.length <= 1) {
			return 0;
		}

		int currentPosition = 0;
		int counter = 0;
		while (currentPosition < num.length - 1 && counter < num.length) {
			counter++;
			int currentMaxPosition = num[currentPosition] + currentPosition; 
			if (currentMaxPosition >= num.length - 1) {
				break;
			}
			int tempMax = 0;
			// greedy, find the next max jump position
			for (int i = currentPosition + 1; i <= currentMaxPosition
					&& i < num.length; i++) {
				if (num[i] + i > tempMax) {
					tempMax = num[i] + i;
					currentPosition = i;
				}
			}
		}

		// no way to jump
		// eg: {0,0,0}
		if (counter == num.length) {
			return -1;
		}

		return counter;
	}

	public static int jump(int[] A) {
		int len = A.length;
		int step[] = new int[len];
		int max = 0;
		for (int i = 0; i < len; ++i) {
			for (int j = max + 1; j <= i + A[i] && j < len; j++) {
				step[j] = step[i] + 1;
			}
			max = Math.max(max, i + A[i]);
		}
		return step[len - 1];
	}

	public static void main(String[] args) {
		int[] num = { 2, 3, 1, 1, 4 };
		int[] n = { 0, 0 };
		System.out.println(jump(n));
	}
}
