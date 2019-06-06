package algo;

import graph.*;

public class Algo1 {
    final int MAX_COLOR = 10;
    int[] color;

    public boolean ok(Graph g, int v, int c) {
        for(int w : g.getAdjList().get(v)) {
            if (color[w] == c) {
                return false;
            }
        }
        return true;
    }

    public boolean dfs(Graph g, int v, int col) {
        int n = g.getDimension();
        if (v == n) {
            return true;
        }
        for (int c = 1; c <= col; c++) {
            if (ok(g, v, c)) {
                color[v] = c;
                if (dfs(g, v + 1, col)) {
                    return true;
                }
                color[v] = 0;
            }
        }
        return false;
    }

    public Graph run(Graph g) {
        long start = System.nanoTime();

        for (int i = 1; i < MAX_COLOR; i++) {
            color = new int[g.getDimension()];
            if (dfs(g, 0, i)) {
                break;
            }
        }

        long end = System.nanoTime() - start;
        g.setTime(end);
        g.setColors(color);
        g.getChromaticNumber();
        return g;
    }
}
