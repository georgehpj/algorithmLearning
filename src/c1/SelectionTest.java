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
 * 从N个数字中获取第M大的数字
 * 方法一用冒泡排序法对N个数字排序后获取排在第M个位置的数字
 * 方法二将N个数字的前M个数字排序后方在新列表中，遍历剩余其他数字与新列表中数字比对后放置在正确位置上，遍历结束后取新列表中最后一位
 * 方法三获取随机位置的数字，遍历列表中的数字与其比对，较大的放在一个列表，较小的放在另一个列表，分为大数列表和小数列表两个列表。判断大数列表长度L
 * 如果大于M，则从大数列表中再获取随机位置的数字重复以上操作；如果小于M，则从小数列表中获取随机位置数字重复以上操作，分出大数和小数列表后，长度与M-L做比较。
 * 遍历以长度小于等于7的大数或小数列表为终结，对剩余的7个数字做冒泡排序后，后去最后一次M-L的位置的数字。
 * 
 *计算三种方法的时间复杂度和空间复杂度？
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
		
//		System.out.println("第"+LOCATION+"个数字是："+getLocationNum(nums)[LOCATION-1]);
		
		//生成随机序列
		List<Integer> numList = new ArrayList<Integer>();
		for (int i = 0; i < TOTAL_NUM; i++) {
			numList.add(r.nextInt(TOTAL_NUM));
		}
		
//		//方法一
//		startTime = System.currentTimeMillis();
//		List orderedList = maopao(numList);
//		System.out.print("\n从大到小排序：");
//		for (int i = 0; i < LOCATION; i++) {
//			System.out.print(orderedList.get(i)+";");
//		}
//		System.out.println("\n第"+LOCATION+"个数字是："+orderedList.get(LOCATION-1));
//		
//		endTime = System.currentTimeMillis();
//		seconds = (endTime - startTime) / 1000F;
//		System.out.println("方法一耗时"+Float.toString(seconds) + " seconds.");
//		
//		//方法二
//		startTime = System.currentTimeMillis();
//		System.out.println("\n第"+LOCATION+"个数字是："+getLocationNum(numList).get(LOCATION-1));
//		
//		endTime = System.currentTimeMillis();
//		seconds = (endTime - startTime) / 1000F;
//		System.out.println("方法二耗时"+Float.toString(seconds) + " seconds.");
//		
		
		//方法三
		startTime = System.currentTimeMillis();

		System.out.println("\n第"+LOCATION+"个数字是："+elseFunc(numList, LOCATION));
		
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000F;
		System.out.println("方法三耗时"+Float.toString(seconds) + " seconds.");
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
