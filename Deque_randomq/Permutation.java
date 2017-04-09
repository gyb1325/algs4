import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		int i = k;
		RandomizedQueue<String> sr = new RandomizedQueue<String>();
		while ((k--) > 0) {
			String in = StdIn.readString();
			sr.enqueue(in);
		}
		while ((i--) > 0) {
			String out = sr.dequeue();
			StdOut.println(out);
		}
	}
}