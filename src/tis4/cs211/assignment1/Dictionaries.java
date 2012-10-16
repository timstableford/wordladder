package tis4.cs211.assignment1;

public enum Dictionaries {
	N2("dict2.dat"),
	N3("dict3.dat"),
	N4("dict4.dat"),
	N5("dict5.dat"),
	N6("dict6.dat"),
	N7("dict7.dat"),
	N8("dict8.dat"),
	N9("dict9.dat"),
	N10("dict10.dat"),
	N11("dict11.dat"),
	N12("dict12.dat"),
	N13("dict13.dat"),
	N14("dict14.dat"),
	N15("dict15.dat"),
	N16("dict16.dat"),
	N17("dict17.dat");
	private final String file;
	Dictionaries(String file){
		this.file = file;
	}
	public String file(){ return file; }
}
