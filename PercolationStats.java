/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private Percolation perc;
    private int T;
    private double[] statsArray;
    public PercolationStats(int N, int T) {
        this.T = T;
        this.statsArray = new double[T];

        for(int i = 0; i < T; i++){
            perc = new Percolation(N);
            while(!perc.percolates()){
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if(!perc.isOpen(row, col))
                    perc.open(row, col);
            }
            statsArray[i] = (double) perc.numberOfOpenSites() / (N*N);
        }
    }
    public double mean() {
        return StdStats.mean(statsArray);
    }
    public double stddev() {
        return StdStats.stddev(statsArray);
    }
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(T);
    }
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(T);
    }

    public static void main(String[] args) {
        // Not required for the API, but useful to test if
        // your code is doing reasonable things
        Stopwatch sw = new Stopwatch();
        PercolationStats st = new PercolationStats(1600,100);
        StdOut.println("Mean: = " + String.valueOf(st.mean()));
        StdOut.println("std dev: = " + String.valueOf(st.stddev()));
        StdOut.println("Confidence Low: = " + String.valueOf(st.confidenceLow()));
        StdOut.println("Confidence High: = " + String.valueOf(st.confidenceHigh()));
        double timer = sw.elapsedTime();
        StdOut.println("Time taken: " + timer);
        // ...
    }
}