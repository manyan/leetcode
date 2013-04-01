package leetcode;
/*
 *Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 *refer to : http://sudoku.com.au/TheRules.aspx
 *1) Each row must have the numbers 1-9 occuring just once.
 *2) Each column must have the numbers 1-9 occuring just once.
 *3) the numbers 1-9 must occur just once in each of the 9 sub-boxes of the grid.
 *
 *explain 3) a little bit
 *so the sudoku is a 9*9 matrix, which contains 9 3*3 sub-boxes

The Sudoku board could be partially filled, where empty cells are filled with the character '.'. 
 * */
public class SudokuValid {
	// input is a char[][] matrix, because according to the question, its not a full filled suduku, it can contain '.' if its a empty grid
	public static boolean isValidSudoKu(char[][] matrix) {
		return isRowValid(matrix) && isColumnValid(matrix) && isSubGridValid(matrix);
	}
	
	public static boolean isRowValid(char[][] matrix) {
		boolean[] flag = null; // default is false, do not need to init it
		
		for (int i = 0; i < matrix.length; i++) {
			flag = new boolean[9];
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] != '.') {
					int num = matrix[i][j] - '1';
					if (!flag[num]) {
						flag[num] = true;
					} else {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public static boolean isColumnValid(char[][] matrix) {
		boolean[] flag = null;
		
		for (int i = 0; i < matrix.length; i++) {
			flag = new boolean[9];
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[j][i] != '.') {
					int num = matrix[j][i] - '1';
					if (!flag[num]) {
						flag[num] = true;
					} else {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public static boolean isSubGridValid(char[][] matrix) {
		boolean[] flag = null;
		for (int i = 0; i < matrix.length; i = i + 3) {
			for (int j = 0; j < matrix.length; j = j + 3) {
				flag = new boolean[9]; //reinit for every grid
				for (int row = 0; row < 3; row++) {
					for (int col = 0; col < 3; col++) {
						if (matrix[i+row][j+col] != '.') {
							int num = matrix[i+row][j+col] - '1';
							if (!flag[num]) {
								flag[num] = true;
							} else {
								return false;
							}
						}
					}
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		String[] input = {"519748632","783652419","426139875","357986241","264317598","198524367","975863124","832491756","641275983"};
		char[][] matrix = new char[9][9];
		for(int i = 0; i < input.length; i++) {
			String s = input[i];
			matrix[i] = s.toCharArray();
		}
		
		System.out.println(isValidSudoKu(matrix));
	}
}
