package leetcode;

/*
 * Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * 
 * */
public class UniquePathsII {
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Start typing your Java solution below
        // DO NOT write main() function
		if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
			return 1;
		}
		
		if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1] == 1) {
			return 0;
		}
		
		// only contains one row
		if (obstacleGrid.length == 1) {
			for (int i = 0; i < obstacleGrid[0].length; i++) {
				if (obstacleGrid[0][i] == 1) {
					return 0;
				}
			}
			return 1;
		}

		// only contains one col
		if (obstacleGrid[0].length == 1) {
			for (int i = 0; i < obstacleGrid.length; i++) {
				if (obstacleGrid[i][0] == 1) {
					return 0;
				}
			}
			return 1;
		}
		
        int[][] matrix = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < matrix.length; i++) {
        	if (obstacleGrid[i][0] == 1) {
        		break;
        	} 
        	matrix[i][0] = 1;
        }
        
        for (int i = 0; i < matrix[0].length; i++) {
        	if (obstacleGrid[0][i] == 1) {
        		break;
        	} 
        	matrix[0][i] = 1;
        }
        
        for (int i = 1; i < matrix.length; i++) {
        	for (int j = 1; j < matrix[0].length; j++) {
        		if (obstacleGrid[i][j] == 0) {
        			matrix[i][j] = matrix[i][j-1] + matrix[i-1][j];
        		} else {
        			matrix[i][j] = 0;
        		}
        	}
        }
        
        return matrix[matrix.length-1][matrix[0].length-1];
    }
	
	public static void main(String[] args) {
		int[][] obs = {{0,0,0,0},{0,1,0,0},{0,0,0,0},{0,0,1,0},{0,0,0,0}};
		//int[][] obs = {{0,1}};
		System.out.println(uniquePathsWithObstacles(obs));
	}
}
