package leetcode;

public class TwoSum {
	 public static int[] twoSum(int[] numbers, int target) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
		 	int[] result = new int[2];
		 	result[0] = -1;
	 		result[1] = -1;
		 	if (numbers == null || numbers.length <= 1) {
		 		return result;
		 	}
		 	
	        for (int start = 0; start < numbers.length; start++) {
	        	for(int end = start+1; end < numbers.length; end++) {
	        		if (numbers[start] + numbers[end] == target) {
	        			result[0] = start+1;
	        			result[1] = end+1;
	        			break;
	        		}
	        	}
	        }
	        
	        return result;
	 }
	 
	 public static void main(String[] args) {
		 int[] numbers = {2,7,11,15};
		 int[] result = twoSum(numbers, 9);
		 for (int i = 0; i < result.length; i++) {
			 System.out.print(result[i] + " ");
		 }
	 }
}
