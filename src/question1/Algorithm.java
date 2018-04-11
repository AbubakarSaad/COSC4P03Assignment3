package question1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
		String key = "ETASRIONHPLDCMGUBFVWYXK---";
	
		for(int i=0; i<alphabets.length; i++) {
			frequencyTable.put(alphabets[i], 0);
		}
		
		StringBuilder text = new StringBuilder(cipher.length());
		
		for(int i=0; i<cipher.length(); i++) {
			text.append("-");
		}
		
		for (char c : cipher.toCharArray()) {
			if(frequencyTable.containsKey(String.valueOf(c))) {
				int count = frequencyTable.get(String.valueOf(c));
				frequencyTable.put(String.valueOf(c), count + 1);
			}
		}
				
		System.out.println(frequencyTable);
		
		HashMap<String, Integer> SortedfrequencyTable = (HashMap<String, Integer>) sortByComparator(frequencyTable, false);
		System.out.println(SortedfrequencyTable);
		
		
		System.out.println(cipher);
		
		Object[] keySets = SortedfrequencyTable.keySet().toArray();
		
		for(int i=0; i<key.length(); i++) {
			decrypt(text, keySets[i].toString(), key.charAt(i), cipher, key);
			System.out.println(text);
		}
		
		System.out.println();
		System.out.println(text);
	}
		
	private void decrypt(StringBuilder sb, String key, char replaceKey, String cipher, String keyCheck) {
		for(int i=0; i<cipher.length(); i++) {
			if(cipher.charAt(i) == key.charAt(0)) {
				sb.setCharAt(i, replaceKey);
			} else {
				if(sb.toString().contains(keyCheck)) 
					sb.setCharAt(i, "-".charAt(0));
			}
		}
	}
	
	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

	

}
