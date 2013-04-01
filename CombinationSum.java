package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … ,ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3]


solution: DP
Set(k) = Set(k-arr[i]) + arr[i] (i from 0 to arr.length-1)
 * */
public class CombinationSum {
	// DP 
	public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
		Map<Integer, HashSet<ArrayList<Integer>>> results = new HashMap<Integer, HashSet<ArrayList<Integer>>>();
		
        for (int curSum = 1; curSum<=target; curSum++) {
        	for (int i = 0; i < candidates.length; i++) {
        		if (curSum < candidates[i]) {
        			continue;
        		} else if (curSum == candidates[i]) {
        			// find a solution
        			if (results.containsKey(curSum)) {
        				ArrayList<Integer> sol = new ArrayList<Integer>();
        				sol.add(candidates[i]);
        				results.get(curSum).add(sol);
        			} else {
        				// init
        				HashSet<ArrayList<Integer>> solution = new HashSet<ArrayList<Integer>>();
        				ArrayList<Integer> sol = new ArrayList<Integer>();
        				sol.add(candidates[i]);
        				solution.add(sol);
        				results.put(curSum, solution);
        			}
        			continue;
        		}
        		
        		// curSum > candidates[i]
        		int previousSum = curSum - candidates[i];
        		if (results.containsKey(previousSum)) {
        			HashSet<ArrayList<Integer>> sulutions = new HashSet<ArrayList<Integer>>();
        			HashSet<ArrayList<Integer>> previousSolutions = results.get(previousSum);
        			for (ArrayList<Integer> previousSolution : previousSolutions) {
        				if (previousSolution.get(previousSolution.size()-1) > candidates[i]) {
        					continue;
        				}
        				ArrayList<Integer> solution = new ArrayList<Integer>();
        				solution.addAll(previousSolution);
        				solution.add(candidates[i]);
        				sulutions.add(solution);
        			}

        			if (results.containsKey(curSum)) {
        				results.get(curSum).addAll(sulutions);
        			} else {
        				results.put(curSum, sulutions);
        			}
        		}

        	}
        }
        
        if (results.containsKey(target)) {
        	HashSet<ArrayList<Integer>> result = results.get(target);
        	ArrayList<ArrayList<Integer>> formatedResult = new ArrayList<ArrayList<Integer>>();
        	for (ArrayList<Integer> r : result) {
        		formatedResult.add(r);
        	}
        	return formatedResult;
        } else {
        	return new ArrayList<ArrayList<Integer>>();
        }
    }
	
	
	// recursion
	// partial representing a potential sub sum of the solutions
	public static void doCombinationSum(int[] candidates, int target, ArrayList<Integer> partial, ArrayList<ArrayList<Integer>> solutions, int currentPointer) {
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
				ArrayList<Integer> partialTemp = new ArrayList<Integer>();
				partialTemp.addAll(partial);
				partialTemp.add(candidates[i]);
				doCombinationSum(candidates, target, partialTemp, solutions, i);
			}
		}
	}
	
	public static ArrayList<ArrayList<Integer>> combinationSumRecursively (int[] candidates, int target) {
		Arrays.sort(candidates);
		ArrayList<ArrayList<Integer>> solutions = new ArrayList<ArrayList<Integer>>();
		
		doCombinationSum(candidates, target, new ArrayList<Integer>(), solutions, 0);
		return solutions;
	}
	
	public static void main(String[] args) {
		int[] candidates = {3,2,6,7};
		ArrayList<ArrayList<Integer>> results = combinationSumRecursively(candidates, 7);
		
		for (ArrayList<Integer> result : results) {
			System.out.println(result);
		}
	}
}
