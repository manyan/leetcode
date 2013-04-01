package leetcode;

/*
 * Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * */
public class BestTimeToBuyAndSellStock {
	/*
	 *  * Abstraction of the question:
 * say we have a array a, find the max diff 
 * max diff = max (a[i] - a[j]) (0 <= i < a.leght && 0<=j<a.lehgth && i > j)
 * 
 * idea:
 * dp.
 * diff[i]: 以a[i] 为减数, 对于任何j > i a[j] - a[i] 的最大值, 这时, a[j] 就是 a[i]右边的最大值
 * diff[i-1]: 则为 max(a[j] - a[i-1], a[i] - a[i-1])
 * 所以我们只要保存下当前最大值便可
	 * */
	public static void getMaxDiff(int[] array) {
		if (array == null || array.length < 2) {
			return;
		}
		
		// init
		int maxPoint = array.length-1;
		int minPoint = array.length - 2;
		int maxDiff = array[maxPoint] - array[minPoint]; 
		
		for (int i = array.length - 2; i >= 0; i--) {
			maxPoint = array[maxPoint] >= array[i+1] ? maxPoint : i+1;
			int currentMaxDiff = array[maxPoint] - array[i];
			if (currentMaxDiff > maxDiff) {
				maxDiff = currentMaxDiff;
				minPoint = i;
			}
		}

		if (maxDiff > 0) {
			System.out.println(String.format("you should buy it at %s day, and sell it at %s day",  minPoint, maxPoint));
		} else {
			System.out.println("you will never win money on this stock");
		}
	}
	
	public static void getMaxDiffEasyWay(int[] array) {
		if (array == null || array.length < 2) {
			return;
		}
		
		int minPoint = 0;
		int maxPoint = 1;
		int mostProfit = array[maxPoint] - array[minPoint];
		
		for (int i = 1; i < array.length; i++) {
			int currentMostProfit = array[i] - array[minPoint];
			if (currentMostProfit > mostProfit) {
				mostProfit = currentMostProfit;
				maxPoint = i;
			}
			
			minPoint = array[minPoint] > array[i] ? i : minPoint;  
		}
		
		if (mostProfit > 0) {
			System.out.println(String.format("you should buy it at %s day, and sell it at %s day",  minPoint, maxPoint));
		} else {
			System.out.println("you will never win money on this stock");
		}
	}

	public static void main(String[] args) {
		int[] array = {5,3,4,2,7};
		int[] a2 = {3,4,5,6,7};
		int[] a3 = {7,6,5,4,3,2};
		getMaxDiffEasyWay(array);
	}
}
