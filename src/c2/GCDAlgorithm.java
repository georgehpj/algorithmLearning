package c2;

/**
 * ŷ������㷨
 * �����������������Լ���������н�С���Ǹ�����������������������Լ����
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
