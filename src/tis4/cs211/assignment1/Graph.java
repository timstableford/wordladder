package tis4.cs211.assignment1;

import java.util.HashMap;

public class Graph {
	private HashMap<Long, Word> dictionary;
	public Graph(HashMap<Long, Word> dictionary){
		this.dictionary = dictionary;
	}
	public Word findWord(String word){
		for(Word h : dictionary.values()){
			if(h.getWord().equalsIgnoreCase(word)){
				return h;
			}
		}
		return null;
	}
	public void setRoot(Word word){
		word.setRoot();
	}
}
