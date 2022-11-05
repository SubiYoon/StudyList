package array;

public class ArrayTest2 {

	public static void main(String[] args) {
		double[] data = new double[5];
		
		data[1] = 1.0;
		data[2] = 2.0;
		data[3] = 3.0;
		
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}

}
