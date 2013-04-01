package leetcode;

/*
 * say we have a array of int, int a and int b,  need to divide them into 3 groups
 * group 1: every element < a
 * group 2: a <= x < b
 * group 3: x >= b
 * */
public class DutchFlag {
	public static void dutchFlag(int[] array, int a, int b) {
		int aPosition = 0; // its the leftest position that next to element <= a
		int bPosition = array.length - 1; // its the rightest position that next to the element >= b 
		int i = 0;
		
		while (i <= bPosition) {
			if (array[i] <= a) {
				swap(array, i, aPosition);
				aPosition++;
				i++;
			} else if (array[i] >= b) {
				swap(array, i, bPosition);
				bPosition--;
				// we do not need i++ here because we swap i and bPosition, and bPosition element has been selected yet
			} else {
				i++;
			}
		}
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args){
		int[] array = {5,3,6,2,4,7,9,10};
		dutchFlag(array, 4, 8);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
}
