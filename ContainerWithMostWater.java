package leetcode;

import java.util.HashMap;

/*
 * Given n non-negative integers a1, a2, …, an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
Note: You may not slant the container.
 * */

/*
 *  solution: actually its the similar the one that find the max rectangle of a array
 *  the different is: in this question, we can just find 2 stickers, left sticker and right sticker, then make a container, so we can ignore the stickers
 *  between left sticker and right sticker, but max rectangle need to calculate the stickers between left and right
 *  in find the max rectangle of a array, we use a Monotone increasing queue to hold the current max
 *  but in this implementation, i am going to use another method to solve it in o(n) time without extra space
 *  
 * */
public class ContainerWithMostWater {
	public static int maxArea(int[] height) {
		if (height == null || height.length == 0) {
			return -1;
		}
		
		if (height.length == 1) {
			return height[0];
		}
		
		int start = 0;
		int end = height.length-1;
		int max = 0;
		int sPoint = start;
		int ePoint = end;
		int tempMax = 0;
		while (start < end) {
			tempMax = (end-start)*(height[start] >= height[end] ? height[end] : height[start]);
			if (tempMax > max) {
				max = tempMax;
				sPoint = start;
				ePoint = end;
			}
			
			if (height[start] <= height[end]) {
                //Since start is lower than end, (array[start] <= array[end])
                //so there will be no end' < end that make the area from start,end'
                //is greater than area from start,end
                //so the maximum area that can benefit from start is already recorded.
                //thus, we move start forward.
				start++;
			} else {
				end--;
			}
		}
		
		//System.out.println(String.format("left stick is:%s, right stick is :%s", sPoint, ePoint));
		return max;
	}
	
	public static void main(String[] args) {
		int[] array = {3,2,4,1,5};
		System.out.println(maxArea(array));
		
	}
}
