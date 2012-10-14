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
	public HashMap<Long, Word> iterateWord(Word tempRoot){
		HashMap<Long, Word> map = new HashMap<Long, Word>();
		if(tempRoot==null){ return map; }
		long i=0;
		for(Word h : dictionary.values()){
			if(h.getParent()==null&&h.differnce(tempRoot)<=1){
				h.setParent(tempRoot);
				map.put(i,h);
				i++;
			}
		}
		return map;
	}
	public HashMap<Long, Word> iterateMap(HashMap<Long, Word> incomingHash){
		HashMap<Long, Word> tempHash = new HashMap<Long,Word>();
		for(Word h : dictionary.values()){
			tempHash.putAll(iterateWord(h));
		}
		return tempHash;
	}
}
