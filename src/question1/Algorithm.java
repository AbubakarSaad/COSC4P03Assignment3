package question1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Algorithm {
	
	
	public Algorithm() {
		String cipher = "DYDCFWGKDIOIHPDQDXTFKKGZGXCWFPGCADWCCYKD" + 
"UDRMHKIVFADOOGRZFCGAFQGTIRYAGDKHDKCYQDPY" + 
"IKLFMFDRCYYAGGQDXMFXFPYDPGVWDKGOEKDRMYAG" +
"ZFYYXGKGZGXCWDGCVFRFMGOYICYGFXCGPKGYWXFR" +
"CYIYAGGVWDKGCEXYDVFYGTGFWIRYAGOGFYACYFKF" +
"RFKVIKGOCWFPGCYFYDIRTDYAGRIEMAWITGKYIOGC" +
"YKILFRGRYDKGWXFRGYWEKCEGOZLYAGGVWDKGCCDR" +
"DCYGKFMGRYCWKDRPGCCXGDFKFPGCAIVGFZIFKOAG" +
"KCYFKCADWPECYIODFRIHYAGCYIXGRWXFRCYAFYPF" +
"RCFQGAGKWGIWXGFROKGCYIKGHKGGOIVYIYAGMFXFJL";
		
		String[] alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		
		HashMap<String, Integer> frequencyTable = new HashMap<String, Integer>();
	
		for(int i=0; i<alphabets.length; i++) {
			frequencyTable.put(alphabets[i], 0);
		}
		
		System.out.println(frequencyTable);
		
		for (char c : cipher.toCharArray()) {
			if(frequencyTable.containsKey(String.valueOf(c))) {
				int count = frequencyTable.get(String.valueOf(c));
				frequencyTable.put(String.valueOf(c), count + 1);
			}
		}
				
		System.out.println(frequencyTable);
		
		String cipherZ = cipher.replaceAll("G","E");
		System.out.println(cipherZ);
		maxValue(frequencyTable);
	}
	
	private void maxValue(HashMap<String, Integer> map) {
		Map.Entry<String, Integer> maxEntry = null;
		
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		
		System.out.println(maxEntry);
		
	}

}
