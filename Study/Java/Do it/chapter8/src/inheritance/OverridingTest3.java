package inheritance;

public class OverridingTest3 {

	public static void main(String[] args) {
		int price = 10000;
		
		Customer customerLee = new Customer(10010, "이순신");
		System.out.println(customerLee.getCustomerName() + " 님의 지불하실 금액은 " + customerLee.calcPrice(price) + "원 입니다.");
		
		VIPCustomer customerKim = new VIPCustomer(10020, "김유신", 12345);
		System.out.println(customerKim.getCustomerName() + " 님의 지불하실 금액은 " + customerKim.calcPrice(price) + "원 입니다.");
		
		Customer vc = new VIPCustomer(10030, "나몰라", 2000);
		System.out.println(vc.getCustomerName() + " 님의 지불하실 급액은 " + vc.calcPrice(price) + "원 입니다.");

		
	}

}
