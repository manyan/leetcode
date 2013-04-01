package leetcode;

/*
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * solution:
 * 对于每一个bar, 他能够接到的水量, 等于位于其左边的最高的bar1和位于其右边的最高的bar2中最小的一个减去当前bar的高度, 
 * 即 num[i] ==  current bar height
 * volumn = Math.min(bar1,bar2) - bar
 * 还可以简化逻辑: bar1的定义可以变为, 当前bar, 以及位于其左边的所有bar的最高值
 * bar2定义为, 当前bar, 以及位于其右边的bar的最高值, 这样我们就不需要处理volumn = Math.min(bar1,bar2) - bar 为负的情况
 * 比如, 当前bar是2, 其左边最高的bar为1, 他右边最高为3, 那么对于当前bar来说, 其实他是没有办法乘到水的, 更不可能是负值
 * 所以如果修改bar1, bar2的定义, 最小就是0, 这样我们只需要累加
 * */
public class TrappingRainWater {
	public static int trap(int[] num) {
		int sum = 0;
		if (num == null || num.length <= 1){
			return sum;
		}
		
		int[] leftMost = new int[num.length];
		int[] rightMost = new int[num.length];
		
		leftMost[0] = num[0];
		for (int i = 1; i < num.length; i++) {
			leftMost[i] = Math.max(leftMost[i-1], num[i]);
		}
		
		rightMost[num.length-1] = num[num.length-1];
		for (int i = num.length-2; i >= 0; i--) {
			rightMost[i] = Math.max(rightMost[i+1], num[i]);
		}
		
		for (int i = 1; i < num.length-1; i++) {
			sum += Math.min(leftMost[i], rightMost[i]) - num[i];
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		int[] num = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(trap(num));
	}
}
