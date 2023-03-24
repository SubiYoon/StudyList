package chapter6;

public class CarFactory {
	private static CarFactory instance = new CarFactory();
	private CarFactory() {}
	
	public static CarFactory getinstance() {
		if(instance == null) {
			instance = new CarFactory();
		}
		return instance;
	}
	public Car createCar() {
		
		Car car = new Car();
		return car;
	}
}

