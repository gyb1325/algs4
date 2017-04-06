/**
 * Auto Generated Java Class.
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.In;
public class Percolation {
	private int dim ;
	private boolean [] openindex;
	private WeightedQuickUnionUF p_grid;
	private int top;
	private int bottom;

	public Percolation(int n) throws java.lang.IllegalArgumentException {
		if (n > 0)
			p_grid = new WeightedQuickUnionUF(n * n + 2);
		else
			throw new java.lang.IllegalArgumentException();
		openindex = new boolean[n * n];
		for (boolean element : openindex) {
			element = false;
		}
		dim = n;
		bottom = n * n + 1;
		top = 0;
	}

	public void open(int row , int col) throws java.lang.IndexOutOfBoundsException {
		if ((row > dim || row < 1) || (col > dim || col < 1) ) {
			throw new java.lang.IndexOutOfBoundsException();
		}
		int index = dim * (row - 1) + (col - 1);
		openindex[index] = true;

		if (index >= 0 && index < dim)
			p_grid.union(index, top);

		if ((index >= dim * (dim - 1) ) && (index < dim * dim ) )
			p_grid.union(index, bottom);


		if ((row - 1) > 0) {
			int index_last = dim * (row - 2) + (col - 1);
			if (openindex[index_last])
				p_grid.union(index, index_last);
		}

		if ((row + 1) <= dim) {
			int index_forward = dim * (row) + (col - 1);
			if (openindex[index_forward] )
				p_grid.union(index, index_forward);
		}
		if ((col - 1) > 0) {
			int index_c_last = dim * (row - 1) + (col - 2);
			if (openindex[index_c_last])
				p_grid.union(index, index_c_last);
		}

		if ((col + 1) <= dim) {
			int index_c_forward = dim * (row - 1) + (col);
			if (openindex[index_c_forward])
				p_grid.union(index, index_c_forward);
		}

	}

	public boolean isOpen(int row, int col) throws java.lang.IndexOutOfBoundsException {
		if ((row > dim || row < 1) || (col > dim || col < 1) ) {
			throw new java.lang.IndexOutOfBoundsException();
		}

		int index = dim * (row - 1) + (col - 1);
		return openindex[index];
	}

	public boolean isFull(int row, int col) throws java.lang.IndexOutOfBoundsException {
		if ((row > dim || row < 1) || (col > dim || col < 1) ) {
			throw new java.lang.IndexOutOfBoundsException();
		}
		int index = dim * (row - 1) + (col - 1);
		return p_grid.connected(index, top);
	}

	public int numberOfOpenSites() {
		int numbers = 0;
		for (boolean element : openindex) {
			if (element) {
				numbers++;
			}
		}
		return numbers;
	}

	public boolean percolates() {
		return p_grid.connected(top, bottom);
	}

// Use for simple test
	public static void main(String[] args) {
		// InputStream f = new FileInputStream("F:/percolate/input8-no.txt");
		// scanner = new Scanner(new BufferedInputStream(f), CHARSET_NAME);
		// scanner.useLocale(LOCALE);
		In in = new In("F:/percolate/input8-no.txt");
		int n = in.readInt();
		Percolation perc = new Percolation(n);
		while (!in.isEmpty()) {
			int i = in.readInt();
			int j = in.readInt();
			perc.open(i, j);
			if (perc.percolates()) {
				System.out.println("It percolates");
			}

		}


	}


}
