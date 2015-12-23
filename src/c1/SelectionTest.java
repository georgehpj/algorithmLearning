package c1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
/**
 * ��N�������л�ȡ��M�������
 * ����һ��ð�����򷨶�N������������ȡ���ڵ�M��λ�õ�����
 * ��������N�����ֵ�ǰM����������������б��У�����ʣ���������������б������ֱȶԺ��������ȷλ���ϣ�����������ȡ���б������һλ
 * ��������ȡ���λ�õ����֣������б��е���������ȶԣ��ϴ�ķ���һ���б���С�ķ�����һ���б���Ϊ�����б��С���б������б��жϴ����б���L
 * �������M����Ӵ����б����ٻ�ȡ���λ�õ������ظ����ϲ��������С��M�����С���б��л�ȡ���λ�������ظ����ϲ������ֳ�������С���б�󣬳�����M-L���Ƚϡ�
 * �����Գ���С�ڵ���7�Ĵ�����С���б�Ϊ�սᣬ��ʣ���7��������ð������󣬺�ȥ���һ��M-L��λ�õ����֡�
 * 
 *�������ַ�����ʱ�临�ӶȺͿռ临�Ӷȣ�
 * @author George
 *
 */
public class SelectionTest {
	private static final int TOTAL_NUM = 10000000;
	private static final int LOCATION = 5000000;

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static void main(String[] args) {
		long startTime;
		long endTime;
		float seconds;
		Random r = new Random();
//		Integer[] nums = new Integer[TOTAL_NUM];
//		for (int i = 0; i < TOTAL_NUM; i++) {
//			nums[i] = r.nextInt(TOTAL_NUM);
//		}
		
//		System.out.println("��"+LOCATION+"�������ǣ�"+getLocationNum(nums)[LOCATION-1]);
		
		//�����������
		List<Integer> numList = new ArrayList<Integer>();
		for (int i = 0; i < TOTAL_NUM; i++) {
			numList.add(r.nextInt(TOTAL_NUM));
		}
		
//		//����һ
//		startTime = System.currentTimeMillis();
//		List orderedList = maopao(numList);
//		System.out.print("\n�Ӵ�С����");
//		for (int i = 0; i < LOCATION; i++) {
//			System.out.print(orderedList.get(i)+";");
//		}
//		System.out.println("\n��"+LOCATION+"�������ǣ�"+orderedList.get(LOCATION-1));
//		
//		endTime = System.currentTimeMillis();
//		seconds = (endTime - startTime) / 1000F;
//		System.out.println("����һ��ʱ"+Float.toString(seconds) + " seconds.");
//		
//		//������
//		startTime = System.currentTimeMillis();
//		System.out.println("\n��"+LOCATION+"�������ǣ�"+getLocationNum(numList).get(LOCATION-1));
//		
//		endTime = System.currentTimeMillis();
//		seconds = (endTime - startTime) / 1000F;
//		System.out.println("��������ʱ"+Float.toString(seconds) + " seconds.");
//		
		
		//������
		startTime = System.currentTimeMillis();

		System.out.println("\n��"+LOCATION+"�������ǣ�"+elseFunc(numList, LOCATION));
		
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000F;
		System.out.println("��������ʱ"+Float.toString(seconds) + " seconds.");
	}
	
	private static Integer[] getLocationNum(Integer[] nums){
		Integer[] smallNums = Arrays.copyOf(nums, LOCATION);
		smallNums = maopao(smallNums);
		for(int i = LOCATION; i < nums.length; i++){
			int curLoc = -1;
			for(int j =  LOCATION-1; j >= 0 ; j--){
				if(smallNums[j] >= nums[i])break;
				else curLoc = j;
			}
			if(curLoc >= 0){
				for(int n = LOCATION-1 ; n > curLoc ; n--){
					smallNums[n] = smallNums[n-1];
				}
				smallNums[curLoc] = nums[i];
			}
		}
		return smallNums;
	}
	
	private static List<Integer> getLocationNum(List<Integer> nums){
		List<Integer> smallNums = new ArrayList<Integer>();
		for(int i=0;i<LOCATION;i++){
			smallNums.add(nums.get(i));
		}
		smallNums = maopao(smallNums);
		for(int i = LOCATION; i < nums.size(); i++){
			int curLoc = -1;
			for(int j =  LOCATION-1; j >= 0 ; j--){
				if(smallNums.get(j) >= nums.get(i))break;
				else curLoc = j;
			}
			if(curLoc >= 0){
				for(int n = LOCATION-1 ; n > curLoc ; n--){
					smallNums.set(n, smallNums.get(n-1));
				}
				smallNums.set(curLoc, nums.get(i));
			}
		}
		return smallNums;
	}

	private static List<Integer> maopao(List<Integer> nums){
		List<Integer> orderedNums = new ArrayList<Integer>(nums);
		for (int i = 0; i < orderedNums.size(); i++) {
			for (int j = i + 1; j < orderedNums.size(); j++) {
				if (orderedNums.get(j) > orderedNums.get(i)) {
					orderedNums.set(i, orderedNums.get(i)^orderedNums.get(j));
					orderedNums.set(j, orderedNums.get(i)^orderedNums.get(j));
					orderedNums.set(i, orderedNums.get(i)^orderedNums.get(j));
				}
			}
		}
		return orderedNums;
	}
	
	private static Integer[] maopao(Integer[] nums) {
		Integer[] orderdNums = Arrays.copyOf(nums, nums.length);
		for (int i = 0; i < orderdNums.length; i++) {
			for (int j = i + 1; j < orderdNums.length; j++) {
				if (orderdNums[j] > orderdNums[i]) {
					orderdNums[i] ^= orderdNums[j];
					orderdNums[j] ^= orderdNums[i];
					orderdNums[i] ^= orderdNums[j];
				}
			}
		}
		return orderdNums;
	}
	
	private static Integer elseFunc(List<Integer> numList, int location){
		if(numList.size()<=7){
			List<Integer> orderedList = maopao(numList);
			return orderedList.get(location-1);
		}
		Random r = new Random();		
		Integer randomNum = numList.get(r.nextInt(numList.size()-1));
		List<Integer> smallNums = new ArrayList<Integer>(); 
		List<Integer> bigNums = new ArrayList<Integer>(); 
		for(Integer num : numList){
			if(num >= randomNum){
				bigNums.add(num);
			}else
				smallNums.add(num);
		}
		if(bigNums.size() >= location){
			return elseFunc(bigNums, location);
		}else{
			return elseFunc(smallNums, location-bigNums.size());
		}
	}
}
