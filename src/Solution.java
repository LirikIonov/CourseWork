import java.io.*;
import graph.*;

public class Solution {
    final int ALGS_MIN_COUNT = 3;
    final int ALGS_MAX_COUNT = 3;
    final int VERTICES_MIN_CNT = 1;
    final int VERTICES_MAX_CNT = 9;

    void run() throws IOException {
        for (int i = ALGS_MIN_COUNT; i <= ALGS_MAX_COUNT; i++) {
            for (int j = VERTICES_MIN_CNT; j <= VERTICES_MAX_CNT; j++) {
                ProcessGraph process = new ProcessGraph(
                        "graph" + j + ".txt",
                        "algo" + i + "Graph" + j + "Out.txt",
                        j,
                        i
                );
                process.run();
            }
        }
        exit();
    }

    public static void main(String[] args) throws IOException {
        new Solution().run();
    }

    void exit() {
        System.exit(0);
    }
}