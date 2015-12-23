package c2;

/**
 * 欧几里得算法
 * 定理：两个整数的最大公约数等于其中较小的那个数和两数的相除余数的最大公约数。
 * @author George
 *
 */
public class GCDAlgorithm {
	public static void main(String[] args) {
		System.out.println(gcd(6, 3));
	}

	public static long gcd(long m, long n){
		while(n != 0){
			long temp = m%n;
			m = n;
			n = temp;
		}
		return n;
	}
}
