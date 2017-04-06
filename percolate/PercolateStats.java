/**
 * Auto Generated Java Class.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.In;
public class PercolateStats {

	private int T;
	private double mean_value;
	private int [] sites_number;

	public PercolateStats(int n, int trials) {
		PercolateStats perc = new Percolation(n);
		T = trials;
		mean_value = 0.0d;
		sites_number = new int[trials];
		StdStats st = new StdStats();
	}

	public double mean() {
		return	st.mean([] sites_number);

	}

	public double stddev() {
		return	st.stddev([] sites_number)
	}

	public double confidenceLo() {

	}

	public double confidenceHi() {

	}

	public static void main(String[] args) {

		int size = 200;
		int trials = 100;

		PercolateStats percs = new PercolateStats(size, trials);
		StdRandom sr = new StdRandom();
		for (int p = 0; p < trials; p++) {
			int site_numbers = 0;
			while (!percs.perc.percolate()) {
				int i = sr.uniform(1, size + 1);
				int j = sr.uniform(1, size + 1);
				percs.perc.open(i, j);
				site_numbers++;
			}
			sites_number[p] = site_numbers;
		}

	}

	/* ADD YOUR CODE HERE */

}
