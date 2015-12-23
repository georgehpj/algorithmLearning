package c1.GenericType;

/**
 * ∑∫–Õ¿‡¡∑œ∞
 * @author George
 *
 * @param <T>
 */
public class GenericMeoryCell<T> {
	
	public T read(){
		return storedValue;
	}
	public void write(T t){
		storedValue = t;
	}
	
	private T storedValue;
}

class Test{
	GenericMeoryCell<String> gmc = new GenericMeoryCell<String>();
	public void go(){
		gmc.write("123");
		System.out.println(gmc.read());
	}
}
