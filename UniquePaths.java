package leetcode;

import java.util.ArrayList;

/*
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


 * solution : dynamic programming
 * m = 3, n = 3
 * 1 1 1
 * 1 2 3
 * 1 3 6
 * 
 * matrix[i][j] = matrix[i][j-1] + matrix[i-1][j]
 * */
public class UniquePaths {
	public static int uniquePaths(int m, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
		if (m <= 1 || n <= 1) {
			return 1;
		}
		
		int[][] matrix = new int[m][n];
		for (int i = 0; i < m; i++) {
			matrix[i][0] = 1;
		}
		
		for (int i = 0; i < n; i++) {
			matrix[0][i] = 1;
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				matrix[i][j] = matrix[i][j-1] + matrix[i-1][j]; 
			}
		}
		
		return matrix[m-1][n-1];
    }
	public static void main(String[] args) {
		System.out.println(uniquePaths(3,3));
	}
}
