package selenium;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Scrapper {
	ChromeDriver driver;
	ChromeOptions options;
	
	public Scrapper() {
		options = new ChromeOptions();
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		setsystemproperties();
	}

	public void close(){
		driver.close();
	}

	public void setsystemproperties() {
		String driverlink="C:\\\\WebDriver\\\\bin\\\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",driverlink);
	}
	
	public void busdbuyurl() {
		String busdbuyurl="https://p2p.binance.com/en/trade/buy/BUSD?fiat=INR&payment=ALL";
		driver.get(busdbuyurl);
	}

	public void busdsellurl() {
		String busdsellurl="https://p2p.binance.com/en/trade/sell/BUSD?fiat=INR&payment=ALL";
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(busdsellurl);
	}

	public void maxwindow() {
		driver.manage().window().maximize();
	}
	
	public void clickNextPage() {
		String nextpage = "//*[@id=\"__APP\"]/div[2]/main/div[5]/div/div[3]/div/button[5]";
		driver.findElementByXPath(nextpage).click();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void popupwindow() {
		String popuplink="css-1pcqseb";
		if (driver.findElementByClassName(popuplink).isDisplayed())
			driver.findElementByClassName(popuplink).click();
	}
	
	public void getOwnOrder(List<Order> orderList) {
		for(Order order:orderList) {
			if(order.name.equalsIgnoreCase(Config.own_buyer_name1) || order.name.equalsIgnoreCase(Config.own_buyer_name2)) {
				System.out.println(order.name +" : "+ order.price+" : "+order.lowerLimit+"-"+order.upperLimit);

			}
		}
	}
	
	public String XpathString(String s, int i) {
		if(s=="name") { 
			return "/html/body/div[1]/div[2]/main/div[5]/div/div[2]/div["+i+"]/div[1]/div[1]/div/div[1]/a";}
			else if(s=="price") {
				return "//*[@id=\"__APP\"]/div[2]/main/div[5]/div/div[2]/div["+ i +"]/div[1]/div[2]/div/div/div[1]";}
			else if(s=="lower") {
				return "//*[@id=\"__APP\"]/div[2]/main/div[5]/div/div[2]/div["+i+"]/div[1]/div[3]/div/div[2]/div[2]/div[1]";}
				else {
					return "//*[@id=\"__APP\"]/div[2]/main/div[5]/div/div[2]/div["+i+"]/div[1]/div[3]/div/div[2]/div[2]/div[3]";
				}
	}

	public double stringtoDouble(String money1) {
		String price=money1.substring(2);
		price = price.replaceAll(",", "").trim();
		double money = Double.parseDouble(price);
		return money;
	}
	
	List <Order> GetBuyOrders(){
		List<Order> buyorderList = new ArrayList<>();
		for(int i=1;i<=10;i++) {
			WebElement webElementName = driver.findElement(By.xpath(XpathString("name",i)));
			String name= webElementName.getText();
			WebElement webElementPrice = driver.findElement(By.xpath(XpathString("price",i)));
			Double price=Double.parseDouble(webElementPrice.getText());
			WebElement webElementLowerLimit = driver.findElement(By.xpath(XpathString("lower",i)));
			Double lowerLimit=stringtoDouble(webElementLowerLimit.getText());
			WebElement webElementUpperLimit = driver.findElement(By.xpath(XpathString("upper",i)));
			Double upperLimit=stringtoDouble(webElementUpperLimit.getText());
			Order order = new Order(name,price,lowerLimit,upperLimit);
			buyorderList.add(order);
		}
		return buyorderList;
	}

	List <Order> GetSellOrders(){
		List<Order> sellorderList = new ArrayList<>();
		for(int i=1;i<=10;i++) {
			WebElement webElementName = driver.findElement(By.xpath(XpathString("name",i)));
			String name= webElementName.getText();
			WebElement webElementPrice = driver.findElement(By.xpath(XpathString("price",i)));
			Double price=Double.parseDouble(webElementPrice.getText());
			WebElement webElementLowerLimit = driver.findElement(By.xpath(XpathString("lower",i)));
			Double lowerLimit=stringtoDouble(webElementLowerLimit.getText());
			WebElement webElementUpperLimit = driver.findElement(By.xpath(XpathString("upper",i)));
			Double upperLimit=stringtoDouble(webElementUpperLimit.getText());
			Order order = new Order(name,price,lowerLimit,upperLimit);
			sellorderList.add(order);
		}
		return sellorderList;
	}

	public void getBuyPrice(List<Order> orderList) {
		System.out.println("Buy Orders");
		normalizeLowerUpperBoundaries(orderList);
		System.out.println("Best Buy Price");
		getBestBuyPrice(orderList);
	}

	public void getSellPrice(List<Order> orderList) {
		System.out.println("Sell Orders");
		normalizeLowerUpperBoundaries(orderList);
		System.out.println("Best Sell Price");
		getBestSellPrice(orderList);
	}

	public void normalizeLowerUpperBoundaries(List<Order> orderList) {
		for(Order order:orderList) {
			order.lowerLimit=order.lowerLimit/Config.min_order_price;
			order.lowerLimit=(double) Math.round(order.lowerLimit);
			order.lowerLimit=(order.lowerLimit*Config.min_order_price);
	//		if(order.lowerLimit!=0) {
	//			order.lowerLimit=order.lowerLimit-1;
	//		}
			order.upperLimit=order.upperLimit/Config.min_order_price;
			order.upperLimit=(double) Math.round(order.upperLimit);
			order.upperLimit=(order.upperLimit*Config.min_order_price);
			System.out.println(order.name +" : "+ order.price+" : "+order.lowerLimit+"-"+order.upperLimit);
		}
	}
	
	public void getBestBuyPrice(List<Order> orderList) {
		Map <Integer,Double> buylimitprice = new HashMap<Integer,Double>();
		for(Order order:orderList) {
			for(int i=Config.min_order_price;i<=Config.max_order_price;i+=Config.min_order_price) {
				if(order.lowerLimit<=i && order.upperLimit>=i) {
					if(buylimitprice.containsKey(i)) {
						Double p=buylimitprice.get(i);
						if(p>order.price) {
							buylimitprice.replace(i, order.price);
						}	
					}
					else {
						buylimitprice.put(i, order.price);
					}
				}
			}
		}
		for(Map.Entry<Integer,Double> entry: buylimitprice.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
	}

	public void getBestSellPrice(List<Order> orderList) {
		Map <Integer,Double> selllimitprice = new HashMap<Integer,Double>();
		for(Order order:orderList) {
			for(int i=Config.min_order_price;i<=Config.max_order_price;i+=Config.min_order_price) {
				if(order.lowerLimit<=i && order.upperLimit>=i) {
					if(selllimitprice.containsKey(i)) {
						Double p=selllimitprice.get(i);
						if(p>order.price) {
							selllimitprice.replace(i, order.price);
						}
					}
					else {
						selllimitprice.put(i, order.price);
					}
				}
			}
		}
		for(Map.Entry<Integer,Double> entry: selllimitprice.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
	}
}
	
	
	
	