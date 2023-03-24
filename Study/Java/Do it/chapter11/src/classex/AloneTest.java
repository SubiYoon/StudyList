package classex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AloneTest {

	public static void main(String[] args) {
		Class pClass = Person.class;
		Constructor[] con = pClass.getConstructors();
		for(Constructor c : con) {
			System.out.println(c);
		}
		
		System.out.println();
		Method[] method = pClass.getMethods();
		for(Method m : method) {
			System.out.println(m);
		}
		
		System.out.println();
		Field[] field = pClass.getFields();
		for(Field f : field) {
			System.out.println(f);
			System.out.println(f.getName());
		}
	}

}
