import edu.princeton.cs.algs4.MinPQ;
import java.lang.Math;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Comparator;
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
			return board.euqals(that.board);
		}

	}

	private MinPQ<Node> pq;
	private MinPQ<Node> pq_twin;
	private boolean solve;
	private Stack<Board> solutionset;

	public Solver(Board initial) {         // find a solution to the initial board (using the A* algorithm)
		ArrayList<Node> listNode = new ArrayList<Node>();
		ArrayList<Node> listNode_twin = new ArrayList<Node>();

		solve = true;
		pq = new MinPQ<Node>();
		pq_twin = new MinPQ<Node>();
		solutionset = new Stack<Board>();

		Node init_Node = new Node(initial, 0, null);
		Node twin_Node = new Node(initial.twin(), 0, null);
		pq.insert(init_Node);
		pq_twin.insert(twin_Node);

		while (true) {
			Node n_Node = pq.delMin();
			Node n_twin_Node = pq_twin.delMin();
			if (n_Node.board.isGoal() || n_twin_Node.board.isGoal() ) break;

			for (Board element : n_Node.board.neighbors() ) {
				Node temp = Node(element, n_Node.steps + 1, n_Node);
				if (n_Node.prev == null || (!n_Node.prev.equals(temp) && !listNode.contains(temp)) ) {
					pq.insert(temp);
					listNode.add(temp);
				}
			}

			for (Board element : n_twin_Node.board.neighbors()) {
				Node temp = Node(element, n_twin_Node.steps + 1, n_twin_Node);

				if (n_twin_Node.prev == null || (!n_twin_Node.prev.equals(temp) && !listNode_twin.contains(temp)) ) {
					pq_twin.insert(temp);
					listNode_twin.add(temp);
				}
			}

		}
		if (n_Node.Board.isGoal()) {
			Node node_temp = n_Node;
			while (node_temp != null) {
				solutionset.push(node.board);
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
		else reutrn - 1;
	}
	public Iterable<Board> solution() {    // sequence of boards in a shortest solution; null if unsolvable
		if (!solve) return null;
		else return solutionset;
	}
	public static void main(String[] args) // solve a slider puzzle (given below)
}