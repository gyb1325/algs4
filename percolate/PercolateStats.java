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
	public int [] sites_number;
	public	int sites_numbers;
	public PercolateStats(int n, int trials) {
		Percolation perc = new Percolation(n);
		T = trials;
		mean_value = 0.0d;
		sites_number = new int[trials];
	}

	public double mean() {
		return	StdStats.mean(sites_number);

	}

	public double stddev() {
		return	StdStats.stddev(sites_number);
	}

	public double confidenceLo() {
		return	(this.mean() - Math.sqrt(this.stddev()) * 1.96 / Math.sqrt(T));

	}

	public double confidenceHi() {
		return	(this.mean() - Math.sqrt(this.stddev()) * 1.96 / Math.sqrt(T));
	}

	public static void main(String[] args) {

		int size = 200;
		int trials = 100;

		PercolateStats percs = new PercolateStats(size, trials);
		for (int p = 0; p < trials; p++) {
			site_numbers = 0;
			while (!percs.perc.percolate()) {
				int i = StdRandom.uniform(1, size + 1);
				int j = StdRandom.uniform(1, size + 1);
				percs.perc.open(i, j);
				site_numbers++;
			}
			sites_number[p] = site_numbers;
		}
		System.out.println("The mean is" + percs.mean());
		System.out.println("The stddev is" + percs.stddev());
		System.out.println("The confidence interval is [" + percs.confidenceLo() + "," + percs.confidenceHi() + "]" );

	}


}
