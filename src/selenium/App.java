package selenium;

import java.util.List;

public class App {

	public static void main(String[] args) {
		Scrapper sc = new Scrapper();
		
		sc.busdbuyurl();
		
		sc.maxwindow();
		
		sc.popupwindow();
		
		List<Order> orderList = sc.GetOrders();
		
//		List<Order> orderList = new ArrayList<Order>();
//		orderList.add(new Order("A", 78.4, 1000.0, 20000.0));
//		orderList.add(new Order("B", 77.2, 15000.0, 30000.0));
//		orderList.add(new Order("C", 78.1, 10000.0, 50000.0));
//		orderList.add(new Order("D", 78.3, 30000.0, 60000.0));
//		orderList.add(new Order("E", 76.8, 25000.0, 40000.0));
		
		sc.getPrice(orderList);
		
		System.out.println("O w n    O r d e r s");
		sc.getOwnOrder(orderList);
	}

}
