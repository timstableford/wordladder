package tis4.cs211.assignment1.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import tis4.cs211.assignment1.Dictionaries;
import tis4.cs211.assignment1.DictionaryLoader;
import tis4.cs211.assignment1.Feedback;
import tis4.cs211.assignment1.Word;

public class DictionaryTests implements Feedback{
	private DictionaryLoader loader;
	private Word a,b,c,d;
	private HashMap<String,Word> dict;
	private HashMap<String,Word> setDict;
	@Before
	public void setUp() throws Exception {
		loader = new DictionaryLoader(this);
		a = new Word("fox");
		b = new Word("fax");
		c = new Word("max");
		d = new Word("cox");
		setDict = new HashMap<String,Word>();
		setDict.put(c.getWord(), c);
		setDict.put(b.getWord(), b);
		setDict.put(a.getWord(), a);
		setDict.put(d.getWord(), d);
	}
	@Test 
	public void loadTestDictionary() {
		dict = loader.getDictionary(Dictionaries.NTEST);
		assertNotNull("Dictionary null",dict);
	}
	@Test
	public void testLoadDictionaryAndCheck() {
		dict = loader.getDictionary(Dictionaries.NTEST);
		assertTrue("Dictionaries not equal",dict.equals(setDict));
	}
	@Test
	public void testAllWordsInDictionary(){
		dict = loader.getDictionary(Dictionaries.NTEST);
		assertNotNull(a.getWord()+" missing",dict.get(a.getWord()));
		assertNotNull(b.getWord()+" missing",dict.get(b.getWord()));
		assertNotNull(c.getWord()+" missing",dict.get(c.getWord()));
	}
	@Test
	public void loadDictionaryTwiceAndTestResetting(){
		HashMap<String,Word> dictA = loader.getDictionary(Dictionaries.NTEST);
		dictA.get("fox").setRoot();
		dictA.get("fax").setParent(dictA.get("fox"));
		HashMap<String,Word> dictB = loader.getDictionary(Dictionaries.NTEST);
		assertFalse("fox parent not reset",dictB.get("fox").getParent()==dictB.get("fox"));
		assertFalse("fax parent not reset",dictB.get("fox")==dictB.get("fax").getParent());
	}

	public void status(String status, boolean append) {}
	public void error(String error) {}
	public void done() {}

}
