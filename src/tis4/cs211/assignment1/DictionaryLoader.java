package tis4.cs211.assignment1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Loads dictionary files into memory
 * @author Tim Stableford
 *
 */
public class DictionaryLoader{
	private Feedback feedback;
	private HashMap<Dictionaries,HashMap<String,Word>> dictionaryCollection;
	/**
	 * @param feedback class with Feedback interface for relaying messages back
	 */
	public DictionaryLoader(Feedback feedback){
		this.feedback = feedback;
		dictionaryCollection = new HashMap<Dictionaries,HashMap<String,Word>>();
	}
	/**
	 * Loads a dictionary
	 * @param dict
	 * @return a clone of the dictionary word collection
	 * Checks if a dictionary is already loaded, if it is, reset the words to their default state
	 * If it's not loaded, load it 
	 */
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
	/**
	 * Does the grunt work of loading a dictionary and places it in the loaded hashmap
	 * @param dict the dictionary to load
	 */
	private void load(Dictionaries dict){
		String dictName = dict.file();
		HashMap<String, Word> hash = new HashMap<String, Word>();
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(dictName))));
		while(scanner.hasNextLine()){
			Word tempWord = new Word(scanner.nextLine());
			hash.put(tempWord.getWord(),tempWord);
		}
		scanner.close();
		dictionaryCollection.put(dict, hash);
	}
	private void resetWords(HashMap<String, Word> dictionary){
		feedback.status("Resetting words\n",true);
		for(Word w: dictionary.values()){
			w.reset();
		}
	}
}
