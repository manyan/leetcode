package leetcode;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * */

/*
 * Abstraction of the question:
 * 和前两道题比起来的话，这道题最难了，因为限制了交易次数。
解决问题的途径我想出来的是：既然最多只能完成两笔交易，而且交易之间没有重叠，那么就divide and conquer。
设i从0到n-1，那么针对每一个i，看看在prices的子序列[0,...,i][i,...,n-1]上分别取得的最大利润（第一题）即可。
这样初步一算，时间复杂度是O(n2)。


改进：
改进的方法就是动态规划了，那就是第一步扫描，先计算出子序列[0,...,i]中的最大利润，用一个数组保存下来，那么时间是O(n)。
第二步是逆向扫描，计算子序列[i,...,n-1]上的最大利润，这一步同时就能结合上一步的结果计算最终的最大利润了，这一步也是O(n)。
所以最后算法的复杂度就是O(n)的。
 * */
public class BestTimeToBuyAndSellStock3 {
	public static int getMaxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		
		// build a new array that holding the max profit that we can get from [0-i] day
		// in another words: array maxProfitEndWith[i] means the max profit that we can get from 0th day to ith day
		int[] maxProfitEndWith = new int[prices.length];
		int currentLowestPrice = prices[0];
		maxProfitEndWith[0] = 0;
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			int currentMaxProfit = prices[i] - currentLowestPrice;
			if (currentMaxProfit > maxProfit) {
				maxProfit = currentMaxProfit;
			} 
			maxProfitEndWith[i] = maxProfit;
			currentLowestPrice = currentLowestPrice > prices[i] ? prices[i] : currentLowestPrice;
		}
		
		// second round, find from the max profit from [i - n-1] days
		// in this case, we need to mark down the highest price instead of the lowest one
		int currentHighestPrice = prices[prices.length-1];
		maxProfit = 0;
		int globalMaxProfit = 0;
		
		for (int i = prices.length - 2; i >= 0; i--) {
			int currentMaxProfit = currentHighestPrice - prices[i];
			if (currentMaxProfit > maxProfit) {
				maxProfit = currentMaxProfit;
			}
			
			int currentGlobalMaxProfit = maxProfit + maxProfitEndWith[i];
			if (currentGlobalMaxProfit > globalMaxProfit) {
				globalMaxProfit  = currentGlobalMaxProfit;
			}
			
			currentHighestPrice = currentHighestPrice > prices[i] ? currentHighestPrice : prices[i];
		}

		return globalMaxProfit;
	}
	
	public static void main(String[] args) {
		int[] prices = {5,2,3,2,6,2,3,2,5};
		System.out.println(getMaxProfit(prices));
	}
}
