package leetcode;

import java.util.ArrayList;
import java.util.Collections;

/*
 * median of a array: 
 * 1) sort the array
 * 2) if array's length is odd, the median middle of the sorted array is the median
 * 3) if array's length is even, then we need to find out the 2 middle element A and B, then (A+B)/2 is median
 * */
public class FindMedianOfTwoSortedArrays {
	
	/*
	 * solution:
	 * 1) if arrayA is empty, find the median of arrayB
	 * 2) if arrayB is empty, find the median of arrayA
	 * 3) if arrayA and arrayB are not empty
	 * 4) find the median from arrayA mark it as the i element of arrayA,
	 * we assume that i is the median element of the overall array contains arrayA and arrayB
	 * j = n - i, (n is the median of the overall array)
	 * j means the jth element of arrayB
	 * case 1: if (arrayA[i] == arrayB[j]) arrayA[i] is actually the median 
	 * 
	 * case 2: if (arrayA[i] > arrayB[j])
	 * median will exist in left to i of arrayA or j to right of arrayB
	 * 
	 * case 3: if (arrayA[i] < arrayB[j])
	 * median will exist in i to right of arrayA or left to j of arrayB
	 * 
	 * keep running 4, still we find the median
	 * or still arrayA[left..right] length <=2 && arrayB[left..right] length <= 2
	 * 
	 * */
	public static int getMedian(int[] arrayA, int[] arrayB) {
		if (arrayA.length == 0) {
			return findMedian(arrayB);
		} else if (arrayB.length == 0) {
			return findMedian(arrayA);
		} else {
			return findMedian(arrayA, 0, arrayA.length-1, arrayB, 0, arrayB.length-1);
		}
	}
	
	private static int findMedian(int[] array) {
		boolean isOdd = (array.length % 2) == 1;
		if (isOdd) {
			return array[array.length/2];
		} else {
			int leftM = array.length/2 - 1;
			int rightM = array.length/2;
			return (array[leftM] + array[rightM] ) / 2;
		}
	}
	
	// return the ith element of arrayA which is the median of overall array
	private static int findMedian(int[] arrayA, int leftA, int rightA, int[] arrayB, int leftB, int rightB) {
		int target = (arrayA.length + arrayB.length) / 2;
		boolean isEven = (arrayA.length + arrayB.length) % 2 == 0;
		if (isEven) {
			target--;
			// target is the leftElement , if overall length is even
		}
		
		int medianA = arrayA.length / 2;
		int corresponding = target - medianA;
		
		while (rightA - leftA > 2 || rightB - leftB > 2) {	
			if (arrayA[medianA] == arrayB[corresponding]) {
				break;
			} else if (arrayA[medianA] > arrayB[corresponding]) {
				// case 2:
				rightA = medianA;
				leftB = corresponding;
			} else {
				// case 3:
				leftA = medianA;
				rightB = corresponding;
			}
			
			medianA = findMedianElement(leftA,rightA);
			corresponding = target - medianA;
		}	
		
		// when reach here, its either we already find the meidanA or (rightA - leftA <= 2 && rightB - leftB <= 2)
		if (arrayA[medianA] == arrayB[corresponding]) {
			return arrayA[medianA];
		} else {
			int totalCount = rightA - leftA + rightB - leftB;
			if (totalCount == 4) {
				return (Math.max(arrayA[leftA], arrayB[leftB]) + Math.min(arrayA[rightA], arrayB[rightB])) / 2;
			} else if (totalCount == 3) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				if (leftA == rightA) {
					list.add(arrayA[leftA]);
					list.add(arrayB[leftB]);
					list.add(arrayB[rightB]);
					Collections.sort(list);
					return list.get(1);
				} else {
					list.add(arrayA[leftA]);
					list.add(arrayA[leftA]);
					list.add(arrayB[rightB]);
					Collections.sort(list);
					return list.get(1);
				}
			} else {
				return (arrayA[leftA] + arrayB[leftB]) / 2;
			}
		}
	}
	
	private static int findMedianElement(int left, int right) {
		return left + (right-left)/2;
	}
}
