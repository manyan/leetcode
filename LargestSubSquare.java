package leetcode;

public class LargestSubSquare {
	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		
		int row = matrix.length;
		int col = matrix[0].length;
		
		int[][] square = new int[row][col];
		int max = 0;
		// init 
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == '1') {
					square[i][j] = 1;
					max = 1;
				}
			}
		}
		
		if (max == 0) {
			return 0;
		}
		
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (matrix[i][j] == '1' && square[i-1][j] > 0 && square[i][j-1] > 0 && square[i-1][j-1] > 0) {
					int min = findMin(square[i-1][j], square[i][j-1], square[i-1][j-1]);
					square[i][j] = min + 1;
					if (square[i][j] > max) {
						max = square[i][j];
					}
				}
			}
		}
		
		return max*max;
    }
	
	private int findMin(int... integers) {
		int min = Integer.MAX_VALUE;
		for (int i : integers) {
			if (i < min) {
				min = i;
			}
	    }
		
		return min;
	}
}
