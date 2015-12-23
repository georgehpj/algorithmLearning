package c1;

public class PrintOut {
	private static int NUM = 8;
	public static void main(String[] args) {
		System.out.println("\n"+getOneNums(NUM, 0));
	}
	public static int getOneNums(int num, int oneNum){
		if(num == 1){
			System.out.print(1);
			return 1 + oneNum;
		}
		if((num&1) == 1){
			System.out.print(1);
			oneNum ++;
		}else{
			System.out.print(0);
		}
		return getOneNums(num/2, oneNum);
	}
}
