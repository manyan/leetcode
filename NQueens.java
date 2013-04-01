package leetcode;

import java.util.ArrayList;

/*
 * The n-queens puzzle is the problem of placing n queens on an n*Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]n chessboard such that no two queens attack each other.
 * 
 * 
 * 
 * */
public class NQueens {
	public static ArrayList<String[]> solveQueens(int n) {
		ArrayList<String[]> solutions = new ArrayList<String[]>();
		int[] num = new int[n];
		for (int i = 0; i < num.length; i++) {
			num[i] = i;
		}
		
		premutation(num,0,solutions);
		return solutions;
	}

	// use premutation to generation each queen's position
	// take ith queen as example, say ith queen will always stay in ith line,
	// then the num[i] will be the position of the ith line of ith queens
	private static void premutation(int[] num, int p, ArrayList<String[]> results) {
		if (p == num.length) {
			// might be a solution
			if (isSolution(num)) {
				results.add(toSolution(num));
			}
		} else {
			for (int i = p; i < num.length; i++) {
				swap(num, i, p);
				premutation(num, p + 1, results);
				swap(num, i, p);
			}
		}
	}

	private static boolean isSolution(int[] num) {
		for (int i = 0; i < num.length; i++) {
			int position = num[i];
			int offset = 0;
			for (int j = i+1; j < num.length; j++) {
				offset++;
				if (num[j] == position+offset || num[j] == position-offset) {
					return false;
				}
			}
			
			offset = 0;
			for (int j = i - 1; j >= 0; j--) {
				offset++;
				if (num[j] == position+offset || num[j] == position-offset) {
					return false;
				}
			}
		}
		
		return true;
	}

	private static void swap(int[] num, int i, int j) {
		int temp = num[i];
		num[i] = num[j];
		num[j] = temp;
	}
	
	private static String[] toSolution(int[] num) {
		// init solution
		String[] solution = new String[num.length];
		for (int i = 0; i < num.length; i++) {
			StringBuilder sb = new StringBuilder();
			int position = num[i];
			for (int j = 0; j < num.length; j++) {
				if (position == j) {
					sb.append("Q");
				} else {
					sb.append(".");
				}
			}
			
			solution[i] = sb.toString();
		}
		
		return solution;
	}
	
	public static void main(String[] args) {
		ArrayList<String[]> solutions = solveQueens(6);
		System.out.println(solutions.size());
		for (String[] sol : solutions) {
			for (String s : sol) {
				System.out.println(s);
			}
			
			System.out.println("---------");
		}
	}
}
