package tis4.cs211.assignment1;

import java.util.HashMap;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Loading dictionary 5");
		DictionaryLoader dl = new DictionaryLoader(Dictionaries.FIVE);
		Graph g = new Graph(dl.getDictionary());
		System.out.println("Setting root graph");
		Word root = g.findWord("graph");
		root.setRoot();
		HashMap<String, Word> hashMap;
		System.out.println("First iteration");
		hashMap = g.iterateWord(root);
		System.out.println(hashMap);
		int i=0;
		while(hashMap.size()>0){
			System.out.println("Iteration "+i);
			hashMap = g.iterateMap(hashMap);
			i++;
		}
	}

}
