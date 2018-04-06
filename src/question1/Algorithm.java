package question1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
		HashMap<String, Integer> frequencyTableDigraphs =  new HashMap<String, Integer>();
	
		for(int i=0; i<alphabets.length; i++) {
			frequencyTable.put(alphabets[i], 0);
		}
		
		
		for (char c : cipher.toCharArray()) {
			if(frequencyTable.containsKey(String.valueOf(c))) {
				int count = frequencyTable.get(String.valueOf(c));
				frequencyTable.put(String.valueOf(c), count + 1);
			}
		}
				
		System.out.println(frequencyTable);
		String newCipher = cipher;
		
		String maxKey = maxValue(frequencyTable).getKey();
		newCipher = replaceKey(cipher, maxKey, "E", newCipher);
		
		System.out.println(cipher);
		System.out.println(newCipher);
		
		
		
		

		
		
	}
	
	private Entry<String, Integer> maxValue(HashMap<String, Integer> map) {
		Map.Entry<String, Integer> maxEntry = null;
		
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		
		System.out.println(maxEntry);
		return maxEntry;
		
	}
	
	private String replaceKey(String cipher, String key, String replaceL, String newCipher) {
		for(int i=0; i<cipher.length(); i++) {
			if(cipher.charAt(i) == key.charAt(0)) {
				newCipher = cipher.replace(cipher.charAt(i), replaceL.charAt(0));
			}else {
				newCipher = cipher.replace(cipher.charAt(i), "-".charAt(0));
			}
		}
		
		return newCipher;
	}

}
