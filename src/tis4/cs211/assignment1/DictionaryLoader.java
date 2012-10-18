package tis4.cs211.assignment1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class DictionaryLoader implements Runnable{
	private String dictName;
	private Feedback feedback;
	private HashMap<Dictionaries,HashMap<String,Word>> dictionaryCollection;
	public DictionaryLoader(Feedback feedback){
		this.feedback = feedback;
		dictionaryCollection = new HashMap<Dictionaries,HashMap<String,Word>>();
	}
	@SuppressWarnings("unchecked")
	public HashMap<String, Word> getDictionary(Dictionaries dict){
		if(dictionaryCollection.get(dict)==null){
			feedback.status("Dictionary not loaded, loading...\n", true);
			load(dict);
		}else{
			resetWords(dictionaryCollection.get(dict));
		}
		feedback.status("Dictionary loaded, passing.\n", true);
		return (HashMap<String, Word>) (dictionaryCollection.get(dict)).clone();
	}
	private void load(Dictionaries dict){
		this.dictName = dict.file();
		HashMap<String, Word> hash = new HashMap<String, Word>();
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/tis4/cs211/assignment1/words/"+dictName))));
		while(scanner.hasNextLine()){
			Word tempWord = new Word(scanner.nextLine());
			hash.put(tempWord.getWord(),tempWord);
		}
		scanner.close();
		dictionaryCollection.put(dict, hash);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	private void resetWords(HashMap<String, Word> dictionary){
			feedback.status("Resetting words\n",true);
			for(Word w: dictionary.values()){
				w.reset();
			}

	}
}
