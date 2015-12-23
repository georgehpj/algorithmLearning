package c2;

import java.util.Random;

public class Test17 {
	private static final int TOTAL_NUM = 15;

	public static void main(String[] args) {
		Random r = new Random();
		int[] arrys = new int[TOTAL_NUM];
		for (int i = 0; i < TOTAL_NUM; i++) {
			arrys[i] = r.nextInt(TOTAL_NUM) - TOTAL_NUM / 2;
			System.out.print(arrys[i] + ";");
		}

		System.out.println("\n" + minChildArraySum(arrys));
	}

	/**
	 * 最小子序列和
	 * 
	 * @param arrys
	 * @return
	 */
	public static int minChildArraySum(int[] arrys) {
		int minSum = arrys[0];
		if (arrys.length > 1) {
			int curSum = 0;
			for (int i = 1; i < arrys.length; i++) {
				int num = arrys[i];
				curSum += num;
				if (curSum < minSum) {
					minSum = curSum;
				} else if (curSum >= 0) {
					curSum = 0;
				}
			}
		}
		return minSum;
	}

	/**
	 * 最小正子序列和（未完成）
	 * 思路：假设最小正子序列和为位置从m到n直接的数字之和，可将结果转换为求sum(int[0~n]) - sum(int[0~(m-1)])的最小正数值，其中n>=m
	 * 1.逐个累计数组中各项之和，每加一个数，将合计记录到新数组sumArrys[]中
	 * 2.将sumArrys[]排序，同时保留排序前各数字在原sumArrys[]中的位置。（未完成，暂时想发将sumArrys[]中各数字和所在位置放在map中，key为位置，value为数字，
	 * 	增加comparator比较value大小，然后将map放进ArrayList中调用sort方法排序）
	 * 3.遍历比较排序后sumArrys[]中两个相邻数字原始位置前后顺序，若原始位置前后顺序相同，计算两数之差；若原始位置前后顺序相反且量数字相等，交换两数原始位置。
	 * 4.获取最小正数差值即最小正子序列和。
	 * @param arrys
	 * @return
	 */
	private static int minPosChildArraySum(int[] arrys) {
		int sumArrys[] = new int[arrys.length];
		int sumArrysIndex[] = new int[arrys.length];
		int sum = 0;
		for (int i = 0; i < arrys.length; i++) {
			sum += arrys[i];
			sumArrys[i] = sum;
		}

		// sort sumArrys

		int minMinusNum = sumArrys[0];
		for (int i = 1; i < sumArrys.length; i++) {
			if (sumArrysIndex[i] > sumArrysIndex[i - 1]) {
				int minusNum = sumArrys[i] - sumArrys[i-1];
				if(minusNum < minMinusNum){
					minMinusNum = minusNum;
					if(minMinusNum == 1){
						return minMinusNum;
					}
				}
			}else if( sumArrys[i] == sumArrys[i-1]){
				sumArrysIndex[i] ^= sumArrysIndex[i-1];
				sumArrysIndex[i-1] ^= sumArrysIndex[i];
				sumArrysIndex[i] ^= sumArrysIndex[i-1];
			}
		}
		
		if(minMinusNum < 0){
			return -1;
		}
		
		return minMinusNum;
	}
}
