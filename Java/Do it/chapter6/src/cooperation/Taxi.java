package cooperation;

public class Taxi {
	String taxiName;
	int passengerCount;
	int money;
	
	public Taxi(String taxiName) {
		this.taxiName = taxiName;
	}
	
	public void take(int money) {
		this.money += money;
		passengerCount++;
	}
	
	public void taxiInfo() {
		System.out.println(taxiName + "의 승객은 " + passengerCount + "명이고, 수입은" + money + "원 입니다.");
	}
}
