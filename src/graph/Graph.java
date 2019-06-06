package graph;

import java.math.*;
import java.util.*;

public class Graph {
    final int COUNT_OF_SIGNS = 7;
    List<List<Integer>> g = new ArrayList<>();
    String graphTitle;
    int[][] adj;
    int[] color;
    int n;
    long compTime;
    int chromaticNumber = -1;

    public enum Colors {
        WHITE, RED, BLUE, GREEN, YELLOW, BLACK, PURPLE, ORANGE, CYAN, PINK, BROWN
    }

    public Graph(String graphTitle, int[][] adj, int n) {
        this.graphTitle = graphTitle;
        this.adj = new int[n][n];
        this.color = new int[n];
        this.n = n;
        compTime = 0;

        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.adj[i][j] = adj[i][j];
                if (adj[i][j] == 1) {
                    addEdge(i, j);
                }
            }
        }
    }

    public void addEdge(int v, int w){
        if (!g.get(v).contains(w) && !g.get(w).contains(v)) {
            g.get(v).add(w);
            g.get(w).add(v);
        }
    }

    public List<List<Integer>> getAdjList() {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> r = new ArrayList<>();
            for (int e : g.get(i)) {
                r.add(e);
            }
            res.add(r);
        }
        return res;
    }

    public int[][] getAdjMatrix() {
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = adj[i][j];
            }
        }
        return res;
    }

    public int[] getColors() {
        return this.color;
    }

    public Colors getColorName(int i) {
        return Colors.values()[i];
    }

    public void setColors(int[] c) {
        System.arraycopy(c, 0, color, 0, n);
    }

    public int getDimension() {
        return this.n;
    }

    public String getName() {
        return this.graphTitle;
    }

    public long getTime() {
        return this.compTime;
    }

    public void setTime(long compTime) {
        this.compTime = compTime;
    }

    public int getChromaticNumber() {
        if (chromaticNumber == -1) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                set.add(color[i]);
            }
            return set.size();
        }
        return chromaticNumber;
    }
}
