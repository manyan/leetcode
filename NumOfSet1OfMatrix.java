package leetcode;

/*
 *  say we have a matrix as below
 *  0 1 1 0 
 *  1 0 0 0 
 *  0 1 1 0 
 *  0 1 1 0 
 *  
 *  we have 3 set of 1
 *  set of 1 means a serial of 1 is connected on the left or right or top or button of another 1
 *  as we can see from the matrix, there are 3 of set 1
 *  
 *  solution:
 *  similar to SurroundedRegion, we still use DFS
 *  for every element of the matrix, if its 1, DFS it, count++, and mark the 1 to + , and also mark any other 1 that can be found from DFS to + 
 *   
 * */
public class NumOfSet1OfMatrix {
	public static int numOfSet1OfMatrix(char[][] matrix) {
		if (matrix.length <= 0 || matrix[0].length <= 0)
			return 0;
		
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == '1') {
					count ++;
					dfs(matrix, i, j);
				}
			}
		}
		
		return count;
	}
	
	public static void dfs(char[][] matrix, int x, int y) {
		if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length)
			return;
		
		matrix[x][y] = '+';
		
		if (x-1 >= 0 && matrix[x-1][y] == '1') 
			dfs(matrix, x-1, y);
		
		if (x+1 < matrix.length && matrix[x+1][y] == '1')
			dfs(matrix, x+1, y);
		
		if (y-1 >= 0 && matrix[x][y-1] == '1')
			dfs(matrix, x, y-1);
		
		if (y+1 < matrix[0].length && matrix[x][y+1] == '1')
			dfs(matrix, x, y+1);
	}
	
	public static void main(String[] args) {
		char[] line1 = {'0','1','1','0'};
		char[] line2 = {'1','0','0','0'};
		char[] line3 = {'0','1','1','0'};
		char[] line4 = {'0','1','1','0'};
		
		char[][] matrix = {line1, line2, line3, line4};
		
		System.out.println(numOfSet1OfMatrix(matrix));
	}
}
