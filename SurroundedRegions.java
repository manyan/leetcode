package leetcode;

/*
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region .
 
 * 
 *   X X X X
     X O O X
     X X O X
     X O X X 
       TO
       
     X X X X
     X X X X
     X X X X
     X O X X
     
     
  solution:
  1) from the outer layer (the 4 boundries of the matrix), if its 0, then DFS it, and mark 0 to + (or any other charactor)
  2) scan from each element of the matrix, if its + , change it to 0, if its 0 , change it to X
  
  idea:
  0 that is not surrounded by x only happens, when a serial of 0 connected together and one or more 0 reach the boundry.
  so we need to search from the boundry to inner layer using DFS
 * */
public class SurroundedRegions {
	public static void surroundedRegions(char[][] matrix) {
		if (matrix.length <= 1 || matrix[0].length <= 1)
			return;
		
		// DFS 4 boundries
		for (int i = 0; i < matrix.length; i++) {
			// search left boundry
			if (matrix[i][0] == '0')
				dfs(matrix, i, 0);
			
			// search right boundry
			if (matrix[i][matrix[0].length-1] == '0')
				dfs(matrix, i, matrix[0].length - 1);
		}
		
		for (int i = 0; i < matrix[0].length; i++) {
			// search top boundry
			if (matrix[0][i] == '0')
				dfs(matrix,0,i);
			
			// search button boundry
			if (matrix[matrix.length-1][i] == '0')
				dfs(matrix,matrix.length - 1,i);
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == '0') {
					matrix[i][j] = 'X';
				} else if (matrix[i][j] == '+') {
					matrix[i][j] = '0';
				}
			}
		}
	}
	
	public static void dfs(char[][] matrix, int x, int y) {
		if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length)
			return;
		
		matrix[x][y] = '+';
		
		if (x-1 >= 0 && matrix[x-1][y] == '0') 
			dfs(matrix, x-1, y);
		
		if (x+1 < matrix.length && matrix[x+1][y] == '0')
			dfs(matrix, x+1, y);
		
		if (y-1 >= 0 && matrix[x][y-1] == '0')
			dfs(matrix, x, y-1);
		
		if (y+1 < matrix[0].length && matrix[x][y+1] == '0')
			dfs(matrix, x, y+1);
		
	}
	
	public static void printMatrix(char[][] matrix) {
		if (matrix.length <= 0 || matrix[0].length <= 0)
			return;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main (String[] args) {
		char[] line1 = {'X','X','X'};
		char[] line2 = {'X','0','X'};
		char[] line3 = {'X','X','X'};
		
		char[][] matrix = {line1, line2, line3};
		
		printMatrix(matrix);
		surroundedRegions(matrix);
		System.out.println();
		printMatrix(matrix);
	}
}
