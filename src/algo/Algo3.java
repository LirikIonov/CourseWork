package algo;

import java.util.Arrays;

import graph.*;

public class Algo3 {
    Graph gg;
    int[][] adj;
    int[] color;
    boolean[] used;
    int n;

    void addRows(int v, int w) {
        for (int i = 0; i < n; i++) {
            adj[v][i] &= adj[w][i];
        }
    }

    long getLeastSignificantBit(long x) {
        x &= ~x + 1;
        return x;
    }

    int findNonAdjVertex(int v) {
        StringBuilder s = new StringBuilder(
                Arrays.toString(adj[v])
                        .replaceAll("\\[", "")
                        .replaceAll("]", "")
                        .replaceAll(", ", "")
        );
        s.reverse();
        long w = getLeastSignificantBit(Long.valueOf(s.toString()));
        return Long.numberOfTrailingZeros(Long.highestOneBit(w));
    }

    void startAlgo() {
        int currentColor = 1;
        for (int v = 0; v < n; v++) {
            if (!used[v]) {
                while (findNonAdjVertex(v) != 64 && v != n - 1) {
                    int w = findNonAdjVertex(v);
                    addRows(v, w);
                    color[w] = currentColor;
                    used[w] = true;
                }
                color[v] = currentColor;
                used[v] = true;
                currentColor++;
            }
        }
    }

    public Graph run(Graph g) {
        long start = System.nanoTime();
        n = g.getDimension();
        color = new int[n];
        used = new boolean[n];
        adj = g.getAdjMatrix();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    adj[i][j] ^= 1;
                }
            }
        }
        gg = new Graph(g.getName(), adj, g.getDimension());

        startAlgo();

        long end = System.nanoTime() - start;
        g.setTime(end);
        g.setColors(color);
        g.getChromaticNumber();
        return g;
    }
}
