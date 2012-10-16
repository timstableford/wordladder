package tis4.cs211.assignment1;

import java.util.HashMap;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DictionaryLoader dl = new DictionaryLoader(Dictionaries.FIVE);
		HashMap<String, Word> dict = dl.getDictionary();
		Graph g = new Graph((HashMap<String,Word>)dict.clone());
		System.out.println("Setting root graph");
		Word root = g.findWord("clash");
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
		//print a word ladder
		Word endWord = dict.get("clock");
		printWordTree(endWord);
	}
	private static void printWordTree(Word w){
		Word tWord = w;
		System.out.print(w.getWord());
		if(w.getParent()==null&&w.getDistance()!=0){
			System.out.println("no link found");
			return;
		}
		int i=0;
		while(i<20&&tWord.getDistance()!=0){
			System.out.print(" - "+tWord.getParent().getWord());
			tWord = tWord.getParent();
			i++;
		}
		System.out.println();
	}

}
