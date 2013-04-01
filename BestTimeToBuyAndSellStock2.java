package leetcode;


/*
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit.
You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * */

/*
 * Abstraction of the question:
 * 每次上升子序列之前买入，在上升子序列结束的时候卖出。相当于能够获得所有的上升子序列的收益。
 * 对于一个上升子序列，比如：5，1，2，3，4，0 中的1，2，3，4序列来说，对于两种操作方案：
一，在1买入，4卖出；
二，在1买入，2卖出同时买入，3卖出同时买入，4卖出；
这两种操作下，收益是一样的
 * */
public class BestTimeToBuyAndSellStock2 {
	public static int getMaxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}

		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			int delta = prices[i] - prices[i-1];
			if (delta > 0) {
				maxProfit += delta;
			}
		}
		
		return maxProfit;
	}
	
	public static void main(String[] args) {
		int[] p1 = {6,5,2,3,5,6,2,4};
		System.out.println(getMaxProfit(p1));
	}
}
