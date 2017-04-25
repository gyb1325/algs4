import java.lang.Math;
import edu.princeton.cs.algs4.StdRandom;
import java.lang.StringBuilder;
import java.util.ArrayList;


public class Board {
    private int dim;
    private int hamm;
    private int manhat;
    private int[][] block_copy;
    private ArrayList<Board> neighborboard;
    private int index_0 ;
    public Board(int[][] blocks) {         // construct a board from an n-by-n array of blocks
        if (blocks == null)
            throw new java.lang.NullPointerException();
        dim = blocks.length;
        block_copy = new int[dim][dim];
        for (int i = 0; i < dim; i++) {                               // (where blocks[i][j] = block in row i, column j)
            for (int j = 0; j < dim; j++ ) {
                if (blocks[i][j] == 0)
                    index_0 = i * dim + j + 1;
                else {
                    manhat += (Math.abs(i - (blocks[i][j] - 1) / dim) + Math.abs(j - (blocks[i][j] - 1) % dim));
                }
                if (!(i == dim - 1 && j == dim - 1)) {
                    if ((i * dim + j + 1) != blocks[i][j])
                        hamm++;
                }

                block_copy[i][j] = blocks[i][j];
            }
        }

    }
    public int dimension() {               // board dimension n
        return dim;
    }
    public int hamming() {                 // number of blocks out of place
        return hamm;
    }
    public int manhattan() {               // sum of Manhattan distances between blocks and goal
        return manhat;
    }
    public boolean isGoal() {              // is this board the goal board?
        return (hamn == 0);
    }

    private void exch(int[][] blocks, int index1, int index2) {
        int a = 0;
        a = blocks[(index1 - 1) / dim][(index1 - 1) % dim ];
        blocks[(index1 - 1) / dim][(index1 - 1) % dim ] = blocks[(index2 - 1) / dim][(index2 - 1) % dim ] ;
        blocks[(index2 - 1) / dim][(index2 - 1) % dim ] = a;

    }

    public Board twin() {                  // a board that is obtained by exchanging any pair of blocks
        int [][] block_temp = new int[dim][dim];
        for (int p = 0; p < dim; p++) {                               // (where blocks[i][j] = block in row i, column j)
            for (int q = 0; q < dim; q++ ) {
                block_temp[p][q] = block_copy[p][q];
            }
        }
        if (index_0 == 1 || index_0 == 2) {
            exch(block_temp, 3 , 4);
            return new Board(block_temp);
        } else {
            exch(block_temp, 1 , 2);
            return new Board(block_temp);
        }
        // int i = StdRandom.uniform(1, dim * dim - 1);
        // int j = StdRandom.uniform(1, dim * dim - 1);
        // int[][] block_temp = new int[dim][dim];
        // for (int p = 0; p < dim; p++) {                               // (where blocks[i][j] = block in row i, column j)
        //     for (int q = 0; q < dim; q++ ) {
        //         block_temp[p][q] = block_copy[p][q];
        //     }
        // }

        // while ((i == j) || block_temp[(i - 1) / dim][(i - 1) % dim ] == 0 || block_temp[(j - 1)  / dim][(j - 1) % dim] == 0) {
        //     if (i == 0) {
        //         i =  StdRandom.uniform(1, dim * dim - 1);
        //     } else {
        //         j =  StdRandom.uniform(1, dim * dim - 1);
        //     }
        // }
        // exch(block_temp, i , j);
        // return new Board(block_temp);


    }

    public boolean equals(Object y) {      // does this board equal y?
        if (!(y instanceof Board) ) return false;
        Board that = (Board) y;

        if (this.hamming() != that.hamming() || this.manhattan() != that.manhattan() || this.dim != that.dim || this.index_0 != that.index_0 ) {
            return false;
        }
        for (int i = 0; i < dim; i++ ) {
            for (int j = 0; j < dim; j++) {
                if (this.block_copy[i][j] != that.block_copy[i][j])return false;
            }
        }

        return true;

    }
    public Iterable<Board> neighbors() {   // all neighboring boards
        neighborboard = new ArrayList<Board>();

        int row = (index_0 - 1) / dim;
        int col = (index_0 - 1) % dim;
        if (row + 1 < dim) {
            exch(block_copy, index_0, index_0 + dim);
            neighborboard.add(new Board(block_copy));
            exch(block_copy, index_0, index_0 + dim);
        }
        if (row >= 1) {
            exch(block_copy, index_0, index_0 - dim);
            neighborboard.add(new Board(block_copy));
            exch(block_copy, index_0, index_0 - dim);
        }
        if (col + 1 < dim) {
            exch(block_copy, index_0, index_0 + 1);
            neighborboard.add(new Board(block_copy));
            exch(block_copy, index_0, index_0 + 1);
        }
        if (col >= 1) {
            exch(block_copy, index_0, index_0 - 1);
            neighborboard.add(new Board(block_copy));
            exch(block_copy, index_0, index_0 - 1);
        }
        return neighborboard;

    }
    public String toString() {             // string representation of this board (in the output format specified below)
        StringBuilder output = new StringBuilder();
        output.append(dim);
        output.append("\n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                output.append(String.format("%2d ", block_copy[i][j]));
            }
            output.append("\n");
        }

        output.delete(output.length() - "\n".length(), output.length());
        return output.toString();
    }

    public static void main(String[] args) { // unit tests (not graded)

    }
}