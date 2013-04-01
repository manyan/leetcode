package leetcode;

import java.util.ArrayList;
import java.util.List;

import basicClass.TreeNode;

/*
 * print all root to leaf path
 *  * 			8
	 * 		   / \	
	 *        5   10
	 *       / \    \
	 *      3   6    12
	 *           \
	 *            7
	 *   all path:         
	 *    8 5 3
	 *    8 5 6 7 
	 *    8 10 12
 * */
public class PrintAllRootToLeafPath {
	public static void printAllRootToLeafPath(TreeNode root) {
		doPrintAllRootToLeafPath(root, new ArrayList<TreeNode>());
	}
	
	public static void doPrintAllRootToLeafPath(TreeNode root, List<TreeNode> list) {
		if (root == null)
			return;
		
		list.add(root);
		
		if (root.left == null && root.right == null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).value + " ");
			}
			System.out.println();
		}
		
		if (root.left != null) {
			doPrintAllRootToLeafPath(root.left, list);
		}
		
		if (root.right != null) {
			doPrintAllRootToLeafPath(root.right, list);
		}
		
		// remove the current node before this method return to its parent node
		list.remove(root);
	}
	
	public static void main(String[] args) {
		TreeNode root = TreeNode.createABFT(false);
		printAllRootToLeafPath(root);
	}
}
