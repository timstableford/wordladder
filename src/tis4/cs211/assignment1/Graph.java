package tis4.cs211.assignment1;

import java.util.HashMap;

public class Graph implements Runnable{
	private HashMap<String, Word> dictionary = null;
	private Feedback feedback;
	private String start,end;
	private DictionaryLoader dictLoader = null;
	private Dictionaries dict = null;
	public Graph(DictionaryLoader dictLoader, Dictionaries dict, String start, String end, Feedback feedback){
		this((HashMap<String, Word>)null,feedback);
		this.dictLoader = dictLoader;
		this.dict = dict;
		this.end = start.toLowerCase();
		this.start = end.toLowerCase();
	}
	public Graph(HashMap<String, Word> dictionary, Feedback feedback){
		this.dictionary = dictionary;
		this.feedback = feedback;
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
	public void displayWordTree(Word end){
		Word tWord = end;
		if(end.getParent()==null&&end.getDistance()!=0){
			feedback.status("no link found",true);
			return;
		}
		feedback.status(end.getWord()+" ",true);
		int i=0;
		while(i<20&&tWord.getDistance()!=0){
			feedback.status(" - "+tWord.getParent().getWord(),true);
			tWord = tWord.getParent();
			i++;
		}
	}
	/**
	 * Generates the tree until the shortest route is found
	 */
	@Override
	public void run() {
		//load dictionary
		if(dictLoader!=null&&dictionary==null){ 
			dictionary = dictLoader.getDictionary(dict);
		}
		//get the words
		Word startW,endW;
		startW = dictionary.get(start);
		endW = dictionary.get(end);
		feedback.status("Checking words in dictionary..."+"\n",true);
		if(start==null){
			feedback.error("End word not in dictionary");
		}else if(end==null){
			feedback.error("Start word not in dictionary");
		}else{
			//continue if they're in the dictionary
			startW.setRoot();
			HashMap<String, Word> hashMap = new HashMap<String,Word>();
			hashMap.put(startW.getWord(), startW);
			int i=0;
			while(endW.getParent()==null&&hashMap.size()>0){
				feedback.status("Iteration "+i+"\n",true);
				hashMap = this.iterateMap(hashMap);
				i++;
			}
			feedback.status("Displaying tree"+"\n",true);
			displayWordTree(endW);
			feedback.done();
		}
	}
}
