package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> fringe;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        fringe = new LinkedList<>();
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        marked[v] = true;
        fringe.add(v);
        announce();

        if (v == t) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }

        while (!fringe.isEmpty()) {
            int min = fringe.poll();

            for (int nei : maze.adj(min)) {
                if (!marked[nei]) {
                    distTo[nei] = distTo[min] + 1;
                    edgeTo[nei] =  min;
                    marked[nei] = true;
                    announce();
                    fringe.add(nei);
                    if (nei == t) {
                        return;
                    }
                }
            }
        }


    }


    @Override
    public void solve() {
        bfs(s);
    }
}

