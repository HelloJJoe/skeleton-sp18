package lab11.graphs;
import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private MinPQ<Node> fringe;

    private class Node implements Comparable<Node> {
        private int n;
        private int priority;

        private Node(int n, int priority) {
            this.n = n;
            this.priority = priority;
        }

        public int compareTo(Node o) {
            return this.priority - o.priority;
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        fringe = new MinPQ<>();
    }

    /** Estimate of the distance from v to the target. */
    private int h (int v) {
        int vertexX = v % maze.N() + 1;
        int vertexY = v / maze.N() + 1;
        return Math.abs(vertexX - maze.N()) + Math.abs(vertexY - maze.N());
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        fringe.insert(new Node(s, 0));

        while (!fringe.isEmpty()) {
            Node min = fringe.delMin();
            int minNum = min.n;
            marked[minNum] = true;

            announce();

            if (minNum == t) {
                targetFound = true;
            }
            if (targetFound) {
                return;
            }

            for (int nei : maze.adj(minNum)) {
                if (!marked[nei] || distTo[minNum] + 1 < distTo[nei]) {
                    distTo[nei] = distTo[minNum] + 1;
                    edgeTo[nei] = minNum;
                    int priority = distTo[nei] + h(nei);
                    fringe.insert(new Node(nei, priority));
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

