package c2;

/**
 * 不使用迭代实现指数计算
 * @author George
 *
 */
public class Test23 {
	public static void main(String[] args) {
		System.out.println(square(2, 13));
	}
	
	private static long square(int n, int i){
		long square = 1;
		int m = i;
		while(m > 0){
			if((m & 1) == 1){
				System.out.println("square = " +square+"*"+n+"="+(square *= n));
			}
			System.out.println("n="+n);
			n *= n;
			System.out.println("n="+n);
			m >>= 1;
			System.out.println("m="+m);
		}
		return square;
	}
}
