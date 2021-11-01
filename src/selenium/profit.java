package selenium;
import java.util.Arrays;

public class profit {
	public void Permutation(){
		int max = 10;
		int split = Config.Split_orders;
		int maxvalue = max - split + 1;
		int a[] = new int[split];

		Arrays.fill(a, 1);
		do {
			a=increase(a,maxvalue);
			if(sum(a)==max) {
				System.out.println(a[0] +"-"+a[1] +"-"+a[2] +"-"+a[3]);
			}
		} while(validateEndPoint(a,maxvalue));
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