public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;

        private SearchNode previousNode;

        private int numberOfMoves;

        public SearchNode(Board board, SearchNode previousNode, int numberOfMoves) {
            this.board = board;
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
        System.out.println(goalNode.getBoard());
    }

    // min number of moves to solve initial board
//    public int moves()
//
//    // sequence of boards in a shortest solution
//    public Iterable<Board> solution()
//
//    // test client (see below)
    public static void main(String[] args) {
        int[][] testTiles = new int[4][];
        testTiles[0] = new int[]{1,2,3,4};
        testTiles[1] = new int[]{5,6,0,8};
        testTiles[2] = new int[]{9,10,7,11};
        testTiles[3] = new int[]{13,14,15,12};
        Board testBoard = new Board(testTiles);
        Solver solver = new Solver(testBoard);
    }
}
