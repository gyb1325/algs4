import edu.princeton.cs.algs4.MinPQ;
import java.lang.Math;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.lang.StringBuilder

public class Solver {
	MinPQ<Board> pq;
	public Solver(Board initial) {         // find a solution to the initial board (using the A* algorithm)
		pq = new MinPQ<Board>();
		for (Board element : initial.neighbors()) {


		}
	}
	public boolean isSolvable() {          // is the initial board solvable?

	}
	public int moves() {                   // min number of moves to solve initial board; -1 if unsolvable
		if (!isSolvable()) return -1;
	}
	public Iterable<Board> solution() {    // sequence of boards in a shortest solution; null if unsolvable
		if (!isSolvable()) return null;
	}
	public static void main(String[] args) // solve a slider puzzle (given below)
}