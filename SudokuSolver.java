package leetcode;

/*
 * Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.


 * solution: recursion! 
 * */
public class SudokuSolver {
	public static final char[] nums = {'1','2','3','4','5','6','7','8','9'};
	
	public static void solveSudoku(char[][] matrix) {
		doSolveSudoku(matrix);
	}
	
	public static boolean doSolveSudoku(char[][] matrix) {	
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] == '.') {
					// need to fill it with num
					for (char c : nums) {
						matrix[i][j] = c;
						if (isValid(matrix, i, j) && doSolveSudoku(matrix)) {
							return true;
						}
						
						// recover if c is not a solution
						matrix[i][j] = '.';
					}
					
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static boolean isValid(char[][] matrix, int row, int col) {
		boolean[] flag = new boolean[9];
		
		// check row is valid
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[row][i] != '.') {
				int num = matrix[row][i] - '1';
				if (!flag[num]) {
					flag[num] = true;
				} else {
					return false;
				}
			}
		}
		
		flag = new boolean[9];
		//check col is valid
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][col] != '.') {
				int num = matrix[i][col] - '1';
				if (!flag[num]) {
					flag[num] = true;
				} else {
					return false;
				} 
			}
		}
		
		// check grid is valid
		flag = new boolean[9];
		for (int i = row/3*3; i < row/3*3 + 3; i++) {
			for (int j = col/3*3; j < col/3*3 + 3; j++) {
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
	
	public static void main(String[] args) {
		String[] input = {"53..7....", "6..195...",".98....6.", "8...6...3","4..8.3..1","7...2...6",".6....28.","...419..5","....8..79"};
		char[][] matrix = new char[9][9];
		for (int i = 0; i < input.length; i++) {
			matrix[i] = input[i].toCharArray();
		}
		
		System.out.println(doSolveSudoku(matrix));
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println(SudokuValid.isValidSudoKu(matrix));
	}
}
