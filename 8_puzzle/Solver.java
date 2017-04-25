import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayDeque;
import java.util.ArrayList;


public class Solver {
	private class Node implements Comparable<Node> {
		private Board board;
		private int steps;
		private Node prev;
		public Node(Board initial, int moves, Node p) {
			board = initial;
			steps = moves;
			prev = p;
		}

		public int compareTo(Node n_Node) {
			int pri_diff = (board.manhattan() + steps) - (n_Node.board.manhattan() + n_Node.steps);
			if (pri_diff == 0) return  (board.manhattan() - n_Node.board.manhattan());
			return pri_diff;
		}

		public boolean equals(Object y) {
			Node that = (Node) y;
			return board.equals(that.board);
		}

	}

	private MinPQ<Node> pq;
	private MinPQ<Node> pq_twin;
	private boolean solve;
	private ArrayDeque<Board> solutionset = new ArrayDeque<Board>();

	public Solver(Board initial) {         // find a solution to the initial board (using the A* algorithm)
		ArrayList<Node> listNode = new ArrayList<Node>();
		ArrayList<Node> listNode_twin = new ArrayList<Node>();

		solve = true;
		pq = new MinPQ<Node>();
		pq_twin = new MinPQ<Node>();
		//solutionset = new Deque<>();

		Node init_Node = new Node(initial, 0, null);
		Node twin_Node = new Node(initial.twin(), 0, null);
		pq.insert(init_Node);
		pq_twin.insert(twin_Node);
		Node n_Node = null;
		Node n_twin_Node = null;
		while (true) {
			n_Node = pq.delMin();
			n_twin_Node = pq_twin.delMin();
			if (n_Node.board.isGoal() || n_twin_Node.board.isGoal() ) break;

			for (Board element : n_Node.board.neighbors() ) {
				Node temp = new Node(element, n_Node.steps + 1, n_Node);
				if (n_Node.prev == null || (!n_Node.prev.equals(temp) && !listNode.contains(temp)) ) {
					pq.insert(temp);
					listNode.add(temp);
				}
			}

			for (Board element : n_twin_Node.board.neighbors()) {
				Node temp = new Node(element, n_twin_Node.steps + 1, n_twin_Node);

				if (n_twin_Node.prev == null || (!n_twin_Node.prev.equals(temp) && !listNode_twin.contains(temp)) ) {
					pq_twin.insert(temp);
					listNode_twin.add(temp);
				}
			}

		}
		if (n_Node.board.isGoal()) {
			Node node_temp = n_Node;
			while (node_temp != null) {
				//StdOut.println(node_temp.board);
				solutionset.addFirst(node_temp.board);
				node_temp = node_temp.prev;
			}
			solve = true;
		} else {
			solve = false;
		}


	}

	public boolean isSolvable() {          // is the initial board solvable?
		return solve;
	}
	public int moves() {                   // min number of moves to solve initial board; -1 if unsolvable
		if (solve) return solutionset.size() - 1;
		else return -1;
	}
	public Iterable<Board> solution() {    // sequence of boards in a shortest solution; null if unsolvable
		if (!solve) return null;
		else return solutionset;
	}
	public static void main(String[] args) { // solve a slider puzzle (given below)
// create initial board from file
		In in = new In("F:/Java_workspace/puzzle_8/src/puzzle04.txt");
		int n = in.readInt();
		int[][] blocks = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);

		}
	}
}