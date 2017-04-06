/**
 * Auto Generated Java Class.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class PercolateStats {

	private int T;
	private double mean_value;
	private double [] sites_number;
	private int size;

	public PercolateStats() {
	}

	public PercolateStats(int n, int trials) {
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException("n<=0 || trials <=0");
		T = trials;
		size = n;
		mean_value = 0.0d;
		sites_number = new double[trials];
		for (int p = 0; p < T; p++ ) {
			Percolation perc = new Percolation(n);
			int open_sites = 0;
			while (!perc.percolates()) {
				int i = StdRandom.uniform(1, size + 1);
				int j = StdRandom.uniform(1, size + 1);
				if (!perc.isOpen(i, j)) {
					perc.open(i, j);
					open_sites++;
				}
			}
			sites_number[p] = (double)open_sites / (size * size);
		}
	}

	public double mean() {
		return StdStats.mean(sites_number);

	}

	public double stddev() {
		return StdStats.stddev(sites_number);
	}

	public double confidenceLo() {
		return (this.mean() - Math.sqrt(this.stddev()) * 1.96 / Math.sqrt(T));

	}

	public double confidenceHi() {
		return (this.mean() + Math.sqrt(this.stddev()) * 1.96 / Math.sqrt(T));
	}

	public static void main(String[] args) {

		int size = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		//int size =2;
		//int trials = 10000;
		PercolateStats percs = new PercolateStats(size, trials);
		System.out.println("The mean is" + percs.mean());
		System.out.println("The stddev is" + percs.stddev());
		System.out.println("The confidence interval is [" + percs.confidenceLo() + "," + percs.confidenceHi() + "]" );

	}


}
