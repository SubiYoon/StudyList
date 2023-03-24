package array;

public class ArrayTest3 {

	public static void main(String[] args) {
		double[] data = new double[5];
		int size = 0;
		
		data[0] = 1.0; size++;
		data[1] = 2.0; size++;
		data[2] = 3.0; size++;

		for (int i = 0; i < size; i++) {
			System.out.println(data[i]);
		}
	}

}
