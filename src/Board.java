import java.util.Arrays;

public class Board {

    private int[][] board;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
       this.board = tiles;
    }

    // string representation of this board
    public String toString() {
        String[] boardString = new String[this.board.length];
        for (int i = 0; i < this.board.length; i++) {
            String[] currentRow = new String[this.board.length + 1]; // +1 for new line char
            for (int j = 0; j < this.board.length; j++)
               currentRow[j] = this.board[i][j] + " ";
            currentRow[currentRow.length -1] = "\n";
            boardString[i] = Arrays.toString(currentRow);
        }
        return Arrays.toString(boardString);
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
    public int hamming()

    // sum of Manhattan distances between tiles and goal
    public int manhattan()

    // is this board the goal board?
    public boolean isGoal()

    // does this board equal y?
    public boolean equals(Object y)

    // all neighboring boards
    public Iterable<Board> neighbors()

    // is this board solvable?
    public boolean isSolvable()

    // unit testing (required)
    public static void main(String[] args)

}