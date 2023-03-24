package chapter5;

public class OrderTest {

	public static void main(String[] args) {
		Order person = new Order();
		person.orderName = "홍길동";
		person.orderNumber = 201803120001L;
		person.orderID = "abc123";
		person.orderDate = "2018년 3월 12일";
		person.orderProductNumber = "PD0345-12";
		person.orderadrress = "서울시 영등포구 여의도동 20번지";
		
		System.out.println("주문 번호" + " : " + person.orderNumber);
		System.out.println("주문자 아이디" + " : " + person.orderID);
		System.out.println("주문 날짜" + " : " + person.orderDate);
		System.out.println("주문자 이름" + " : " + person.orderName);
		System.out.println("주문 상품 번호" + " : " + person.orderProductNumber);
		System.out.println("배송 주소" + " : " + person.orderadrress);
	}

}
