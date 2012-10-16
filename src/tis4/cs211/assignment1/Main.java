package tis4.cs211.assignment1;

import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		DictionaryLoader dl = new DictionaryLoader(Dictionaries.N5);
		HashMap<String, Word> dict = dl.getDictionary();
		@SuppressWarnings("unchecked")
		Graph g = new Graph((HashMap<String,Word>)dict.clone());
		
		Word root = g.findWord("clock");
		Word endWord = dict.get("clash");
		
		System.out.println("Setting root graph");
		root.setRoot();
		HashMap<String, Word> hashMap;
		System.out.println("First iteration");
		hashMap = g.iterateWord(root);
		System.out.println(hashMap);
		int i=0;
		while(endWord.getParent()==null&&hashMap.size()>0){
			System.out.println("Iteration "+i);
			hashMap = g.iterateMap(hashMap);
			i++;
		}
		//print a word ladder
		
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
