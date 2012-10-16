package tis4.cs211.assignment1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class DictionaryLoader {
	private String dictName;
	private HashMap<String, Word> hash;
	public DictionaryLoader(Dictionaries dictionary){
		this.dictName = dictionary.file();
		hash = new HashMap<String, Word>();
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/tis4/cs211/assignment1/words/"+dictName))));
		while(scanner.hasNextLine()){
			Word tempWord = new Word(scanner.nextLine());
			hash.put(tempWord.getWord(),tempWord);
		}
		scanner.close();
	}
	public HashMap<String, Word> getDictionary(){
		return hash;
	}
}
