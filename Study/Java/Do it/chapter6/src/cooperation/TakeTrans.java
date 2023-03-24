package cooperation;

public class TakeTrans {

	public static void main(String[] args) {
		Student studentJame = new Student("James", 5000);
		Student studentTomas = new Student("Tomas", 10000);
		Student studentEdward = new Student("Eduward", 30000);
		
		Bus bus100 = new Bus(100);
		studentJame.takeBus(bus100);
		studentJame.showInfo();
		bus100.showInfo();
		
		Subway subwayGreen = new Subway("2호선");
		studentTomas.takeSubway(subwayGreen);
		studentTomas.showInfo();
		subwayGreen.showInfo();
		
		Taxi taxiNormal = new Taxi("일반택시");
		studentEdward.takeTaxi(taxiNormal);
		studentEdward.showInfo();
		taxiNormal.taxiInfo();
		
	}

}
