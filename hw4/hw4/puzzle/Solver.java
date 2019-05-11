package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Deque;

public class Solver {

    private MinPQ<SearchNode> fringe;
    private Deque<WorldState> solution;

    private class SearchNode implements Comparable<SearchNode>{

        private WorldState state;
        private int moveSoFar;
        private SearchNode prev;
        private int priority;

        private SearchNode(WorldState state, int moveSoFar, SearchNode prev) {
            this.state = state;
            this.moveSoFar = moveSoFar;
            this.prev = prev;
            priority = moveSoFar + state.estimatedDistanceToGoal();
        }

        public int compareTo(SearchNode o) {
            return this.priority - o.priority;
        }

    }

    public Solver(WorldState initial) {


        fringe = new MinPQ<>();
        fringe.insert(new SearchNode(initial, 0, null));

        solution = new ArrayDeque<>();
        SearchNode goal = null;

        while (!fringe.isEmpty()) {
            SearchNode min = fringe.delMin();

            if (min.state.isGoal()) {
                goal = min;
                break;
            } else {
                for (WorldState state : min.state.neighbors()) {
                    if (min.prev == null || !min.prev.state.equals(state)) {

                        SearchNode neighborNode = new SearchNode(state, min.moveSoFar + 1, min);
                        fringe.insert(neighborNode);
                    }
                }
            }
        }

        while (goal != null) {
            solution.addFirst(goal.state);
            goal = goal.prev;
        }

    }



    public int moves() {
        return solution.size() - 1;
    }

    public Iterable<WorldState> solution() {

        return solution;
    }
}