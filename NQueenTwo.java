package leetcode;

import java.util.ArrayList;

/*
 * just need to return how many distinct solutions
 * 
 * solution: DFS
 * */
public class NQueenTwo {
	public static int solveQueen(int n) {
		// say position[i] = j, means in the n*n board, we put the ith queen at (i,j) position
		int[] positions = new int[n];
		for (int i = 0; i < n; i++) {
			// have not set ith queen yet
			positions[i] = -1;
		}
		
		boolean[] colIsUserd = new boolean[n];
		
		ArrayList<Integer> solutions = new ArrayList<Integer>();
		solve(positions, colIsUserd, 0, solutions);
		return solutions.size();
	}
	
	// detect whether its ok to put ith queen at (i,j) position
	public static boolean isOk(int i, int j, int[] positions) {
		for (int p = 0; p < positions.length; p++) {
			if (p != i && positions[p] != -1 && Math.abs(positions[p] - j) == Math.abs(p-i)) {
				return false;
			}
		}
		return true;
	}
	
	public static void solve(int[] positions, boolean[] colIsUserd, int row, ArrayList<Integer> solutions) {
		if (row == positions.length) {
			solutions.add(1);
		} else {
			for (int col = 0; col < positions.length; col++) {
				if (!colIsUserd[col]) {
					colIsUserd[col] = true;
					positions[row] = col;
					if (isOk(row, col, positions)) {
						solve(positions, colIsUserd, row+1, solutions);
					}
					positions[row] = -1;
					colIsUserd[col] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(solveQueen(6));
	}
}
