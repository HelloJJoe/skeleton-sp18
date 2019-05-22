package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int[] parent;
    private boolean targetFound = false;

    public MazeCycles(Maze m) {
        super(m);
        s = maze.xyTo1D(1, 1);
        parent = new int[maze.N() * maze.N()];
        parent[s] = 0;

    }

    // Perform a cycle check from vertex v
    private void cc(int v) {
        marked[v] = true;
        announce();

        if (targetFound) {
            return;
        }

        for (int nei : maze.adj(v)) {
            if (!marked[nei]) {
                parent[nei] = v;
                cc(nei);
            } else if (nei != parent[v]) {
                parent[nei] = v;
                int start = v;
                while (nei != start) {
                    edgeTo[start] = parent[start];
                    start = parent[start];
                }
                edgeTo[start] = parent[start];
                announce();
                targetFound = true;
                return;
            }
            if (targetFound) {
                return;
            }
        }
    }

    @Override
    public void solve() {
        cc(s);
    }
}

