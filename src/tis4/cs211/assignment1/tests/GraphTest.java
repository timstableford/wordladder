package tis4.cs211.assignment1.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import tis4.cs211.assignment1.Dictionaries;
import tis4.cs211.assignment1.DictionaryLoader;
import tis4.cs211.assignment1.Feedback;
import tis4.cs211.assignment1.Graph;
import tis4.cs211.assignment1.Word;

public class GraphTest implements Feedback{
	private Word a,b,c,d,e;
	private HashMap<String,Word> dictionary;
	private Graph graphGeneration, graphShortest;
	private DictionaryLoader loader;
	@Before
	public void setUp() throws Exception {
		loader = new DictionaryLoader(this);
		graphGeneration = new Graph(loader, Dictionaries.NTEST, this, "max", 10);
		graphShortest = new Graph(loader, Dictionaries.NTEST, this, "fox", "max");
		dictionary = new HashMap<String,Word>();
		a = new Word("fox");
		b = new Word("fax");
		c = new Word("max");
		d = new Word("cox");
		e = new Word("yes");
		dictionary.put(a.getWord(),a);
		dictionary.put(b.getWord(),b);
		dictionary.put(c.getWord(), c);
		dictionary.put(d.getWord(), d);
	}
	@Test
	public void testIterateWord(){
		HashMap<String,Word> expected = new HashMap<String,Word>();
		expected.put(b.getWord(),b);
		expected.put(d.getWord(),d);
		assertEquals("Iterate word unexpected result",expected,graphGeneration.iterateWord(dictionary, a));
	}
	@Test
	public void testIterateWordNoResult(){
		HashMap<String,Word> expected = new HashMap<String,Word>();
		assertEquals("Iterate word unexpected result",expected,graphGeneration.iterateWord(dictionary, e));
	}
	@Test
	public void testIterateNull(){
		HashMap<String,Word> expected = new HashMap<String,Word>();
		assertEquals("Iterate word unexpected result",expected,graphGeneration.iterateWord(dictionary, null));
	}
	@Test
	public void testDisplayTreeNoProblems(){
		c.setParent(b);
		b.setParent(a);
		a.setRoot();
		assertEquals("word tree display wrong",graphGeneration.displayWordTree(c),"max - fax - fox");
	}
	@Test
	public void testDisplayWordTreeNoLink(){
		loader.getDictionary(Dictionaries.NTEST).get("max").setRoot();
		assertEquals("tree should be blank",graphGeneration.displayWordTree(c),"no link found");
	}
	@Test
	public void testDisplayWordTreeStartNotRoot(){
		c.setParent(b);
		b.setParent(a);
		assertEquals("problem displaying tree",graphGeneration.displayWordTree(c),"max - fax - fox");
	}
	@Test
	public void testShortestRouteFoxToMax(){
		Word end = dictionary.get("max");
		graphShortest.shortestRoute(dictionary, "fox", "max");
		assertEquals("incorrect shortest path",graphGeneration.displayWordTree(end),"max - fax - fox");
	}
	@Test
	public void testShortestRouteToSelf(){
		Word end = dictionary.get("fox");
		graphShortest.shortestRoute(dictionary, "fox", "fox");
		assertEquals("incorrect shortest path",graphShortest.displayWordTree(end),"fox");
	}
	@Override
	public void status(String status, boolean append) {}

	@Override
	public void error(String error) {}

	@Override
	public void done() {}

}
