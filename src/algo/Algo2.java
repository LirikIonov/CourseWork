package algo;

import graph.*;

public class Algo2 {
    int[] color;
    boolean[] used;

    void greedy(Graph g){
        int n = g.getDimension();
        color[0] = 1;

        for (int i = 1; i < n; i++) {
            for (int w : g.getAdjList().get(i)) {
                if (color[w] != 0) {
                    used[color[w]] = true;
                }
            }

            int firstUnusedColor = -1;
            for (int j = 1; j <= n; j++) {
                if (!used[j]) {
                    firstUnusedColor = j;
                    break;
                }
            }
            color[i] = firstUnusedColor;

            for (int w : g.getAdjList().get(i)) {
                if (color[w] != 0) {
                    used[color[w]] = false;
                }
            }
        }
    }

    public Graph run(Graph g) {
        int n = g.getDimension();
        color = new int[n];
        used = new boolean[n + 1];
        long start = System.nanoTime();

        greedy(g);

        long end = System.nanoTime() - start;
        g.setTime(end);
        g.setColors(color);
        g.getChromaticNumber();
        return g;
    }
}
