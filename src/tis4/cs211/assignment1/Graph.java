package tis4.cs211.assignment1;

import java.util.HashMap;
/**
 * Data tree for calculating the shortest route and doing generation
 * @author Tim Stableford
 * Uses threads to not block the GUI
 */
public class Graph implements Runnable{
	private Feedback feedback;
	private String start,end;
	private int length;
	private DictionaryLoader dictLoader = null;
	private Dictionaries dict = null;
	private HashMap<String,Word> dictionary = null;
	/**
	 * Called for shortest route
	 */
	public Graph(DictionaryLoader dictLoader, Dictionaries dict, Feedback feedback, String start, String end){
		this(dictLoader,dict,feedback,start);
		this.end = end.toLowerCase().trim();;
	}
	/**
	 * Called for generation
	 */
	public Graph(DictionaryLoader dictLoader, Dictionaries dict, Feedback feedback, String start, int length){
		this(dictLoader,dict,feedback,start);
		this.length = length;
	}
	private Graph(DictionaryLoader dictLoader, Dictionaries dict, Feedback feedback, String start){
		this.dictLoader = dictLoader;
		this.dict = dict;
		this.feedback = feedback;
		this.start = start.toLowerCase().trim();
	}
	/**
	 * Performs an iteration around a word
	 * @param tempRoot Word to iterate around
	 * @return All words in the dictionary which were within 1 of the root word
	 * Goes through each word in the dictionary, if that word has a change of only 1 letter, then it sets that words parent as the root word and adds it to a hashmap.
	 * When complete it then goes through that hashmap and removes all words in it from the dictionary.
	 */
	public HashMap<String, Word> iterateWord(HashMap<String, Word> localDictionary, Word tempRoot){
		HashMap<String, Word> map = new HashMap<String, Word>();
		for(Word h: localDictionary.values()){
			if(h.difference(tempRoot)==1){
				h.setParent(tempRoot);
				map.put(h.getWord(),h);
			}
		}
		for(Word m: map.values()){
			localDictionary.remove(m.getWord());
		}
		return map;
	}
	/**
	 * Iterates all words in a hashmap
	 * @param incomingHash the hash to iterate all the words in
	 * @return a hash of all the words that have had their parent set inside of this operation
	 */
	public HashMap<String, Word> iterateMap(HashMap<String, Word> localDictionary, HashMap<String, Word> incomingHash){
		HashMap<String, Word> output = new HashMap<String,Word>();
		for(Word h : incomingHash.values()){
			output.putAll(iterateWord(localDictionary,h));
		}
		return output;
	}
	/**
	 * Displays the word tree
	 * @param end The word to start from, always has to be the end because it works backwards through parents
	 * while the current word, initially the end word, has a parent send this word is buffered and then the ladder is returned
	 */
	public String displayWordTree(Word localEnd){
		StringBuffer output = new StringBuffer();
		Word targetWord = localEnd;
		if(targetWord==null||targetWord.getParent()==null||targetWord==targetWord.getParent()){
			output.append("no link found");
		}else{
			output.append(localEnd.getWord());
			while(targetWord!=null&&targetWord.getParent()!=null&&targetWord.getParent()!=targetWord){
				output.append(" - ");
				output.append(targetWord.getParent().getWord());
				targetWord = targetWord.getParent();
			}
		}
		return output.toString();
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
			shortestRoute(dictionary,start,end);
		}else if(length>0){
			generation(dictionary,start,length);
		}
	}
	/**
	 * Finds the shortest route between 2 words
	 * First it checks that the 2 words are in its dictionary, length and empty string checks are performed in the gui.
	 * Second it sets the start word as a root, meaning it's distance is 0 and it's parent is its self.
	 * It then does an initial iteration around that word and gets all words within a distance of 1 from its self.
	 * Next it performs a loop where the initial word list is iterated around and then the word list that it returns is iterated around,
	 * this continues until the end word has a parent or there's no more words to process.
	 * Finally, it displays the tree and tells the GUI that it is done.
	 */
	public void shortestRoute(HashMap<String,Word> localDictionary, String localStart, String localEnd){
		//get the words
		Word startWord,endWord;
		startWord = localDictionary.get(localStart);
		endWord = localDictionary.get(localEnd);
		feedback.status("Checking words in dictionary..."+"\n",true);
		if(startWord==null){
			feedback.error("Start word not in dictionary");
		}else if(endWord==null){
			feedback.error("End word not in dictionary");
		}else{
			//continue if they're in the dictionary
			startWord.setRoot();
			//make sure the start word isnt in the dictonary, if it is remove it
			localDictionary.remove(startWord.getWord());
			feedback.status("Iteration 0\n", true);
			long runtime = System.nanoTime();
			HashMap<String, Word> hashMap = iterateWord(localDictionary,startWord);
			for(int i=1; endWord.getParent()==null&&hashMap.size()>0; i++){
				feedback.status("Iteration "+i+"\n",true);
				hashMap = this.iterateMap(localDictionary, hashMap);
			}
			feedback.status("Displaying ladder"+"\n",true);
			runtime = System.nanoTime() - runtime;
			double retTime = runtime/Math.pow(10, 9);
			feedback.status("Runtime "+retTime+"s\n",true);
			feedback.status(displayWordTree(endWord),true);
		}
		feedback.done();
	}
	/**
	 * Generates a word tree of arbitrary length
	 * First it checks to make sure the initial word exists
	 * Then it finds a single word with a distance of 1 to it and sets its parent as the initial word.
	 * The same process is done with the new word, looped until the length it reached or it cant find a direction to go.
	 * When done, it displays the tree and tells the GUI it's done. 
	 * @return the word it finishes on
	 */
	public Word generation(HashMap<String,Word> localDictionary, String localStart, int localLength){
		Word current = null;
		if(!localDictionary.containsKey(localStart)){
			feedback.error("Word not in dictionary");
		}else{
			current = localDictionary.get(localStart);
			//continue if it's in the dictionary
			feedback.status("Doing generation...\n",true);
			current.setRoot();
			long runtime = System.nanoTime();
			localDictionary.remove(current.getWord());
			int i = 0;
			for(i=0;i<(localLength-1); i++){
				Word next = generationAddWord(current,localDictionary);
				if(next==null){ break; }
				current = next;
			}
			feedback.status("Ladder of length "+(i+1)+"\n", true);
			runtime = System.nanoTime() - runtime;
			double retTime = runtime/Math.pow(10, 9);
			feedback.status("Runtime "+retTime+"s\n",true);
			feedback.status("Displaying ladder\n",true);
			feedback.status(displayWordTree(current),true);
		}
		feedback.done();
		return current;
	}
	/**
	 * Does the work for generation
	 * @param current The current word
	 * @param dictionary the word list to check in
	 * @return a word that has a distance to 1 of the input word
	 */
	public Word generationAddWord(Word current, HashMap<String,Word> dictionary){
		for(Word w: dictionary.values()){
			if(w.difference(current)==1){
				w.setParent(current);
				dictionary.remove(w.getWord());
				return w;
			}
		}
		return null;
	}
}