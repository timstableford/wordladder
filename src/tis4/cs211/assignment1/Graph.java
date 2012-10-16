package tis4.cs211.assignment1;

import java.util.HashMap;

public class Graph {
	private HashMap<String, Word> dictionary;
	public Graph(HashMap<String, Word> dictionary){
		this.dictionary = dictionary;
	}
	public Word findWord(String word){
		return dictionary.get(word);
	}
	public HashMap<String, Word> iterateWord(Word tempRoot){
		HashMap<String, Word> map = new HashMap<String, Word>();
		HashMap<String, Word> toDelete = new HashMap<String,Word>();
		if(tempRoot==null){ return map; }
		for(Word h : dictionary.values()){
			if(h.differnce(tempRoot)==1){
				h.setParent(tempRoot);
				map.put(h.getWord(),h);
				toDelete.put(h.getWord(),h);
			}
		}
		for(Word m: toDelete.values()){
			dictionary.remove(m.getWord());
		}
		return map;
	}
	public HashMap<String, Word> iterateMap(HashMap<String, Word> incomingHash){
		HashMap<String, Word> tempHash = new HashMap<String,Word>();
		for(Word h : incomingHash.values()){
			tempHash.putAll(iterateWord(h));
		}
		return tempHash;
	}
}
