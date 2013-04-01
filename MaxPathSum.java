package leetcode;

import basicClass.ResultOfMaxPathSum;
import basicClass.TreeNode;

public class MaxPathSum {	
	public static int getMaxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		ResultOfMaxPathSum currentMax = new ResultOfMaxPathSum(root.value, root.value);
		currentMax = doMaxPathSum(root, currentMax);
		return currentMax.globalMax;
	}
	
	public static ResultOfMaxPathSum doMaxPathSum(TreeNode root, ResultOfMaxPathSum currentMax) {
		if (root == null) {
			return new ResultOfMaxPathSum(0, currentMax.globalMax);
		}
		
		ResultOfMaxPathSum leftResultOfMaxPathSum = doMaxPathSum(root.left, new ResultOfMaxPathSum(0, currentMax.globalMax) );
		ResultOfMaxPathSum rightResultOfMaxPathSum = doMaxPathSum(root.right, new ResultOfMaxPathSum(0, currentMax.globalMax));
		int cMax = root.value;
		// currentNodeMax means: the max sum of the path from the current node to any child that it can reach
		// current node max can be 
		// 1.cMax (single node)
		// 2. current node + a path from starts from its direct left child or direct right child (recursively)
		cMax = Math.max(root.value, root.value + Math.max(leftResultOfMaxPathSum.currentNodeMax, rightResultOfMaxPathSum.currentNodeMax));
		
		currentMax.currentNodeMax = cMax;
		int currentGlobalMax = root.value + (leftResultOfMaxPathSum.currentNodeMax > 0 ? leftResultOfMaxPathSum.currentNodeMax : 0) + (rightResultOfMaxPathSum.currentNodeMax > 0 ? rightResultOfMaxPathSum.currentNodeMax : 0);
		currentMax.globalMax = Math.max(currentMax.globalMax, currentGlobalMax);
		
		return currentMax;
	}
	
	public static void main(String[] args) {
		TreeNode root = TreeNode.createTree();
		System.out.println(getMaxPathSum(root));
	}
}
