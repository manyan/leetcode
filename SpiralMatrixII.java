package leetcode;

/*
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 * 
 * */
public class SpiralMatrixII {
	public static int[][] generateMatrix(int n) {
		if (n <= 0) {
			int[][] matrix = new int[0][0];
			return matrix;
		}
		
		if (n == 1) {
			int[][] matrix = {{1}};
			return matrix;
		}
		
		int[][] matrix = new int[n][n];
		int counter = 0;
		int maxRowCount = n;
		int maxColCount = n;
		int currentRow = 0;
		int currentCol = 0;
		
		while (currentRow < maxRowCount && currentCol< maxColCount) {
			// fill the toppest row
			for (int i = currentCol; i < maxColCount; i++) {
 				counter++;
				matrix[currentRow][i] = counter; 
			}
			currentRow++;
			if (currentRow == maxRowCount) {
				break;
			}
			
			// fill the rightest col
			for (int i = currentRow; i < maxColCount; i++) {
				counter++;
				matrix[i][maxColCount-1] = counter;
			}
			maxColCount--;
			if (maxColCount ==  currentCol) {
				break;
			}
			
			// fill the button row
			for (int i = maxColCount-1; i >=currentCol ; i--) {
				counter++;
				matrix[maxRowCount-1][i] = counter;
			}
			maxRowCount--;
			
			// fill the leftest col
			for (int i = maxRowCount-1; i >= currentRow; i--) {
				counter++;
				matrix[i][currentCol] = counter;
			}
			currentCol++;
		}
		
		return matrix;
	}
	
	public static void main(String[] args) {
		int[][] matrix = generateMatrix(4);
		
		for (int i = 0;i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		
		int[][] test = new int[0][0];
		System.out.println(test.length);
	}
}
