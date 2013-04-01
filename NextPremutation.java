package leetcode;


/*
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

idea:
1) 从右往左扫面, 找到第一个递增的pair, eg: 231, 第一队递增的是23, 如果没有, 说明已经是最后一个premutation, 把整个reverse一次
2) i指向2, 再从最后往前找, 第一个j, array.length > j > i, 同时array[j] > array[i]
3) swap(i,j)
4) 把数组从i+1开始reverse
 * */
public class NextPremutation {
	public static void nextPremutation(int[] premutation) {
		if (premutation == null || premutation.length == 0) {
			System.out.println("");
		}
		
		for (int i = premutation.length-2; i>=0; i--) {
			if (premutation[i] < premutation[i+1]) {
				for (int j = premutation.length-1; j > i; j--) {
					if (premutation[j] > premutation[i]) {
						swap(premutation, i, j);
						// reverse the the from the i+1 th element
						reverse(premutation, i+1);
						return;
					}
				}
			}
		}
		
		// its already the last premutation , reverse the whole premutation
		reverse(premutation,0);
	}
	
	// reverse from the kth element
	public static void reverse(int[] a, int k) {
		int start = k;
		int end = a.length-1;
		while (start < end) {
			swap(a, start, end);
			start++;
			end--;
		}
	}
	
	// swap
	public static void swap(int[] a, int i, int j) {
		if (i < 0 || i >= a.length || j < 0 || j >= a.length) {
			return;
		}
		
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	
	
	public static void main(String[] args) {
		int[] a = {2,3,1};
		nextPremutation(a);
	//	reverse(a,1);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}
}
