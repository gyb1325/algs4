import edu.princeton.cs.algs4.MinPQ;
import java.lang.Math;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;


public class Board {
    private int dim;
    private int[][] block_copy;
    public Board(int[][] blocks) {         // construct a board from an n-by-n array of blocks
        dim = blocks.length;
        block_copy = new int[dim][dim];
        for (int i = 0; i < dim; i++) {                               // (where blocks[i][j] = block in row i, column j)
            for (int j = 0; j < dim; j++ ) {
                block_copy[i][j] = blocks[i][j];
            }
        }

    }
    public int dimension() {               // board dimension n
        return dim;
    }
    public int hamming() {                 // number of blocks out of place
        int ham = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++ ) {
                if (!(i == (dim - 1) && j == (dim - 1)))
                    if ((i * dim + j + 1) != block_copy[i][j] ) {
                        ham++;
                    }
            }
        }

        return ham;
    }
    public int manhattan() {               // sum of Manhattan distances between blocks and goal
        int mat = 0;
        for (int i = 0; i < dim ; i++) {
            for (int j = 0; j < dim; j++ ) {
                if (block_copy[i][j] != 0) {
                    mat += Math.abs(i + j - block_copy[i][j] / dim - block_copy[i][j] % dim + 1);
                }
            }
        }
        return mat;
    }
    public boolean isGoal() {              // is this board the goal board?
        return (hamming() == 0);
    }

    private void exch((int[][] blocks, index1, index2) {
        int a = 0;
        a = blocks[index1 / dim][index1 % dim - 1];
        blocks[index1 / dim][index1 % dim - 1] = blocks[index2 / dim][index2 % dim - 1] ;
        locks[index2 / dim][index2 % dim - 1] = a;

    }

    public Board twin() {                  // a board that is obtained by exchanging any pair of blocks


        int i = StdRandom.uniform(1, dim * dim - 1);
        int j = StdRandom.uniform(1, dim * dim - 1);
        int[][] block_temp = new int[dim][dim];
        for (int i = 0; i < dim; i++) {                               // (where blocks[i][j] = block in row i, column j)
            for (int j = 0; j < dim; j++ ) {
                block_temp[i][j] = block_copy[i][j];
            }
        }

        while ((i == j) || block_temp[i / dim][i % dim - 1] == 0 || block_temp[j / dim][j % dim - 1] == 0) {
            if (i == 0) {
                i =  StdRandom.uniform(1, dim * dim - 1);
            } else {
                j =  StdRandom.uniform(1, dim * dim - 1);
            }
            exch(block_temp, i , j);
            return new Board(block_temp);

        }

        public boolean equals(Object y) {      // does this board equal y?

        }
        public Iterable<Board> neighbors()     // all neighboring boards
        public String toString()               // string representation of this board (in the output format specified below)

        public static void main(String[] args) // unit tests (not graded)
    }