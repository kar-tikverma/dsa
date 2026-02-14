import java.util.*;

public class _31_Graphs {
    static class Edge {
        int dest;
        int wt;

        Edge (int d, int wt) {
            this.dest = d;
            this.wt = wt;
        }
    }

    static List<List<Edge>> createWeightedGraph () {
        int v = 4;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(1, 5));
        graph.get(0).add(new Edge(2, 10));
        // graph.get(0).add(new Edge(3, 1));

        // graph.get(1).add(new Edge(0, 1));
        graph.get(1).add(new Edge(3, 1));

        // graph.get(2).add(new Edge(0, 1));
        graph.get(2).add(new Edge(1, -8));
        // graph.get(2).add(new Edge(3, 1));

        // graph.get(3).add(new Edge(0, 1));
        // graph.get(3).add(new Edge(2, 1));
        // graph.get(3).add(new Edge(4, 1));

        // graph.get(4).add(new Edge(0, 1));
        // graph.get(4).add(new Edge(3, 1));
        // graph.get(4).add(new Edge(5, 1));

        // graph.get(5).add(new Edge(0, 1));
        // graph.get(5).add(new Edge(3, 1));
        // graph.get(5).add(new Edge(4, 1));

        // graph.get(6).add(new Edge(5, 1));


        // graph.get(0).add(new Edge(1, 5));
        // graph.get(0).add(new Edge(2, 1));

        // graph.get(1).add(new Edge(2, 2));
        // graph.get(1).add(new Edge(4, 3));

        // graph.get(2).add(new Edge(0, 1));
        // graph.get(2).add(new Edge(3, 7));
        // graph.get(2).add(new Edge(4, 9));

        // graph.get(3).add(new Edge(1, 1));
        // graph.get(3).add(new Edge(4, 1));

        // graph.get(4).add(new Edge(2, 1));
        // graph.get(4).add(new Edge(3, 1));

        return graph;
    }

    static List<List<Integer>> createGraph () {
        int v = 4;
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(0).add(2);
        // graph.get(0).add(3);

        // graph.get(1).add(0);
        graph.get(1).add(3);

        // graph.get(2).add(0);
        graph.get(2).add(1);
        // graph.get(2).add(3);

        // graph.get(3).add(0);
        // graph.get(3).add(2);
        // graph.get(3).add(4);

        // graph.get(4).add(0);
        // graph.get(4).add(3);
        // graph.get(4).add(5);

        // graph.get(5).add(0);
        // graph.get(5).add(3);
        // graph.get(5).add(4);

        // graph.get(6).add(5);

        return graph;
    }

    public static void bfs (List<List<Integer>> graph) { // TC -> O(V + E), V = no. of vertices and E = edges
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            if (! vis[i]) {
                q.add(i);
            }
            while (! q.isEmpty()) {
                int curr = q.poll();
                if (vis[curr]) {
                    continue;
                }
    
                System.out.println(curr);
                vis[curr] = true;
    
                for (Integer dest : graph.get(curr)) {
                    q.add (dest);
                }
            }
        }
    }

    public static void dfs (List<List<Edge>> graph) {
        boolean[] vis = new boolean[graph.size()];
        
        for (int i = 0; i < graph.size(); i++) {
            if (! vis[i]) {
                dfs_Util(i, graph, vis);
            }
        }
    }
    private static void dfs_Util (int curr, List<List<Edge>> graph, boolean[] vis) { // TC -> O(V + E)
        System.out.print(curr + "  ");
        vis[curr] = true;
        for (Edge e : graph.get(curr)) {
            if (! vis[e.dest]) {
                dfs_Util(e.dest, graph, vis);
            }
        }
    }

    public static boolean hasPath (List<List<Integer>> graph, int src, int dest) {
        boolean[] vis = new boolean[graph.size()];
        return hasPath_Util(src, dest, graph, vis);
    }
    private static boolean hasPath_Util (int src, int dest, List<List<Integer>> graph, boolean[] vis) {
        if (src == dest) {
            return true;
        }

        vis[src] = true;
        for (Integer dst : graph.get(src)) {
            if (! vis[dst] && hasPath_Util(dst, dest, graph, vis)) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsCycle_dfs (List<List<Integer>> graph) { // TC -> O(V + E)
        boolean[] vis = new boolean[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            if (! vis[i] && containsCycle_dfs_Util(i, -1, graph, vis)) {
                return true;
            }
        }

        return false;
    }
    private static boolean containsCycle_dfs_Util (int curr, int par, List<List<Integer>> graph, boolean[] vis) {
        vis[curr] = true;
        for (Integer dest : graph.get(curr)) {
            if (! vis[dest]) {
                if (containsCycle_dfs_Util(dest, curr, graph, vis)) {
                    return true;
                }
            } else if (dest != par) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsCycle_bfs (List<List<Integer>> graph) { // TC -> O(V + E)
        boolean[] vis = new boolean[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            if (!vis[i] && containsCycle_bfs_Util(i, graph, vis)) {
                return true;
            }
        }

        return false;
    }
    private static boolean containsCycle_bfs_Util (int node, List<List<Integer>> graph, boolean[] vis) {
        int parent[] = new int[graph.size()];
        Arrays.fill(parent, -1);

        Queue<Integer> q = new LinkedList<>();

        vis[node] = true;
        q.add(node);

        while (!q.isEmpty()){
            int curr = q.poll();
            for (Integer dest : graph.get(curr)) {
                if (! vis[dest]){
                    vis[dest] = true;
                    q.add(dest);
                    parent[dest] = curr;
                }
                else if (parent[curr] != dest)
                    return true;
            }
        }

        return false;
    }

    public static boolean isBipartite (List<List<Integer>> graph) { // TC -> O(V + E)s
        Queue<Integer> q = new LinkedList<>();
        int[] color = new int[graph.size()];
        for (int i = 0; i < color.length; i++) {
            color[i] = -1;
        }

        for (int i = 0; i < graph.size(); i++) {
            if (color[i] == -1) {
                color[i] = 0;
                q.add (i);
                
                while (! q.isEmpty()) {
                    int curr = q.poll();
                    int nextColor = color[curr] == 0 ? 1 : 0;

                    for (Integer dest : graph.get(curr)) {
                        if (color[dest] == -1) {
                            color[dest] = nextColor;
                            q.add(dest);
                        }
                        else if (color[dest] != nextColor) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean containsCycle_Directed_DFS (List<List<Integer>> graph) { // TC -> O(V + E)
        boolean[] vis = new boolean[graph.size()];
        boolean[] stack = new boolean[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            if (! vis[i] && containsCycle_Directed_DFS_Util(i, graph, vis, stack)) {
                return true;
            }
        }

        return false;
    }
    private static boolean containsCycle_Directed_DFS_Util (int curr, List<List<Integer>> graph, boolean[] vis, boolean[] stack) {
        vis[curr] = true;
        stack[curr] = true;

        for (Integer dest : graph.get(curr)) {
            if (! vis[dest]) {
                if (containsCycle_Directed_DFS_Util(dest, graph, vis, stack)) {
                    return true;
                }
            }
            else if (stack[dest]) {
                return true;
            }
        }

        stack[curr] = false;

        return false;
    }

    public static boolean containsCycle_Directed_BFS(List<List<Integer>> graph) {
        int[] indegree = new int[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
    
        // Calculate indegrees
        for (int i = 0; i < graph.size(); i++) {
            for (Integer dest : graph.get(i)) {
                indegree[dest]++;
            }
        }
    
        // Enqueue nodes with indegree 0
        for (int i = 0; i < graph.size(); i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
    
        int visitedCount = 0; // Count of vis vertices
    
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visitedCount++;
    
            for (Integer dest : graph.get(current)) {
                indegree[dest]--;
                if (indegree[dest] == 0) {
                    queue.add(dest);
                }
            }
        }
    
        // If visitedCount is not equal to the number of vertices, there's a cycle.
        return visitedCount != graph.size();
    }

    private static void topologicalSort_Util (int curr, List<List<Edge>> graph, Stack<Integer> stk, boolean[] vis) {
        vis[curr] = true;
        for (Edge e : graph.get(curr)) {
            if (! vis[e.dest]) {
                topologicalSort_Util(e.dest, graph, stk, vis);
            }
        }

        stk.push(curr);
    }
    public static Stack<Integer> topSort_dfs (List<List<Edge>> graph) {
        boolean[] vis = new boolean[graph.size()];
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < graph.size(); i++) {
            if (! vis[i]) {
                topologicalSort_Util(i, graph, stk, vis);
            }
        }

        return stk;
    }

    public static List<Integer> topSort_bfs (List<List<Integer>> graph) {
        int[] indeg = calcIndeg(graph);

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (! q.isEmpty()) {
            int curr = q.poll();
            res.add(curr);

            for (Integer dest : graph.get(curr)) {
                indeg[dest]--;
                if (indeg[dest] == 0) {
                    q.add(dest);
                }
            }
        }

        return res;
    }
    private static int[] calcIndeg (List<List<Integer>> graph) {
        int[] indeg = new int[graph.size()];
        boolean[] vis = new boolean[graph.size()];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < indeg.length; i++) {
            if(! vis[i]) {
                q.add(i);
            }

            while (! q.isEmpty()) {
                int curr = q.poll();
                vis[curr] = true;

                for (Integer dest : graph.get(curr)) {
                    if (! vis[dest]) {
                        q.add(dest);
                    }
                    indeg[dest]++;
                }
            }
        }

        return indeg;
    }

    // Leetcode #210 Course Schedule II
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] i : prerequisites) {
            graph.get(i[1]).add(i[0]);
        }

        int[] indeg = calcIndegree (graph);
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }

        int[] res = new int[indeg.length];
        int idx = 0;
        while (! q.isEmpty()) {
            int curr = q.poll();
            res[idx++] = curr;
            for (Integer dest : graph.get(curr)) {
                indeg[dest]--;
                if (indeg[dest] == 0) {
                    q.add(dest);
                }
            }
        }
        if (idx < graph.size()) {
            return new int[0];
        }

        return res;
    }
    private static int[] calcIndegree (List<List<Integer>> graph) {
        int[] indeg = new int[graph.size()];
        
        for (int i = 0; i < graph.size(); i++) {
            for (Integer dest : graph.get(i)) {
                indeg[dest]++;
            }
        }

        return indeg;
    }

    public static void allPaths (List<List<Integer>> graph, int src, int dest) {
        Stack<Integer> path = new Stack<>();
        
        allPaths_Util(graph, src, dest, path);
    }
    private static void allPaths_Util (List<List<Integer>> graph, int src, int dest, Stack<Integer> path) {
        if (src == dest) {
            for (Integer i : path) {
                System.out.print(i + " -> ");
            }
            System.out.println(dest);
            return;
        }

        path.push(src);
        for (Integer dst : graph.get(src)) {
            allPaths_Util(graph, dst, dest, path);
        }
        path.pop();

        return;
    }

    static class Pair implements Comparable<Pair> {
        int node;
        int dist;
        public Pair (int node, int distance) {
            this.node = node;
            this.dist = distance;
        }

        @Override
        public int compareTo (Pair p2) {
            return this.dist - p2.dist;
        }
    }
    public static int[] dijkstraShortestPathAlgorithm (List<List<Edge>> graph, int src) { // TC -> O(V + ElogV)
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        boolean[] vis = new boolean[graph.size()];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (! pq.isEmpty()) {
            Pair curr = pq.poll();
            if (! vis[curr.node]) {
                vis[curr.node] = true;
                for (Edge e : graph.get(curr.node)) {
                    int u = curr.node;
                    int v = e.dest;
                    int wt = e.wt;

                    // Relaxation Step
                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }

        return dist;
    }

    public static int[] bellmanFordAlgorithm (List<List<Edge>> graph, int src) {
        int V = graph.size();
        
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        for (int i = 0; i < V - 1; i++) { // TC -> O(V * E)
            for (int j = 0; j < V; j++) { // TC -> O(E)
                int u = j;
                if (dist[u] == Integer.MAX_VALUE) {
                    continue;
                }

                for (Edge e : graph.get(j)) {
                    int v = e.dest;
                    int wt = e.wt;

                    // relaxation
                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                    }
                }
            }
        }

        return dist;
    }

    static class EdgePair {
        int wt;
        int dest;

        EdgePair (int dest, int wt) {
            this.wt = wt;
            this.dest = dest;
        }
    }
    public static int primsAlgorithm (List<List<Edge>> graph) {
        int V = graph.size();
        boolean[] vis = new boolean[V];
        PriorityQueue<EdgePair> edges = new PriorityQueue<>((a, b) -> a.wt - b.wt);

        edges.add(new EdgePair(0, 0));
        int vertexCount = 0;
        int minCost = 0;

        while (! edges.isEmpty() && vertexCount < V) {
            EdgePair curr = edges.poll();
            if (! vis[curr.dest]) {
                vis[curr.dest] = true;
                minCost += curr.wt;
                vertexCount++;

                for (Edge e : graph.get(curr.dest)) {
                    edges.add(new EdgePair(e.dest, e.wt));
                }
            }
        }

        return minCost;
    }

    // Prints Strongly Connected Components
    public static void kosarajuAlgorithm (List<List<Edge>> graph) {
        int V = graph.size();

        // Step 1: Get nodes in Stack (Topological Order).
        Stack<Integer> topOrder = topSort_dfs(graph);

        // Step 2: Transpose the graph.
        List<List<Edge>> transpose = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            transpose.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            for (Edge e : graph.get(i)) {
                int u = i;
                int v = e.dest;
                int wt = e.wt;
                transpose.get(v).add(new Edge(u, wt));
            }
        }

        // Step 3: Perform DFS according to the stack (topOrder) nodes on transpose graph.
        boolean[] vis = new boolean[V];
        System.out.println("Strongly Connected Components:");
        while (! topOrder.isEmpty()) {
            int curr = topOrder.pop();
            if (! vis[curr]) {
                dfs_Util(curr, transpose, vis);
                System.out.println();
            }
        }
    }

    private static class Time {
        int time;

        Time (int time) {
            this.time = time;
        }
    }
    public static List<List<Integer>> tarjanBridges (List<List<Integer>> graph) {
        int v = graph.size();
        boolean[] vis = new boolean[v];
        int[] disc = new int[v]; // discovery time
        int[] low = new int[v];  // lowest discovery time among neighbors
        Time t = new Time(0);
        List<List<Integer>> bridges = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            if (! vis[i]) {
                dfs_bridge(i, -1, graph, vis, disc, low, bridges, t);
            }
        }

        return bridges;
    }
    private static void dfs_bridge (int curr, int par, List<List<Integer>> graph, boolean[] vis, int[] disc, int[] low, List<List<Integer>> bridges, Time t) {
        vis[curr] = true;
        disc[curr] = low[curr] = ++t.time;

        for (Integer dst : graph.get(curr)) {
            if (! vis[dst]) {
                dfs_bridge(dst, curr, graph, vis, disc, low, bridges, t);
                low[curr] = Math.min(low[curr], low[dst]);
                if (disc[curr] < low[dst]) {
                    bridges.add(new ArrayList<>(Arrays.asList(curr, dst)));
                }
            }
            else if (dst != par) {
                low[curr] = Math.min (low[curr], disc[dst]);
            }
        }
    }

    public static HashSet<Integer> ArticulationPoints (List<List<Integer>> graph) {
        int v = graph.size();
        boolean[] vis = new boolean[v];
        int[] disc = new int[v]; // discovery time
        int[] low = new int[v];  // lowest discovery time among neighbors
        Time t = new Time(0);

        // We use HashSet and not an ArrayList because sometimes an already existing
        // articulation point gets introduced again in the list.
        HashSet<Integer> AP = new HashSet<>();
        
        for (int i = 0; i < v; i++) {
            if (! vis[i]) {
                dfs_AP(i, -1, graph, vis, disc, low, AP, t);
            }
        }

        return AP;
    }
    private static void dfs_AP (int curr, int par, List<List<Integer>> graph, boolean[] vis, int[] disc, int[] low, HashSet<Integer> AP, Time t) {
        vis[curr] = true;
        disc[curr] = low[curr] = ++t.time;
        int disconnectedChildren = 0;

        for (Integer dst : graph.get(curr)) {
            if (! vis[dst]) {
                dfs_AP(dst, curr, graph, vis, disc, low, AP, t);
                low[curr] = Math.min(low[curr], low[dst]);
                if (par != -1 && disc[curr] <= low[dst]) {
                    AP.add(curr);
                }
                disconnectedChildren++;
                // if (disc[curr] <= low[e.dest]) {
                //     AP.add(curr);
                // }
            }

            // Backedge case (also called cycle condition)
            else if (dst != par) {
                low[curr] = Math.min (low[curr], disc[dst]);
            }
        }

        if (par == -1 && disconnectedChildren > 1) {
            AP.add(curr);
        }
    }

    public static void main (String[] args) {
        //
    }

    public static void print_1D (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}