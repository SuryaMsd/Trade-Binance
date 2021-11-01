package selenium;

import java.util.List;

public class App {

	public static void main(String[] args) {
		Scrapper sc = new Scrapper();
		
		sc.busdbuyurl();
		
		sc.maxwindow();
		
		sc.popupwindow();
		
		List<Order> buyorderList = sc.GetBuyOrders();
		sc.getBuyPrice(buyorderList);

		sc.busdsellurl();

		List<Order> sellorderList = sc.GetSellOrders();
		sc.getSellPrice(sellorderList);


//		System.out.println("O w n    O r d e r s");
//		sc.getOwnOrder(buyorderList);

		sc.close();
	}

}
