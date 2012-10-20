package tis4.cs211.assignment1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tis4.cs211.assignment1.Word;

public class WordTests {
	private Word a,b,c,d,e;
	@Before
	public void setUp() throws Exception {
		a = new Word("fox");
		b = new Word("fax");
		c = new Word("max");
		d = new Word("yes");
		e = new Word("dinosaur");
	}

	@Test
	public void testGetWord() {
		assertEquals("Word retrieval not working",a.getWord(),"fox");
	}
	@Test
	public void testDifferenceByOne(){
		assertEquals("Test with 1 difference fail",a.difference(b),1);
	}
	@Test
	public void testDifferenceByTwo(){
		assertEquals("Test with 2 difference fail",a.difference(c),2);
	}
	@Test
	public void testNoDifference(){
		assertEquals("Test with the same word",a.difference(a),0);
	}
	@Test
	public void testImpossibleDifference(){
		assertEquals("Test with no link",a.difference(d),3);
	}
	@Test
	public void testWordsDifferentLength(){
		assertEquals("Test with different length words",a.difference(e),-1);
	}
	@Test
	public void testSetGetParent(){
		a.setParent(b);
		assertTrue("parent test",a.getParent()==b);
	}
	@Test
	public void testSetRoot(){
		d.setRoot();
		assertTrue("parent not self for root",d.getParent()==d);
	}
	@Test
	public void testEquals(){
		assertFalse("a should not equals b",a.equals(b));
		assertFalse("fox should not equals dinosaur",a.equals(d));
		assertTrue("fox should equal fox",a.equals(new Word("fox")));
	}
	

}
