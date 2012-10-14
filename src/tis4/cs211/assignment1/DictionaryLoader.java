package tis4.cs211.assignment1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class DictionaryLoader {
	private String dictName;
	private HashMap<Long, Word> hash;
	public DictionaryLoader(Dictionaries dictionary){
		this.dictName = dictionary.file();
		hash = new HashMap<Long, Word>();
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/tis4/cs211/assignment1/words/"+dictName))));
		long i=0;
		while(scanner.hasNextLine()){
			Word tempWord = new Word(scanner.nextLine());
			hash.put(i,tempWord);
			i++;
		}
		scanner.close();
	}
	public HashMap<Long, Word> getDictionary(){
		return hash;
	}
}
