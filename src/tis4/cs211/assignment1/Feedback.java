package tis4.cs211.assignment1;

public interface Feedback {
	public void status(String status, boolean append);
	public void error(String error);
	public void done();
}
