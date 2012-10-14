package tis4.cs211.assignment1;

import java.util.HashMap;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Loading dictionary 3");
		DictionaryLoader dl = new DictionaryLoader(Dictionaries.THREE);
		Graph g = new Graph(dl.getDictionary());
		System.out.println("Setting root fox");
		Word root = g.findWord("fox");
		root.setRoot();
		HashMap<Long, Word> hashMap;
		System.out.println("First iteration");
		hashMap = g.iterateWord(root);
		System.out.println(hashMap);
		int i=0;
		while(hashMap.size()>0){
			System.out.println("Iteration "+i);
			hashMap = g.iterateMap(hashMap);
			System.out.println(hashMap);
			i++;
		}
	}

}
