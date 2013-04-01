package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


/*
 *  find 3 element from a array (only contains integer, but can be negative and positive), that sum equals to target
 *  
 *  idea: brute force will need O(n*n*n), so we must think a better way
 *  1) sort the array first, which will take (nlgn) time
 *  2) i from 0 to array.lenght-1, we fix the first element, then make start = i+1, and end = array.length-1, move this 2 pointers still we find the result
 *  3) need O(n*n) time 
 *  
 *  A) similar question, select 3 element from a array, their sum is closest to the target
 *  only different is, if the sum matchs target, return, because its already the CLOSEST 
 *  and keep track of the current closest sum, update it if needed
 *  
 *  B) find 4 element from a array that sum equals to target
 *  the idea is the same, instead of fix i, we need to fix j as well, so j = i+1, start = j+1, end = array.length-1
 *  the time is O(n*n*n)
 *  so say find 5 element, time because O(n*n*n*n)
 * */
public class ThreeSum {
	public static ArrayList<ArrayList<Integer>> threeSum(int[] array, int target) {
		if (array == null || array.length < 3) {
			return null;
		}
		
		HashSet<ArrayList<Integer>> results = new HashSet<ArrayList<Integer>>();
		
		//sort it first
		Arrays.sort(array);
		int tempSum = 0;
		for (int i =  0; i < array.length-2;i++) {
			int start = i+1;
			int end = array.length-1;
			while (start < end) {
				tempSum = array[i] + array[start] + array[end];
				if (tempSum == target) {
					ArrayList<Integer> result = new ArrayList<Integer>();
					result.add(array[i]);
					result.add(array[start]);
					result.add(array[end]);
					results.add(result);
					start++;
					end--;
				} else if (tempSum > target) {
					end--;
				} else {
					start++;
				}
			}
		}
		
		return new ArrayList<ArrayList<Integer>>(results);
	}
	
	public static ArrayList<ArrayList<Integer>> fourSum(int[] array, int target) {
		if (array == null || array.length < 4) {
			return null;
		}
		
		Arrays.sort(array);
		HashSet<ArrayList<Integer>> results = new HashSet<ArrayList<Integer>>();
		int tempSum = 0;
		for (int i = 0; i < array.length-3; i++) {
			for (int j = i+1; j < array.length-2; j++) {
				int start = j+1;
				int end = array.length-1;
				while (start < end) {
					tempSum = array[i] + array[j] + array[start] + array[end];
					if (tempSum == target) {
						ArrayList<Integer> result = new ArrayList<Integer>();
						result.add(array[i]);
						result.add(array[j]);
						result.add(array[start]);
						result.add(array[end]);
						results.add(result);
						start++;
						end--;
					} else if (tempSum > target) {
						end--;
					} else {
						start++;
					}
				}
			}
		}
			
			
		return new ArrayList<ArrayList<Integer>>(results);
	}
	
	public static void main(String[] args) {
		int[] array = {10,4,-1,-1,0,2,1};
		ArrayList<ArrayList<Integer>> results = fourSum(array, 0);
		for (ArrayList<Integer> result : results) {
			for (Integer i : result) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
