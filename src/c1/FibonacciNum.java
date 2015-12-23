package c1;

import java.util.ArrayList;
import java.util.List;

/**
 * ·Æ²¨ÄÇÇÐÊýÁÐÁ·Ï°
 * @author George
 *
 */
public class FibonacciNum {
	public static void main(String[] args) {
		long[] nums = returnNums(100);
		for(long num : nums){
			System.out.println(num+",");
		}
	}

	private static long[] returnNums(int k) {
		long[] nums = new long[k];
		nums[0] = 1;
		if (k == 1)
			return nums;
		nums[1] = 1;
		if (k == 2)
			return nums;
		for (int i = 2; i < k; i++) {
			nums[i] = nums[i-1]+nums[i-2];
		}
		return nums;
	}

}
