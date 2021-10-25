package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

public class Giottus {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		
		String[] buying = new String[10];
		String[] buystring = new String[10];
		Float[] buyvalue = new Float[10];
		String[] selling = new String[10];
		String[] sellstring = new String[10];
		Float[] sellvalue = new Float[10];
				
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.giottus.com/tradeview?basecoin=INR&paircoin=USDT/INR");
		
		driver.manage().window().maximize();
		
		for(int i=1;i<=10;i++) {
			String path="//*[@id=\"table_2\"]/tbody/tr["+i+"]/td[3]";
			buying[i-1] = driver.findElementByXPath(path).getText();
			buystring[i-1]=buying[i-1].substring(0, 6);
			buyvalue[i-1]=Float.parseFloat(buystring[i-1]);
			String path2="//*[@id=\"table_3\"]/tbody/tr["+i+"]/td[3]";
			selling[i-1] = driver.findElementByXPath(path2).getText();
			sellstring[i-1]=selling[i-1].substring(0, 6);
			sellvalue[i-1]=Float.parseFloat(sellstring[i-1]);			
		}
		
		for(int i=1;i<=10;i++) {
			System.out.println("Buy Price :"+i);
			System.out.println(buyvalue[i-1]);
			System.out.println("Sell Price :"+i);
			System.out.println(sellvalue[i-1]);
		}
		
		Double by =buyvalue[0]+0.001;
		String bys=Double.toString(by);
		driver.findElementByClassName("orderPriceInput").clear();
		driver.findElementByClassName("orderPriceInput").sendKeys(bys);
	}

}
