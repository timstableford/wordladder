package tis4.cs211.assignment1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tis4.cs211.assignment1.Feedback;
import tis4.cs211.assignment1.Graph;
import tis4.cs211.assignment1.Word;

public class GraphTest implements Feedback{
	private Word a,b,c;
	private Graph graphGeneration, graphShortest;
	@Before
	public void setUp() throws Exception {
		a = new Word("fox");
		b = new Word("fax");
		c = new Word("max");
	}

	@Test
	public void testIterateWord(){
		
	}

	@Override
	public void status(String status, boolean append) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		
	}

}
