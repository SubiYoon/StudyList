package string;

public class StringTest2 {
	public static void main(String[] args) {
		String javaStr = new String("Java");
		String androidStr = new String("Android");
		System.out.println(javaStr);
		System.out.println("처음 문자열 주소 값 : " + System.identityHashCode(javaStr));
		
		javaStr = javaStr.concat(androidStr);
		
		System.out.println(javaStr);
		System.out.println("연결된 문자열 주소 값 : " + System.identityHashCode(javaStr));

	}

}
