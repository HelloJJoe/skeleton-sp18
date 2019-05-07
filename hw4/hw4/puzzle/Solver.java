package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {

    private MinPQ<SearchNode> fringe;
    private SearchNode initialState;
    private Queue<WorldState> solution;
    private int moveMadeSoFar;

    private class SearchNode {
        private WorldState state;
        private int dist;
        private SearchNode prev;

        private SearchNode(WorldState state, int dist, SearchNode prev) {
            this.state = state;
            this.dist = dist;
            this.prev = prev;
        }
    }

    public Solver(WorldState initial) {
        initialState = new SearchNode(initial, 0, null);

        Comparator c = new Comparator<SearchNode>() {
            @Override
            public int compare (SearchNode a, SearchNode b) {
                return a.dist - b.dist;
            }
        };

        fringe = new MinPQ<>(c);
        fringe.insert(initialState);
        moveMadeSoFar = 0;
        solution = new LinkedList<>();

        while (!fringe.isEmpty()) {
            SearchNode min = fringe.delMin();
            solution.add(min.state);
            if (min.state.isGoal()) {
                return;
            } else {
                for (WorldState state : min.state.neighbors()) {
                    moveMadeSoFar += 1;
                    int dist = moveMadeSoFar + state.estimatedDistanceToGoal();
                    SearchNode neighborNode = new SearchNode(state, dist, min);
                    fringe.insert(neighborNode);
                }
            }
        }

    }

    public int moves() {
        return moveMadeSoFar;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }
}