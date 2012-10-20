package tis4.cs211.assignment1;
/**
 * Used to make the interface modular 
 * @author Tim Stableford
 *
 */
public interface Feedback {
	public void status(String status, boolean append);
	public void error(String error);
	public void done();
}
