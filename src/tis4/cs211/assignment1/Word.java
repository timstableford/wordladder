package tis4.cs211.assignment1;
/**
 * Stores the node data of the graph, specifically a word.
 * @author Tim Stableford
 */
public class Word {
	private String word;
	private Word parent = null;
	private int distance = -1;
	public Word(String word){
		this.word = word;
	}
	/**
	 * @param other the other word to compare against
	 * @return the difference in letters between this word and the other word
	 */
	public int differnce(Word other){
		int difference=0;
		byte[] thisWord = this.word.getBytes();
		byte[] otherWord = other.word.getBytes();
		if(thisWord.length!=otherWord.length){ return -1; }
		for(int i=0; i<word.length(); i++){
			if(thisWord[i]!=otherWord[i]){
				difference++;
			}
		}
		return difference;
	}
	public String getWord(){
		return this.word;
	}
	public Word getParent(){
		return parent;
	}
	public void setParent(Word word){
		this.parent = word;
	}
	/**
	 * Sets this as the start word
	 */
	public void setRoot(){
		distance = 0;
		this.parent = this;
	}
	public int getDistance(){
		return distance;
	}
	public String toString(){
		String p = "unset";
		if(parent!=null){
			p = parent.getWord();
		}
		return "Word - "+word+" parent - "+p;
	}
	/**
	 * Resets the word to the state where it was loaded
	 */
	public void reset(){
		parent = null;
		distance = -1;
	}
}
