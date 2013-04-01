package leetcode;

/*
 * Implement pow(x, n). 
 * */
public class PowXToTheN {
	public static double pow(double x, int n) {
		if (n == 0) {
			return 1;
		} else if (n < 0) {
			return 1 / doPow(x, Math.abs(n));
		} else {
			return doPow(x, n);
		}
	}

	public static double doPow(double x, int n) {
		if (n == 0) {
			return 1;
		} else {
			double temp = doPow(x, n / 2);
			if (n % 2 == 0) {
				return temp * temp;
			} else {
				return x * temp * temp;
			}
		}
	}
}
