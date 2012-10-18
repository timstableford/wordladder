package tis4.cs211.assignment1;

public class Word {
	private String word;
	private Word parent = null;
	private int distance = -1;
	public Word(String word){
		this.word = word;
	}
	public int differnce(Word other){
		int difference=0;
		byte[] thisWord = this.word.getBytes();
		byte[] otherWord = other.getWord().getBytes();
		if(word.length()!=other.getWord().length()){ return difference; }
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
	public int distanceToRoot(){
		if(distance<0){
			Word tempParent = parent;
			if(parent!=null){
				distance = 0;
				while(tempParent!=null){
					distance++;
				}
			}
		}
		return distance;
	}
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
	public void reset(){
		parent = null;
		distance = -1;
	}
}
