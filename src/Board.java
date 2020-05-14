import java.util.Arrays;
import java.util.Iterator;

public class Board {

    private int[][] board;

    private int hammingDistance = 0;

    private int manhattanDistance = 0;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles.length == 0)
            throw new IllegalArgumentException("tile cannot be empty");
        this.board = tiles;
        int currentOrderedTile = 1;
        for (int i = 0; i < this.board.length; i++) {
           if (this.board[i].length != this.board.length)
               throw new IllegalArgumentException("tiles must be an n *n grid");
           for (int j = 0; j < this.board.length; j++) {
            if (this.board[i][j] != 0 && this.board[i][j] != currentOrderedTile) {
                this.hammingDistance += 1;
                int[] coordinatesForMisplacedTile = this.getCoordinates(this.board[i][j]);
                this.manhattanDistance += (Math.abs(coordinatesForMisplacedTile[0] - i) +
                                            Math.abs(coordinatesForMisplacedTile[1] - j));
            }
            currentOrderedTile += 1;
           }
        }
    }

    // string representation of this board
    public String toString() {
        String[] boardString = new String[this.board.length];
        for (int i = 0; i < this.board.length; i++) {
            String[] currentRow = new String[this.board.length];
            for (int j = 0; j < this.board.length; j++)
               currentRow[j] = this.board[i][j] + " ";
            boardString[i] = String.join(" ", currentRow);
        }
        return String.join("\n", boardString);
    }

    // tile at (row, col) or 0 if blank
    public int tileAt(int row, int col) {
       return this.board[row][col];
    }

    // board size n
    public int size() {
        return this.board.length;
    }

    // number of tiles out of place
    public int hamming() {
        return this.hammingDistance;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return this.manhattanDistance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int currentOrderTile = 1;
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j ++) {
                if (currentOrderTile != this.board[i][j]) return false;
            }
        }
        return true;
    }

    // // does this board equal y?
    public boolean equals(Object y) {
       if (this == y)
           return true;
       if (y == null || getClass() != y.getClass())
           return false;
       Board yBoard = (Board) y;
       if (yBoard.size() != this.size()) return false;
       for (int i = 0; i < this.size(); i++) {
           for (int j = 0; j < this.size(); i++) {
               if (this.tileAt(i, j) != yBoard.tileAt(i,j))
                   return false;
           }
       }
       return true;
    }

    // // all neighboring boards
    public Iterable<Board> neighbors() {
        return new NeighborsIterable(this);
    }

    private class NeighborsIterable implements Iterable<Board> {

        private Board boardWithNeighbors;

        public NeighborsIterable(Board board) {
            this.boardWithNeighbors = board;
        }
        public Iterator<Board> iterator() {
            return new NeighborsIterator();
        }

        private class NeighborsIterator implements Iterator<Board> {
           private Board currentBoardWithNeighbors = NeighborsIterable.this.boardWithNeighbors;

           public boolean hasNext() {

           }

           public Board next() {

           }

           public void remove() {
               throw new UnsupportedOperationException();
           }
        }
    }

    // // is this board solvable?
    // public boolean isSolvable() {}

    public int[] getCoordinates(int tileNumber) {
        int row = (tileNumber / this.board.length) - 1;
        int col = tileNumber % this.board.length;
        if (col == 0) col = this.board.length - 1;
        else {
            row += 1;
            col = -1 + col;
        }
        return new int[]{row, col};
    }


    // unit testing (required)
    public static void main(String[] args) {
        int[][] testTiles = new int[3][3];
        testTiles[0] = new int[]{8,1,3};
        testTiles[1] = new int[]{4,0,2};
        testTiles[2] = new int[]{7,6,5};
        Board testBoard = new Board(testTiles);
        System.out.println(testBoard);
        System.out.println(Arrays.toString(testBoard.getCoordinates(13)));
        System.out.println(testBoard.hamming());
        System.out.println(testBoard.manhattan());
    }

}