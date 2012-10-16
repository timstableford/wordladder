package tis4.cs211.assignment1;

public enum Dictionaries {
	TWO("dict2.dat"),
	THREE("dict3.dat"),
	FOUR("dict4.dat"),
	FIVE("dict5.dat"),
	SIX("dict6.dat"),
	SEVEN("dict7.dat"),
	EIGHT("dict8.dat"),
	NINE("dict9.dat"),
	TEN("dict10.dat"),
	SEVENTEEN("dict17.dat");
	private final String file;
	Dictionaries(String file){
		this.file = file;
	}
	public String file(){ return file; }
}
