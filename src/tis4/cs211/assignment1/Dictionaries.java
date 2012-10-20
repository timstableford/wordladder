package tis4.cs211.assignment1;
/**
 * Quick way to store the names of dictionaries with words of set length
 * @author Tim Stableford
 *
 */
public enum Dictionaries {
	N2("/tis4/cs211/assignment1/words/dict2.dat"),
	N3("/tis4/cs211/assignment1/words/dict3.dat"),
	N4("/tis4/cs211/assignment1/words/dict4.dat"),
	N5("/tis4/cs211/assignment1/words/dict5.dat"),
	N6("/tis4/cs211/assignment1/words/dict6.dat"),
	N7("/tis4/cs211/assignment1/words/dict7.dat"),
	N8("/tis4/cs211/assignment1/words/dict8.dat"),
	N9("/tis4/cs211/assignment1/words/dict9.dat"),
	N10("/tis4/cs211/assignment1/words/dict10.dat"),
	N11("/tis4/cs211/assignment1/words/dict11.dat"),
	N12("/tis4/cs211/assignment1/words/dict12.dat"),
	N13("/tis4/cs211/assignment1/words/dict13.dat"),
	N14("/tis4/cs211/assignment1/words/dict14.dat"),
	N15("/tis4/cs211/assignment1/words/dict15.dat"),
	N16("/tis4/cs211/assignment1/words/dict16.dat"),
	N17("/tis4/cs211/assignment1/words/dict17.dat");
	private final String file;
	Dictionaries(String file){
		this.file = file;
	}
	/**
	 * @return the file name and package ie /tis4/cs211/assignment1/words/dict17.dat
	 */
	public String file(){ return file; }
}
