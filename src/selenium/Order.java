package selenium;

public class Order{
	String name;
	Double price;
	Double lowerLimit;
	Double upperLimit;
	
	public Order(String name,Double price,Double lowerLimit,Double upperLimit) {
		this.name=name;
		this.price=price;
		this.lowerLimit=lowerLimit;
		this.upperLimit=upperLimit;
	}
	
	public String getName() {return name;}
	
	public double getPrice() {return price;}
	
	public double getLowerLimit() {return lowerLimit;}
	
	public double getUpperLimit() {return upperLimit;}
	

}
