package question2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Algorithm {
	
	String[] alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	String cipher = "LGEMWMYIPAGRPPJHEXSWKKLOKXRVOLAVZYQTXICG" + 
			"YKLOKMRVCZBGIXLXITBALVMDKVFRDAGLMXYFZWCA" + 
			"HEXYWQGPYJXJXBSGXIXWPNSBDWJXYKXVOYMMEIGD" + 
			"BWIKFWEIGUBMMVASRXSGGJXYTHCHVQZFARWKVRYG" + 
			"GVLKKZFROTXWSBW";
	double[] exceptedFre = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 	
			0.00153, 0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 
			0.02758, 0.00978, 0.02360, 0.00150, 0.01974, 0.00074}; 
 	HashMap<String, Double> exceptedFrequencies = new HashMap<String, Double>();
	
	public Algorithm() {

		String plainText = "";
		
		
		storeExceptedFreq();
		
		//System.out.println(exceptedFrequencies);
		
		int keyLength = keyLength(cipher);
		System.out.println(keyLength);

		// according that blog, i have keylength caesar cipher to break
		String[] cipherSeq = getAllSequence(keyLength);
		//System.out.println(Arrays.toString(cipherSeq));
		
		String keyword = getKey(cipherSeq, keyLength);
		System.out.println(keyword);
		
			
		
		for(int i=0; i<cipher.length(); i++) {
			int cipherChar = cipher.charAt(i) - 'A';
			int keyChar = keyword.charAt(i % keyword.length()) - 'A';
			int plainChar = ((cipherChar - keyChar) % 26);
			plainChar = plainChar < 0 ? plainChar += 26 : plainChar;
			plainText += (char) (plainChar + 'A');
		}
		
		
		System.out.println(plainText);
		
		
	}
	

	private void storeExceptedFreq() {
		for(int i=0; i<alphabets.length; i++) {
			exceptedFrequencies.put(alphabets[i], exceptedFre[i]);
		}
	}
	
	private String getKey(String[] cipherSeq, int keyLength) {
		// TODO Auto-generated method stub
		double[] chiSquareValues = new double[26];
		int[] indexOfMinimumChi = new int[keyLength];
		int shiftValue;
		
		for(int i=0; i<keyLength; i++) {
			
			// get all the possible 26 shifted sets of the sequences
			for(int j=0; j<26; j++) {
				String shiftedChar = "";
				for(int k=0; k<cipherSeq[i].length(); k++) {
					shiftValue = ((cipherSeq[i].charAt(k) - 'A') + j) % 26 + 'A';
					shiftedChar += (char) shiftValue;
				}
				
				// System.out.println(shiftedChar);
				
				// calculate the chi-squared 
				HashMap<String, Integer> actualFreq = letterFreq(shiftedChar);
				double chiSquareValue = calcChiSquare(actualFreq, shiftedChar);
				
				//System.out.println(chiSquareValue);
				chiSquareValues[j] = chiSquareValue;
				
			}
			
			// find the minimum in chiSquareValues
			indexOfMinimumChi[i] = getMinChi(chiSquareValues);
			
			//break;
		}
		
		
		String result = "";
		for(int i=0; i<indexOfMinimumChi.length; i++) {
			result += alphabets[indexOfMinimumChi[i]];
		}
		
		System.out.println(Arrays.toString(indexOfMinimumChi));
		//System.out.println(result);
		
		return result;
	}


	private int getMinChi(double[] chiSquareValues) {
		// TODO Auto-generated method stub
		double min = Double.MAX_VALUE;
		for(int i=0; i<chiSquareValues.length; i++) {
			if(chiSquareValues[i] < min) {
				min = chiSquareValues[i];
			}
		}
		
		int minIndex = getIndex(chiSquareValues, min);
		
		
		return 26 - minIndex;
	}

	private double calcChiSquare(HashMap<String, Integer> actualFreq, String shiftedChar) {
		// TODO Auto-generated method stub
		double chiValue = 0.0;
		
		// calculate it for every char
		for(String s : actualFreq.keySet()) {
			// Ca is the count in the sequence
			// Ei is the excepted count 
			
			int Ca = actualFreq.get(s);
			double Ei = exceptedFrequencies.get(s) * shiftedChar.length();
			chiValue += Math.pow(Ca - Ei, 2.0) / Ei;
		}
		
		return chiValue;
	}

	private String[] getAllSequence(int keyLength) {
		// TODO Auto-generated method stub
		String[] cipherSeq = new String[keyLength];
		for(int i=0; i<keyLength; i++) {
			String sequence = "";
			for(int j=i; j<cipher.length(); j+=keyLength) {
				sequence += cipher.charAt(j);
			}
			
			cipherSeq[i] = sequence;
		}

		return cipherSeq;
	}


	public int keyLength(String cipherText) {
		double[] avg = new double[5];
		
		for(int i=1; i<=5; i++) {
			double[] m = new double[i];
			//System.out.println();
			for(int j=0; j<i; j++) {
				String sequence = "";

				for(int k=j; k<cipherText.length(); k+=i) {
					sequence += cipherText.charAt(k);
				}
				

				//System.out.println(sequence);
				
				m[j] = calcIC(letterFreq(sequence));
			}
			//System.out.println(Arrays.toString(m));
			// Create a table of average I.C 
			avg[i-1] = calcAvg(m);
			
		}
		
		//System.out.println(Arrays.toString(avg));
				
		// get the max value
		double max = getMax(avg);
		//System.out.println(max);
		
		// get index of max value and that is the keylength
		int keyLength = getIndex(avg, max);
		return keyLength + 1;

 	}
			
	private int getIndex(double[] avg, double max) {
		// TODO Auto-generated method stub
		for(int i=0; i<avg.length; i++) {
			if(avg[i] == max) return i;
		}
		return -1;
	}


	private double getMax(double[] avg) {
		// TODO Auto-generated method stub
		double max = 0;
		for(int i=0; i<avg.length; i++) {
			if(max < avg[i]) 
				max = avg[i];
		}
		return max;
	}


	private double calcAvg(double[] m) {
		// TODO Auto-generated method stub
		double sum = 0;
		for(double d : m) sum += d;
		
		return sum/m.length;
	}


	private HashMap<String, Integer> letterFreq(String sequence) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> frequencyTable = new HashMap<String, Integer>();
		for(int i=0; i<alphabets.length; i++) {
			frequencyTable.put(alphabets[i], 0);
		}
		
		
		for (char c : sequence.toCharArray()) {
			if(frequencyTable.containsKey(String.valueOf(c))) {
				int count = frequencyTable.get(String.valueOf(c));
				frequencyTable.put(String.valueOf(c), count + 1);
			}
		}

		// System.out.println(frequencyTable);
		return frequencyTable;
	}


	public static double calcIC(HashMap<String, Integer> map) {
		double ic = 0;
		double n = 0;
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			n += entry.getValue();
		}
		// apply the IC formula
		
		
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			double top = entry.getValue() * (entry.getValue() - 1);
			double bottom = n * (n - 1);
			ic += top /bottom ;
		}
			
		return ic;
		
	}




}
