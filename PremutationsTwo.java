package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
 * 
 * */
public class PremutationsTwo {
	public static ArrayList<ArrayList<Integer>> premutation(int[] num) {
		ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
		doPremutations(num, 0, results, new HashSet<String>());
		return results;
	}
	
	public static void doPremutations(int[] num, int p, ArrayList<ArrayList<Integer>> results, Set<String> distinct) {
		if (p == num.length) {
			ArrayList<Integer> result = new ArrayList<Integer>();
			for (int i = 0; i < num.length; i++) {
				result.add(num[i]);
			}
			
			results.add(result);
		} else if (distinct.contains(arrayToString(num))) {
			return;
		} else {
			for (int i = p; i < num.length; i++) {
				swap(num, i, p);
				doPremutations(num, p+1, results, distinct);
				swap(num, i, p);
			}
			distinct.add(arrayToString(num));
		}
	}
	
	public static void swap(int[] num, int i, int j) {
		int temp = num[i];
		num[i] = num[j];
		num[j] = temp;
	}
	
	public static String arrayToString(int[] num) {
		StringBuilder sb = new StringBuilder();
		for (int i : num) {
			sb.append(i);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		int[] num = {1,1,2};
		ArrayList<ArrayList<Integer>> results = premutation(num);
		for (ArrayList<Integer> result : results) {
			for (Integer i : result) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
