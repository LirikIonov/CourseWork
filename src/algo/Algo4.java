package algo;

import graph.*;

public class Algo4 {
    Graph gg;
    int[][] adj;
    int[] color;
    int n;

    void addRows(int v, int w) {
        for (int i = 0; i < n; i++) {
            adj[v][i] |= adj[w][i];
        }
    }

    void startOptimisedAlgo() {
        int currentColor = 1;

        for (int v = 0; v < n; v++) {
            if (color[v] == 0) {
                for (int w = 0; w < n; w++) {
                    if (adj[v][w] == 0) {
                        addRows(v, w);
                        color[w] = currentColor;
                    }
                }
                color[v] = currentColor;
                currentColor++;
            }
        }
    }

    public Graph run(Graph g) {
        long start = System.nanoTime();
        n = g.getDimension();
        color = new int[n];
        adj = g.getAdjMatrix();
        for (int i = 0; i < n; i++) {
            adj[i][i] = 1;
        }
        gg = new Graph(g.getName(), adj, g.getDimension());

        startOptimisedAlgo();

        long end = System.nanoTime() - start;
        g.setTime(end);
        g.setColors(color);
        g.getChromaticNumber();
        return g;
    }
}
