package question3;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Algorithm {

	private int publicKeyN = 18209;
	private int publicKeyB = 3001;
    ArrayList<Integer> rsaEncrypt = new ArrayList<Integer>();
    ArrayList<Integer> xValues = new ArrayList<Integer>();
	
	public Algorithm() throws FileNotFoundException {
		String fileName = "src/question3/rsa.txt";

		File file = new File(fileName);

        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()) {
            // System.out.println(sc.nextInt());
            rsaEncrypt.add(sc.nextInt());
        }

        sc.close();

        //System.out.println(Arrays.toString(rsaEncrypt.toArray()));

		ArrayList<Integer> factors = primeFactors(publicKeyN);
		
		int p = factors.get(0);
		int q = factors.get(1);
		
		int phi = (p-1) * (q - 1);
		//System.out.println("phi: " + phi);
		int bInverse = multiplicativeInverseAlgorithm(phi, publicKeyB);
		int a = bInverse;
		
		// search for the letters after finding x's of all 
		for(int x : rsaEncrypt) {
			int bg = modulo(x, a, publicKeyN);
			xValues.add(bg);
		}
		
		//System.out.println(Arrays.toString(xValues.toArray()));
		
		// x = c0*26^2 + c1*26 + c2
		String result = "";
		for(int x : xValues) {
			result += calcX(x);
			
		}
		System.out.println(result);
		
		
	}
	
	private String calcX(int x) {
		// TODO Auto-generated method stub
		String result = "";
		int c0,c1,c2 = 0;
		c0 = x / (26*26);
		c1 = (x - 26*26*c0) / 26;
		c2 = x - 26*26*c0 - 26*c1;
		
		result += (char) (c0 + 'A');
		result += (char) (c1 + 'A');
		result += (char) (c2 + 'A');
		//System.out.println(c0 + " : " + c1 + " : " + c2);
		
		return result;
	}

	private int multiplicativeInverseAlgorithm(int a, int b) {
		//System.out.println(a +"," + b);
		int a0 = a, b0 = b, t0 = 0, t = 1, bInverse = 0;
		double q = Math.floor(a0/b0);
		//System.out.println(q);
		double r = a0 - q * b0;
		//System.out.println(r);
		
		while(r > 0) {
			//System.out.println(t);
			double temp = (t0 - q * t) % a;
			t0 = t;
			t = (int) temp;
			a0 = b0;
			b0 = (int) r;
			q = Math.floor(a0/b0);
			r = a0 - q * b0;
			//System.out.println(t);
		}
		
		if (b0 != 1) {
			System.out.println("b has no inverse mod a");
		} else {
			bInverse = t;
			//System.out.println(bInverse);
		}
		
		return bInverse;
		
	}
	
	/**
	 * Taken from Geeks for Geeks 
	 * @param n
	 * @return 
	 */
	private ArrayList<Integer> primeFactors(int n) {
		ArrayList<Integer> factors = new ArrayList<Integer>();
		
		
		while(n % 2 == 0) {
			//System.out.println(2 + " ");
			factors.add(2);
			n /= 2;
		}
		
		for(int i=3; i<=Math.sqrt(n); i+=2) {
			while( n % i == 0) {
				//System.out.println(i + " ");
				factors.add(i);
				
				n /= i;
			}
		}
		
		if(n > 2) {
			//System.out.println(n);
			factors.add(n);
		}
		
		//System.out.println(Arrays.toString(factors.toArray()));
		return factors;
	}
	
	private int modulo(int a,int b,int c) {
	    long x=1;
	    long y=a;
	    while(b > 0){
	        if(b%2 == 1){
	            x=(x*y)%c;
	        }
	        y = (y*y)%c; // squaring the base
	        b /= 2;
	    }
	    return (int) x%c;
	}

}
