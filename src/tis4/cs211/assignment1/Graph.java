package tis4.cs211.assignment1;

import java.util.HashMap;

public class Graph implements Runnable{
	private Feedback feedback;
	private String start,end;
	private int length;
	private DictionaryLoader dictLoader = null;
	private Dictionaries dict = null;
	private HashMap<String,Word> dictionary = null;
	public Graph(DictionaryLoader dictLoader, Dictionaries dict, Feedback feedback, String start, String end){
		this(dictLoader,dict,feedback,start);
		this.end = end.toLowerCase();
	}
	public Graph(DictionaryLoader dictLoader, Dictionaries dict, Feedback feedback, String start, int length){
		this(dictLoader,dict,feedback,start);
		this.length = length;
	}
	public Graph(DictionaryLoader dictLoader, Dictionaries dict, Feedback feedback, String start){
		this.dictLoader = dictLoader;
		this.dict = dict;
		this.feedback = feedback;
		this.start = start.toLowerCase();
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
		Word targetWord = end;
		if(targetWord.getParent()==null&&targetWord.getDistance()!=0){
			feedback.status("no link found",true);
			return;
		}
		feedback.status(end.getWord()+" ",true);
		while(targetWord!=null&&targetWord.getDistance()!=0){
			feedback.status(" - "+targetWord.getParent().getWord(),true);
			targetWord = targetWord.getParent();
		}
	}
	/**
	 * Loads the dictionary and chooses which mode to do
	 */
	@Override
	public void run() {
		//load dictionary
		if(dictLoader!=null&&dictionary==null){ 
			dictionary = dictLoader.getDictionary(dict);
		}
		if(end!=null){
			shortestRoute();
		}else if(length>0){
			generation();
		}
	}
	private void shortestRoute(){
		//get the words
		Word startW,endW;
		startW = dictionary.get(start);
		endW = dictionary.get(end);
		feedback.status("Checking words in dictionary..."+"\n",true);
		if(start==null){
			feedback.error("Start word not in dictionary");
		}else if(end==null){
			feedback.error("End word not in dictionary");
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
	private void generation(){
		Word current = dictionary.get(start);
		feedback.status("Checking words in dictionary..."+"\n",true);
		if(current==null){
			feedback.error("Start word not in dictionary");
		}else{
			//continue if they're in the dictionary
			feedback.status("Doing generation...\n",true);
			current.setRoot();
			dictionary.remove(current.getWord());
			for(int i=0;i<(length-1); i++){
				Word next = generationAddWord(current,dictionary);
				if(next==null){ 
					feedback.status("Ladder of length "+(i+1)+"\n", true);
					break; 
				}
				current = next;
			}
			feedback.status("Displaying tree\n",true);
			displayWordTree(current);
			feedback.done();
		}
	}
	private Word generationAddWord(Word current, HashMap<String,Word> dictionary){
		for(Word w: dictionary.values()){
			if(w.differnce(current)==1){
				w.setParent(current);
				dictionary.remove(w.getWord());
				return w;
			}
		}
		return null;
	}
}