package leetcode;

import java.util.ArrayList;

/*
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
 * 
 * */
public class SpiralMatrix {
	public static ArrayList<Integer> spiralMatrix (int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return new ArrayList<Integer>();
		}
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		int currentMaxColumnIndex = matrix[0].length-1;
		int currentMaxRowIndex = matrix.length-1;
		int currentRowIndex = 0;
		int currentColIndex = 0;
		
		while(currentRowIndex <= currentMaxRowIndex && currentColIndex <= currentMaxColumnIndex) {
			// deal with there is only one row left
			if (currentRowIndex == currentMaxRowIndex) {
				for (int i = currentColIndex; i <= currentMaxColumnIndex; i++) {
					result.add(matrix[currentRowIndex][i]);
				}
				break;
			}
			
			// deal with there is only one column left
			if (currentColIndex == currentMaxColumnIndex) {
				for (int i = currentRowIndex; i <= currentMaxRowIndex; i++) {
					result.add(matrix[i][currentMaxColumnIndex]);
				}
				break;
			}
			
			// add the toppest row into result
			for (int i = currentColIndex; i <= currentMaxColumnIndex; i++) {
				result.add(matrix[currentRowIndex][i]);
			}
			currentRowIndex++;
	
			// add the rightest col into result
			for (int i = currentRowIndex; i <= currentMaxRowIndex; i++) {
				result.add(matrix[i][currentMaxColumnIndex]);
			}
			currentMaxColumnIndex--;
			
			// add the button row into result
			for (int i = currentMaxColumnIndex; i >=currentColIndex; i--) {
				result.add(matrix[currentMaxRowIndex][i]);
			}
			currentMaxRowIndex--;
			
			// add the leftest col into result
			for (int i = currentMaxRowIndex; i >= currentRowIndex; i--) {
				result.add(matrix[i][currentColIndex]);
			}
			currentColIndex++;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		int[][] test = {{1},{2},{3}};
		ArrayList<Integer> result = spiralMatrix(test);
		
		for (Integer i : result) {
			System.out.print(i + " ");
		}
	}
}
