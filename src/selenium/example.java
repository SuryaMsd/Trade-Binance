package selenium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class example {

	public static void main(String[] args) {
		
		int max=10;
		int split=4;
		int maxvalue=max-split+1;
		int a[] = new int[split];
		
		Map <Integer,Double> limitprice = new HashMap<Integer,Double>();
		limitprice.put(10000, 78.25);
		limitprice.put(20000, 78.27);
		limitprice.put(30000, 78.27);
		limitprice.put(40000, 78.27);
		limitprice.put(50000, 78.27);
		limitprice.put(60000, 78.27);
		limitprice.put(70000, 78.27);
		limitprice.put(80000, 78.27);
		limitprice.put(90000, 78.27);
		limitprice.put(100000, 78.27);
		
		Arrays.fill(a, 1);
		do {
			a=increase(a,maxvalue);
			if(sum(a)==max) {
				System.out.println(a[0] +"-"+a[1] +"-"+a[2] +"-"+a[3]);
				getProfit(a, limitprice);
			}
		} while(validateEndPoint(a,maxvalue));		
	}

	private static void getProfit(int[] a, Map<Integer, Double> lmtprice) {

	}

	private static boolean validateEndPoint(int[] a, int maxvalue) {
		for(int i=0;i<a.length;i++) {
			if(a[i]!=maxvalue) {
				return true;	
			}
		}
		return false;
	}

	private static int[] increase(int[] a, int maxvalue) {
		for(int i=a.length-1;i>=0;i--) {
			if(a[i]==maxvalue) 
				a[i]=1;
			else {
				a[i]++;
				return a;
			}
		}
		return a;
	}
	
	public static Integer sum (int[] a) {
		int sum=0;
		for(int i=0;i<a.length;i++) {
			sum=sum+a[i];
		}
		return sum;
	}
	
	
}
