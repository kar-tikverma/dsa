import java.util.ArrayList;
import java.util.List;

class DisjointSet {
    private int n;
    private int[] par;
    private int[] rank;

    public DisjointSet(int size) {
        init(size);
    }

    public void init(int size) {
        this.n = size;
        par = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
    }

    public int find(int x) {
        if (x == par[x]) {
            return x;
        }

        return par[x] = find(par[x]);
    }

    public void union(int a, int b) {
        int parA = find(a);
        int parB = find(b);

        if (rank[parA] < rank[parB]) {
            par[parA] = parB;
        } else {
            par[parB] = parA;
            if (rank[parA] == rank[parB]) {
                rank[parA]++;
            }
        }
    }
}

public class _33_Graphs_DisjointSets {

    static class Edge {
        int src;
        int dst;
        int wt;

        Edge(int src, int dst, int wt) {
            this.src = src;
            this.dst = dst;
            this.wt = wt;
        }
    }

    static List<List<Edge>> createGraph() {
        int v = 4;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(0, 1, 10));
        graph.get(0).add(new Edge(0, 2, 15));
        graph.get(0).add(new Edge(0, 3, 30));

        graph.get(1).add(new Edge(1, 0, 10));
        graph.get(1).add(new Edge(1, 3, 40));

        graph.get(2).add(new Edge(2, 0, 15));
        graph.get(2).add(new Edge(2, 3, 50));

        graph.get(3).add(new Edge(3, 1, 40));
        graph.get(3).add(new Edge(3, 2, 50));
        graph.get(3).add(new Edge(3, 0, 30));

        // graph.get(4).add(new Edge(4, 0, 1));
        // graph.get(4).add(new Edge(4, 1, -1));
        // graph.get(4).add(new Edge(4, 5, 5));

        // graph.get(5).add(new Edge(5, 0, 1));
        // graph.get(5).add(new Edge(5, 2, 1));
        // graph.get(5).add(new Edge(5, 6, 1));

        // graph.get(6).add(new Edge(6, 5, 1));

        // graph.get(0).add(new Edge(0, 1, 5));
        // // graph.get(0).add(new Edge(0, 2, 1));

        // graph.get(1).add(new Edge(1, 2, 2));
        // graph.get(1).add(new Edge(1, 4, 3));

        // // graph.get(2).add(new Edge(2, 0, 1));
        // graph.get(2).add(new Edge(2, 3, 7));
        // graph.get(2).add(new Edge(2, 4, 9));

        // graph.get(3).add(new Edge(3, 1, 1));
        // graph.get(3).add(new Edge(3, 4, 1));

        // graph.get(4).add(new Edge(4, 2, 1));
        // graph.get(4).add(new Edge(4, 3, 1));

        // graph.get(0).add(new Edge(0, 2, 1));
        // graph.get(0).add(new Edge(0, 1, 1));

        // graph.get(1).add(new Edge(1, 3, 1));

        // graph.get(2).add(new Edge(2, 3, 1));

        return graph;
    }

    public static int kruskalsAlgorithm(List<List<Edge>> graph) {
        int V = graph.size();
        DisjointSet DS = new DisjointSet(V);

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (Edge e : graph.get(i)) {
                edges.add(e);
            }
        }
        edges.sort((a, b) -> a.wt - b.wt);

        int minCost = 0;
        int egdeCount = 0;

        for (int i = 0; egdeCount < V - 1; i++) {
            Edge curr = edges.get(i);

            if (DS.find(curr.src) != DS.find(curr.dst)) {
                DS.union(curr.src, curr.dst);
                minCost += curr.wt;
                egdeCount++;
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        List<List<Edge>> g = createGraph();
        System.out.println(kruskalsAlgorithm(g));
    }
}