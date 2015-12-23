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
	 * ��С�����к�
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
	 * ��С�������кͣ�δ��ɣ�
	 * ˼·��������С�������к�Ϊλ�ô�m��nֱ�ӵ�����֮�ͣ��ɽ����ת��Ϊ��sum(int[0~n]) - sum(int[0~(m-1)])����С����ֵ������n>=m
	 * 1.����ۼ������и���֮�ͣ�ÿ��һ���������ϼƼ�¼��������sumArrys[]��
	 * 2.��sumArrys[]����ͬʱ��������ǰ��������ԭsumArrys[]�е�λ�á���δ��ɣ���ʱ�뷢��sumArrys[]�и����ֺ�����λ�÷���map�У�keyΪλ�ã�valueΪ���֣�
	 * 	����comparator�Ƚ�value��С��Ȼ��map�Ž�ArrayList�е���sort��������
	 * 3.�����Ƚ������sumArrys[]��������������ԭʼλ��ǰ��˳����ԭʼλ��ǰ��˳����ͬ����������֮���ԭʼλ��ǰ��˳���෴����������ȣ���������ԭʼλ�á�
	 * 4.��ȡ��С������ֵ����С�������к͡�
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
