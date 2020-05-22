import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;

        private SearchNode previousNode;

        private int numberOfMoves;

        public SearchNode(Board board, SearchNode previousNode, int numberOfMoves) {
            this.board = board;
            this.previousNode = previousNode;
            this.numberOfMoves = numberOfMoves;
        }

        public Board getBoard() { return this.board; }

        public SearchNode getPreviousNode() { return this.previousNode; }

        public int getNumberOfMoves() { return this.numberOfMoves; }

        @Override
        public int compareTo(SearchNode searchNode) {
            return (this.board.manhattan() + this.numberOfMoves) - (searchNode.board.manhattan() + searchNode.numberOfMoves);
        }
    }
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (!initial.isSolvable())
            throw new IllegalArgumentException("Board is unsolvable");
        MinPriorityQueue<SearchNode> pq = new MinPriorityQueue<>();
        pq.insert(new SearchNode(initial, null , 0));

        SearchNode goalNode = null;
        while (pq.numberOfElements() > 0) {
            SearchNode currentNode = pq.deleteMin();
            if (currentNode.getBoard().isGoal()) {
                goalNode = currentNode;
                break;
            } else {
                for (Board neighbor : currentNode.getBoard().neighbors()) {
                    if (!neighbor.equals(currentNode.getBoard()))
                        pq.insert(new SearchNode(neighbor, currentNode, currentNode.getNumberOfMoves() + 1));
                }
            }
        }
        if (goalNode == null)
            throw new IllegalArgumentException("An error occurred");
        System.out.println(String.format("Minimum number of moves: %s", goalNode.getNumberOfMoves()));
        SearchNode currentSearchNodeBackTrace = goalNode;
        while (currentSearchNodeBackTrace != null) {
            System.out.println(currentSearchNodeBackTrace.getBoard());
            System.out.println();
            currentSearchNodeBackTrace = currentSearchNodeBackTrace.getPreviousNode();
        }
    }

    // min number of moves to solve initial board
//    public int moves()
//
//    // sequence of boards in a shortest solution
//    public Iterable<Board> solution()
//
//    // test client (see below)
    public static void main(String[] args) throws Exception {
        Scanner fileScanner = new Scanner(new BufferedInputStream(new FileInputStream(args[0])));
        int n = fileScanner.nextInt();
        int[][] testTiles = new int[n][];
        int[] currentRow = new int[n];
        int currentRowIdx = 0;
        int currentColIdx = 0;
        while (currentRowIdx < n) {
           if (currentColIdx == n) {
               testTiles[currentRowIdx] = currentRow;
               currentColIdx = 0;
               currentRow = new int[n];
               currentRowIdx += 1;
               continue;
           }
           currentRow[currentColIdx] = fileScanner.nextInt();
           currentColIdx += 1;
        }
        //  System.out.println(n);
        //  int[][] testTiles = new int[4][];
        //  testTiles[0] = new int[]{1,2,3,4};
        //  testTiles[1] = new int[]{5,6,0,8};
        //  testTiles[2] = new int[]{9,10,7,11};
        //  testTiles[3] = new int[]{13,14,15,12};
        Board testBoard = new Board(testTiles);
        Solver solver = new Solver(testBoard);
    }
}
