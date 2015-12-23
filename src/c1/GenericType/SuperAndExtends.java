package c1.GenericType;

import java.util.ArrayList;
import java.util.List;

public class SuperAndExtends {
	/**
	 * �����Э���Իᵼ�±��벻�ܷ��ֵ��쳣�����������
	 * @param args
	 */
	public static void errorTest(){
		Number[] nums = new Integer[10];
		nums[9] = 10f;
	}
	
	public <T>  void someLimits(){
//		List<int> firstLimit = new ArrayList<int>();
//		T secondLimit = new T();
//		T[] thirdLimit = new T[10];
		Comparable<Shape> fourthLimit = new Circle(5.5);
//		Comparable<Shape>[] fourthLimits = new Comparable<Circle>[10];
	}
	
	
	/**
	 * 1.Ҫʹ��compareTo����������T����̳�Comparable�ӿڣ�
	 * 2.jdk1.8֮ǰ�����Ͷ���Ҫ���ϸ����ֻ���巺��<T extends Comparable<T>>����41�б���
	 * Circle��Shape���࣬Shapeʵ��Comparable<Shape>��CircleҲʵ����Comparable<Shape>����Circle��δʵ��Comparable<Circle>��
	 * ��Circle extends Comparable<Circle>��������
	 * ��ȷ�����Circle extends Comparable<Shape>��ʵ���У����ǿ��ܲ�֪����Ҳ����Ҫ֪��Circle׼ȷ�ĸ��࣬���ʹ��ͨ���Circle extends Comparable<? super Circle>
	 * ����ɷ���<T extends Comparable<? super T>>
	 * @param arr
	 * @return
	 */
	public static <T extends Comparable<? super T>> T findMax(T[] arr){
		int maxIndex = 0;
		for(int i = 0;i>arr.length;i++){
			if(arr[i].compareTo(arr[maxIndex])>0){
				maxIndex = i;
			}
		}
		return arr[maxIndex];
	}
	
	public static void main(String[] args) {
		Shape[] shapes = new Shape[2];
		shapes[0] = new Square(5.0);
		shapes[1] = new Circle(4.0);
		System.out.println(findMax(shapes).getSquare());
		
		Circle[] circles = new Circle[2];
		circles[0] = new Circle(4.0);
		circles[1] = new Circle(5.0);
		System.out.println(findMax(circles).getSquare());
	}
}

//����Shape
abstract class Shape implements Comparable<Shape>{
	public int compareTo(Shape shape) {
		return this.getSquare().compareTo(shape.getSquare());
	}
	abstract public Double getSquare();
}

class Circle extends Shape{
	private Double r;
	public Circle(Double r){
		this.r = r;
	}
	@Override
	public Double getSquare() {
		Double square = Math.PI*r*r;
		System.out.println("Square of the circle("+r+") is "+square);
		return square;
	}
}

class Square extends Shape{
	private Double r;
	public Square(Double r){
		this.r = r;
	}
	@Override
	public Double getSquare() {
		Double square = r*r;
		System.out.println("Square of the square("+r+") is "+square);
		return square;
	}
}
