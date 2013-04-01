package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * */
public class Premutations {
	public static ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
		doPremutations(num,0,results);
		return results;
	}
	
	public static void doPremutations(int[] num, int p, ArrayList<ArrayList<Integer>> results) {
		if (p == num.length) {
			ArrayList<Integer> result = new ArrayList<Integer>();
			for (int i = 0; i < num.length; i++) {
				result.add(num[i]);
			}
			
			results.add(result);
		} else {
			for (int i = p; i < num.length; i++) {
				swap(num, i, p);
				doPremutations(num,p+1,results);
				swap(num, i, p);
			}
		}
	}
	
	public static void swap(int[] num, int i, int j) {
		// without checking i and j are valid or not
		int temp = num[i];
		num[i] = num[j];
		num[j] = temp;
	}
}
