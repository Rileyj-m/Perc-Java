/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class Percolation {
    private WeightedQuickUnionUF wquUF; // weighted quick union
    private boolean[][] gridOfSights; // boolean array for the sites
    private int matrixLength; // the length of the matrix
    private int topHolder; // the top holder, to handle percolation, and connection when opening sites
    private int bottomHolder; // the bottom holder, to handle percolation, and connection when opening sites.
    private int openSitesNumm; // the total number of open sites, better to do this than to loop (makes it constant).

    public Percolation(int N)  {
        // create N-by-N grid, with all sites initially blocked
        if (N <= 0){
            throw new IllegalArgumentException("Cannot create a matrix with a value of n equal to or less than zero.");
        }
        matrixLength = N;
        gridOfSights = new boolean[N][N];
        bottomHolder = N*N + 1;
        topHolder = N*N;
        wquUF = new WeightedQuickUnionUF(N*N+2);
        openSitesNumm = 0;
    }
    public void open(int row, int col) {
        if (col < 0 || col > matrixLength) // Checking if col is less than 1, or greater than matrix length. row and col start are 1-n.
            throw new IndexOutOfBoundsException("Col cannot be outside the bounds of the array.");
        if (row < 0 || row > matrixLength) // Checking if col is less than 1, or greater than matrix length. row and col start are 1-n.
            throw new IndexOutOfBoundsException("Row cannot be outside the bounds of the array.");
        if (!isOpen(row, col)){
            gridOfSights[row][col] = true; // Setting the sight to open.
            int index = Index(row, col); // using the index method, so I can Union the two.
            openSitesNumm++; // add an open site to the count, considering that we just opened the site.

            if (row == 0)
                wquUF.union(index, topHolder); // if in the first row, connect it to the top holder for determining if it percolates.
            if (row == matrixLength-1)
                wquUF.union(index, bottomHolder); // Same thing, but for the bottom. Check if it's on the bottom row, then connect, so we can determine percolation.
            if (row >= 1 && isOpen(row-1, col)) // check if row is 1 or greater, and then check the index above it.
                wquUF.union(index, Index(row-1, col)); //if yes, union that index, with the index that was open above it.
            if (row <= matrixLength && isOpen(row+1, col)) // check if row is less than or equal to the total length of array. (checking below it)
                wquUF.union(index, Index(row+1, col)); //if yes, then we can connect the row at index, with the one below it.
            if (col >= 1 && isOpen(row, col-1)) //check if the col is greater than 1 and checking if the site to the left is open.
                wquUF.union(index, Index(row, col-1)); //if yes, then we union the site with the one to the left.
            if (col <= matrixLength && isOpen(row, col+1)) //check if the col is less than or equal to matrix lengh, and check the one to the right.
                wquUF.union(index, Index(row, col+1)); //if it is open to the right we union the two.
        }
    }
    public boolean isOpen(int row, int col) {
        if((row < 0 || row == matrixLength) || col < 0 || col == matrixLength)
            return false;
        return gridOfSights[row][col]; // returns true is it's open, false otherwise.
    }
    public boolean isFull(int row, int col) {
        if (col < 0 || col > matrixLength) // Checking if col is less than 1, or greater than matrix length. row and col start are 1-n.
            throw new IndexOutOfBoundsException("Col cannot be outside the bounds of the array.");
        if (row < 0 || row > matrixLength) // Checking if col is less than 1, or greater than matrix length. row and col start are 1-n.
            throw new IndexOutOfBoundsException("Row cannot be outside the bounds of the array.");
        if (isOpen(row, col)) // is the site open?
            if (wquUF.connected(Index(row, col), topHolder)) // if yes, let's check if it's connected to the top of the matrix.
                return true; // it is.
            if (wquUF.connected(Index(row, col), bottomHolder)) // Check if it's connected to bottom.
                return true; // it is.
        return false; // it isn't.
    }
    public int numberOfOpenSites() {
        /*int nummOfOpenSites = 0;
        int width = matrixLength;
        int maxindex = gridOfSights.length * width;
        for(int i = 0; i < maxindex; i++){
            if(gridOfSights[i / width][i % width] == true){
                nummOfOpenSites += 1;
            }
        }
        return nummOfOpenSites;*/ // found a better way than this.
        return openSitesNumm;
    }
    public boolean percolates() {
        return wquUF.connected(topHolder, bottomHolder); // check if there is connection between the top and bottom holders of the matrix.
    }
    private int Index(int row, int col){
        return ((row) * matrixLength) + (col); // Doug showed me this in office hours, it's an index to handle quick-union when using a 2D array.
        // Although I didn't remember it fully, so I adjusted it to what works for me. all examples worked with this method of handling the index.
    }

    public static void main(String[] args) {
        int n = 5;
        Percolation percolation = new Percolation(n);
        int count = 0;
        for (int i = 0; i < percolation.matrixLength; i++)
            for(int j = 0; j < n; j++){
                double prob = StdRandom.uniform(0, 100);
                if(prob > 50){
                    percolation.open(i, j);
                    assert percolation.isOpen(i, j) == true;
                    count++;
                }
            }

        assert percolation.numberOfOpenSites() == count;
        assert percolation.Index(0,0) == 0;
        assert percolation.Index(0,4) == 4;
        assert percolation.Index(2,2) == 12;
        assert percolation.Index(4,3) == 23;

        int t = 3;
        Percolation percolation1 = new Percolation(t);

        percolation1.open(1, 0);
        percolation1.open(2, 0);
        percolation1.open(0,0);

        assert percolation1.isFull(1,0) == true;
        assert percolation1.isFull(2,0) == true;
        assert percolation1.isFull(0,0) == true;

        assert percolation1.isFull(0,1) == false;
        assert percolation1.isFull(0,2) == false;
        assert percolation1.isFull(1,2) == false;

    }
}