package leetcode;

/*
 * The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.
 * 
 * */
public class PermutationSequence {
	public static String getPermutation(int n, int k) {
		int[] num = new int[n];
		for (int i = 0; i < num.length; i++) {
			num[i] = i+1;
		}
		
		for (int i = 1; i < k; i++) {
			NextPremutation.nextPremutation(num);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num.length; i++) {
			sb.append(num[i]);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getPermutation(3,2));
	}
}
