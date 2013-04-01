package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * similar to CombinationSum, but the element in candidates can only exist once in each solution
 * 
 * 
 * 
 * */
public class CombinationSumTwo {
	public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
		Arrays.sort(candidates);
		ArrayList<ArrayList<Integer>> solutions = new ArrayList<ArrayList<Integer>>();
		doCombinationSum(candidates, target, new ArrayList<Integer>(), solutions, 0);
		
		return solutions;
    }
	
	public static void doCombinationSum (int[] candidates, int target, ArrayList<Integer> partial, ArrayList<ArrayList<Integer>> solutions, int currentPointer) {
		int sum = 0;
		for (Integer i : partial) {
			sum += i;
		}
		
		if (sum > target) {
			return;
		} else if (sum == target) {
			if (!solutions.contains(partial)) {
				solutions.add(partial);
			}
		} else {
			for (int i = currentPointer; i < candidates.length; i++) {
				ArrayList<Integer> p = new ArrayList<Integer>();
				p.addAll(partial);
				p.add(candidates[i]);
				// only different, next iteration is i+1 instead of i
				doCombinationSum(candidates, target, p, solutions, i+1);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] candidates = {1,1};
		ArrayList<ArrayList<Integer>> results = combinationSum(candidates, 2);
		
		for (ArrayList<Integer> result : results) {
			System.out.println(result);
		}
	}
}
