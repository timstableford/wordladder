package tis4.cs211.assignment1;
/**
 * Stores the node data of the graph, specifically a word.
 * @author Tim Stableford
 */
public class Word {
	private String word;
	private Word parent = null;
	public Word(String word){
		this.word = word;
	}
	/**
	 * @param other the other word to compare against
	 * @return the difference in letters between this word and the other word
	 */
	public int difference(Word other){
		int difference=0;
		if(other==null||this.word.length()!=other.word.length()){ return -1; }
		byte[] thisWord = this.word.getBytes();
		byte[] otherWord = other.word.getBytes();
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
		this.parent = this;
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
	}
	@Override
	public boolean equals(Object other){
		boolean ret = true;
		if(!(other instanceof Word)){
			ret = false;
		}
		Word word = (Word)other;
		if(ret&&word.getWord().equals(this.getWord())==false){
			ret = false;
		}
		return ret;
	}
}
