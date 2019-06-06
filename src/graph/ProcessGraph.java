package graph;

import algo.*;

import java.io.*;
import java.util.*;

public class ProcessGraph {
    DeluxeScanner in;
    PrintWriter graphOut;
    long totalTime;
    int algoType, graphType;
    int graphsCount = 0;

    public ProcessGraph(String inputName, String outputName, int graphType, int algoType) throws IOException {
        in = new DeluxeScanner(inputName);
        graphOut = new PrintWriter(new FileWriter(outputName), true);
        totalTime = 0;
        this.graphType = graphType;
        this.algoType = algoType;
    }

    public Graph algoProceed(Graph g) {
        if (algoType == 1) {
            return new Algo1().run(g);
        }
        else if (algoType == 2) {
            return new Algo2().run(g);
        }
        else if (algoType == 3) {
            return new Algo3().run(g);
        }
        else {
            return new Algo4().run(g);
        }
    }

    public void processGraphs() {
        String input = in.nextLine();

        while ((input = in.nextLine()) != null) {
            String[] split = input.split(", ");
            String graphTitle = split[0];
            int n = Integer.valueOf(split[1].substring(split[1].indexOf(" ") + 1, split[1].indexOf(".")));
            int[][] adj = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] s = in.nextLine().split(" ");
                for (int j = 0; j < n; j++) {
                    adj[i][j] = Integer.valueOf(s[j]);
                }
            }
            input = in.nextLine();

            Graph g = new Graph(graphTitle, adj, n);
            g = algoProceed(g);
            totalTime += g.getTime();
            writeGraph(g);
            graphsCount++;
        }

        graphOut.close();
    }

    public void writeGraph(Graph g)  {
        StringBuilder res = new StringBuilder();
        res.append(g.getName() + ", chromatic number = " + g.getChromaticNumber() + ", computation time: " + g.getTime() + "\n");

        for (int i = 0; i < g.getDimension(); i++) {
            res.append(i + ": " + g.getColorName(g.getColors()[i]) + "\n");
        }
        res.append("\n");

        graphOut.print(res);
    }

    public void writeTotalTime() throws IOException {
        PrintWriter timeOut = new PrintWriter(new FileWriter("totalAlgo" + algoType + "Graph" + graphType + ".txt"), true);
        timeOut.print("Total time: " + totalTime + " nanoseconds" );
        timeOut.close();

        /*PrintWriter avgOut = new PrintWriter(new FileWriter("avgAlgo" + algoType + "Graph" + graphType + ".txt"), true);
        long avgTime = totalTime / graphsCount;
        avgOut.print(avgTime);
        avgOut.close();*/
    }

    public void run() throws IOException {
        processGraphs();
        writeTotalTime();
    }

    class DeluxeScanner {
        BufferedReader br;
        StringTokenizer st;

        DeluxeScanner(String filename) {
            if (filename != null)
                try {
                    br = new BufferedReader(new FileReader(filename));
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            else
                br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        int[][] nextInt2DArray(int n, int m) {
            int[][] a = new int[n][m];
            for (int i = 0; i < n; i++)
                a[i] = nextIntArray(m);
            return a;
        }
    }
}