package leetcode;

import java.util.ArrayList;
import java.util.List;

import basicClass.TreeNode;

/*
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25. 
 * */

/*
 * idea:
 * Similar to print All Root to lead path
 * */
public class SumOfAllRootToLeafPath {
	public static int sumOfAllRootToLeafPath(TreeNode root) {
		return doSumOfAllRootToLeafPath(root, new ArrayList<TreeNode>(), 0);
	}
	
	public static int doSumOfAllRootToLeafPath(TreeNode root, List<TreeNode> list, int currentSum) {
		if (root == null) 
			return 0;
		
		list.add(root);
		if (root.left == null && root.right == null) {
			// its a root to leaf path
			// calculate the sum of it and add to the currentSum
			int sum = 0;
			for (int i = 0; i < list.size(); i++) {
				// hardcode to multi 10, because the list can only have one digit 
				sum = sum * 10 + list.get(i).value;
			}
			
			currentSum += sum;
		}
		
		if (root.left != null) {
			currentSum = doSumOfAllRootToLeafPath(root.left, list, currentSum);
		}
		
		if (root.right != null) {
			currentSum = doSumOfAllRootToLeafPath(root.right, list, currentSum);
		}
		
		// remove the current node before return to the parent node
		// recovery
		list.remove(root);
		
		return currentSum;
	}
	
	public static void main(String[] args) {
		TreeNode root = TreeNode.createABFT(true);
		System.out.println(sumOfAllRootToLeafPath(root));
	}
}
