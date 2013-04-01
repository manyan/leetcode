package leetcode;

import java.util.Stack;

/*
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
 * */

/*
 * idea:http://www.youtube.com/watch?v=E5C5W6waHlo
 * use 2 stacks, one for heights, one for index 
 * if the current height is larger than heightStack.peek(), it has the potential to be the start of the largest rectangle
 * (start of the largest rectangle means, the index is the start point of the rectangle
 * and the height is rectangle's height)
 *  
 *  if the current height equals to the heightStack.peek(), just ignore it, because the if previous one is the start of the largest
 *  rectagnle, it will definitely cover the current height
 *  
 *  if the current height is smaller than heightStack.peek(), need to pop it up, and calculate the potential rectangle, 
 *  and this potential rectangle does not cover the current height
 * */
public class LargestRectangle {
	public static int getLargestRectangle(int[] heights) {
		if (heights == null || heights.length < 1) {
			return 0;
		}
		
		Stack<Integer> heightStack = new Stack<Integer>();
		Stack<Integer> indexStack = new Stack<Integer>();
		
		int largestRectangle = 0;
		int startPoint = 0;
		int endPoint = 0;
		int height = 0;
		int lastIndex = 0; // keep track of the leftest position that has the same height of the current point
		for (int i=0; i < heights.length; i++) {
			if (heightStack.isEmpty() || heights[i] > heightStack.peek()) {
				//case1: might be the start point of the largest rectangle
				heightStack.push(heights[i]);
				indexStack.push(i);
			} else if (heights[i] < heightStack.peek()) {
				while (!heightStack.isEmpty() && heightStack.peek() > heights[i]) {
					// case 2: then we need to calculate each potential rectangle
					lastIndex = indexStack.pop();
					int width = i - lastIndex;
					int currentHeight = heightStack.pop();
					int currentMax = width * (currentHeight); 
					// the potential rectangle that we calculate does not cover the current point
					if (currentMax > largestRectangle) {
						largestRectangle = currentMax;
						startPoint = lastIndex;
						endPoint = i-1;
						height = currentHeight;
					}
				}
				
				// need to push the current height into heightStack 
				// and instead of push current index into indexStack, we push lastIndex
				// because lastIndex keep track of the leftest element that has a larger height than the current point
				heightStack.push(heights[i]);
				indexStack.push(lastIndex);
			}
		}
		
		// if the stack is not empty , we still need to calculate it
		while (!heightStack.isEmpty()) {
			lastIndex = indexStack.pop();
			int currentHeight = heightStack.pop();
			int currentMax = (heights.length - 1 - lastIndex) * currentHeight;
			
			// the potential rectangle that we calculate does not cover the current point
			if (currentMax > largestRectangle) {
				largestRectangle = currentMax;
				startPoint = lastIndex;
				endPoint = heights.length - 1;
				height = currentHeight;
			}
		}
		
		System.out.println(String.format("largest rectangle is :%s, start:%s, end:%s, height:%s", largestRectangle, startPoint, endPoint, height));
		return largestRectangle;
	}
	
	public static void main(String[] args) {
		int[] h = {2,1,5,6,2,3};
		getLargestRectangle(h);
	}
}
