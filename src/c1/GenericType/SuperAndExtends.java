package c1.GenericType;

import java.util.ArrayList;
import java.util.List;

public class SuperAndExtends {
	/**
	 * 数组的协变性会导致编译不能发现的异常情况，如下例
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
	 * 1.要使用compareTo方法，泛型T必须继承Comparable接口；
	 * 2.jdk1.8之前，泛型定义要求严格，如果只定义泛型<T extends Comparable<T>>，第41行报错。
	 * Circle是Shape子类，Shape实现Comparable<Shape>，Circle也实现了Comparable<Shape>，但Circle并未实现Comparable<Circle>，
	 * 即Circle extends Comparable<Circle>不成立。
	 * 正确情况是Circle extends Comparable<Shape>，实际中，我们可能不知道、也不需要知道Circle准确的父类，因此使用通配符Circle extends Comparable<? super Circle>
	 * 最后变成泛型<T extends Comparable<? super T>>
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

//父类Shape
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
