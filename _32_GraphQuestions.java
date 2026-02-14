import java.util.*;

public class _32_GraphQuestions {

    // Leetcode #994
    // Solution 1: Obvious one
    public static int orangesRotting_1 (int[][] grid) {
        rows_RO = grid.length;
        if (rows_RO > 0) {
            cols_RO = grid[0].length;
        }
        else {
            return 0;
        }
        
        Queue<Ele> Q = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < rows_RO; i++)
            for (int j = 0; j < cols_RO; j++)
                if (grid[i][j] == 2)
                    Q.add(new Ele(i,j));
        Q.add (new Ele(-1,-1));
        
        int[] r = {0, 0, 1, -1};
        int[] c = {1, -1, 0, 0};
        while (! Q.isEmpty()) {
            boolean flag = false;
            while (!isDelim(Q.peek())) {
                Ele curr = Q.poll();
                for (int k = 0; k < 4; k++) {
                    if (isValid(curr.x + r[k], curr.y + c[k]) && grid[curr.x + r[k]][curr.y + c[k]] == 1) {
                        if (!flag) {
                            res++;
                            flag = true;
                        }
                        grid[curr.x + r[k]][curr.y + c[k]] = 2;
                        Q.add(new Ele(curr.x + r[k],curr.y + c[k]));
                    }
                }
            }
            
            Q.remove();
            if (!Q.isEmpty()) {
                Q.add(new Ele(-1,-1));
            }
        }

        return (checkAll(grid))? -1: res;
    }
    private static int rows_RO;
    private static int cols_RO;
    private static class Ele {
        int x;
        int y;
        Ele (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static boolean isValid (int i, int j) {
        return (i >= 0 && j >= 0 && i < rows_RO && j < cols_RO);
    }
    private static boolean isDelim (Ele curr) {
        return (curr.x == -1 && curr.y == -1);
    }
    private static boolean checkAll (int grid[][]) {
        for (int i = 0; i < rows_RO; i++)
            for (int j = 0; j < cols_RO; j++)
                if (grid[i][j] == 1)
                    return true;
        return false;
    }

    // Solution 2: Runtime = 1 ms
    public static int orangesRotting_2 (int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    infect(grid, i, j);
                }
            }
        }

        int minInfectingTime = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) return -1;
                if (grid[i][j] - 2 > minInfectingTime) minInfectingTime = grid[i][j] - 2;
            }
        }

        return minInfectingTime;
    }
    private static void infect (int[][] grid, int i, int j) {
        /* 
        *  grid[i][j] > 2 represents the timestamp (in minutes) of when an
           orange becomes rotten due to its neighboring rotten orange.
        *  grid[i][j] = 5 means the orange gets rotten after 5 - 2 = 3 minutes.
        */
        int curr = grid[i][j];

        if ((i > 0) && (grid[i-1][j] == 1 || grid[i-1][j] > curr + 1)) {
            grid[i-1][j] = curr + 1;
            infect(grid, i-1, j);
        }

        if ((j > 0) && (grid[i][j-1] == 1 || grid[i][j-1] > curr + 1)) {
            grid[i][j-1] = curr + 1;
            infect(grid, i, j-1);
        }

        if ((i + 1 < grid.length) && (grid[i+1][j] == 1 || grid[i+1][j] > curr + 1)) {
            grid[i+1][j] = curr + 1;
            infect(grid, i+1, j);
        }

        if ((j + 1 < grid[0].length) && (grid[i][j+1] == 1 || grid[i][j+1] > curr + 1)) {
            grid[i][j+1] = curr + 1;
            infect(grid, i, j+1);
        }
    }

    // Leetcode #695: Runtime = 1 ms
    private static int size_islArea = 0;
    public static int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                }
                
                if (size_islArea > res) {
                    res = size_islArea;
                }
                
                size_islArea = 0;
            }
        }
        
        return res;
    }
    private static void dfs(int[][] grid, int x, int y) {
        grid[x][y] = 0;
        size_islArea += 1;
        
        if (x >= 1 && grid[x-1][y] == 1) {
            dfs(grid, x - 1, y);
        }
        if (y >= 1 && grid[x][y-1] == 1) {
            dfs(grid, x, y - 1);
        }
        if (y + 1 < grid[0].length && grid[x][y+1] == 1) {
            dfs(grid, x, y + 1);
        }
        if (x + 1 < grid.length && grid[x+1][y] == 1) {
            dfs(grid, x + 1, y);
        }
    }

    // Leetcode #127: Hard
    // Solution 1: Runtime = TLE (Apna College Solution)
    public static int ladderLength_1(String beginWord, String endWord, List<String> wordList) {
        if(beginWord == endWord) return 0;
        if (!wordList.contains(endWord)) return 0;
        int level = 0, wordlength = beginWord.length();
        Queue<String> Q = new LinkedList<>();
        Q.add(beginWord);
        while (!Q.isEmpty()) {
            ++level;
            int sizeofQ = Q.size();
            for (int i = 0; i < sizeofQ; ++i) {
                char[] word = Q.peek().toCharArray();
                Q.remove();
                for (int pos = 0; pos < wordlength; ++pos) {
                    char orig_char = word[pos];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        word[pos] = c;
                        if (String.valueOf(word).equals(endWord)) return level + 1;
                        if (!wordList.contains(String.valueOf(word))) continue;
                        wordList.remove(String.valueOf(word));
                        Q.add(String.valueOf(word));
                    }
                    word[pos] = orig_char;
                }
            }
        }

        return 0;
    }

    // Solution 2: Runtime = 1270 ms
    public static int ladderLength_2 (String beginWord, String endWord, List<String> wordList) {
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        HashMap<String, HashSet<String>> graph = new HashMap<String, HashSet<String>>();
        wordList.add(beginWord);

        for(int i=0; i< wordList.size(); i++){
            graph.put(wordList.get(i), new HashSet<String>());
            visited.put(wordList.get(i), false);
        }
        for(int i=0; i<wordList.size(); i++){
            for(int j=i; j<wordList.size(); j++){
                if(isEdge(wordList.get(i), wordList.get(j))){
                    HashSet<String> list_i = graph.get(wordList.get(i));
                    list_i.add(wordList.get(j));
                    HashSet<String> list_j = graph.get(wordList.get(j));
                    list_j.add(wordList.get(i));
                    graph.put(wordList.get(i), list_i);
                    graph.put(wordList.get(j), list_j);
                }
            }
        }
        List<String> candidates = new ArrayList<String>();
        candidates.add(beginWord);

        return bfs(candidates, visited, graph, endWord, 0);
    }
    private static boolean isEdge(String s1, String s2){
        int cnt = 0;
        for(int i=0; i< s1.length(); i++){
            cnt += (s1.charAt(i) != s2.charAt(i) ? 1 : 0);
        }
        return cnt == 1;
    }
    private static int bfs(List<String> candidates, HashMap<String,Boolean> vis, HashMap<String,HashSet<String>> graph, String target, int length) {
        length++;
        if(candidates.size() == 0) {
            return 0;
        }
        Set<String> new_candidates = new HashSet<String>();
        for(int i=0; i<candidates.size(); i++){
            if(vis.get(candidates.get(i)) == true){
                continue;
            }
            vis.put(candidates.get(i), true);
            HashSet<String> next = graph.get(candidates.get(i));
            new_candidates.addAll(next);
        }
        if(new_candidates.contains(target)){
            return length + 1;
        }
        return bfs(new ArrayList<String>(new_candidates), vis, graph, target, length);
    }

    // Solution 3: Runtime = 97 ms
    public static int ladderLength_3 (String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        
        int changes = 1;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                String word = queue.poll();
                if(word.equals(endWord)) return changes;
                
                for(int j = 0; j < word.length(); j++){
                    for(int k = 'a'; k <= 'z'; k++){
                        char arr[] = word.toCharArray();
                        arr[j] = (char) k;
                        
                        String str = new String(arr);
                        if(set.contains(str) && !visited.contains(str)){
                            queue.add(str);
                            visited.add(str);
                        }
                    }
                }
            }
            ++changes;
        }
        return 0;
    }

    // Solution 4: Runtime = 77 ms
    public static int ladderLength_4 (String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        int cnt = 0;
        Set<String> dict = new HashSet<>(wordList);
        Queue<String> que = new LinkedList<>();

        que.add(beginWord);

        while (!que.isEmpty()) {
            cnt++;
            int size = que.size();
            for (int k = 0; k < size; k++) {
                String word = que.poll();
                if (word.equals(endWord))
                    return cnt;
                for (int i = 0; i < word.length(); i++) {
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        char[] arr = word.toCharArray();
                        arr[i] = ch;
                        String midWord = new String(arr);
                        if (dict.contains(midWord)) {
                            que.add(midWord);
                            dict.remove(midWord);
                        }
                    }
                }
            }
        }
        
        return 0; 
    }

    // Solution 5: Runtime = 69 ms
    public static int ladderLength_5 (String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        q.add(null);
        HashSet<String> vis = new HashSet<>();
        vis.add (beginWord);
        int lvl = 1;
        while (!q.isEmpty()) {
            String pop = q.poll();
            if (pop == null) {
                lvl++;
                if (!q.isEmpty()) q.add(null);
            }
            else {
                char[] str = pop.toCharArray();
                for (int i = 0; i < str.length; str[i] = pop.charAt(i), i++){
                    for (char a = 'a'; a <= 'z'; a++) {
                        str[i] = a;
                        String neigh = new String(str);
                        if (set.contains(neigh) && !vis.contains(neigh)) {
                            if (neigh.equals(endWord)) return lvl+1;
                            vis.add(neigh);
                            q.add(neigh);
                        }
                    }
                }
            }
        }
        
        return 0;
    }

    // Solution 6: Runtime = 12 ms
    public static int ladderLength_6 (String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<String>(wordList);
        if (!wordSet.contains(endWord))
            return 0;
        
        Set<String> forwardSet = new HashSet<String>(); 
        Set<String> backwardSet = new HashSet<String>();
        forwardSet.add(beginWord);
        backwardSet.add(endWord);
        wordSet.remove(endWord);
        wordSet.remove(beginWord);
        
        return transform(forwardSet, backwardSet, wordSet);
    }
    private static int transform (Set<String> forwardSet, Set<String> backwardSet, Set<String> wordSet) {
        Set<String> newSet = new HashSet<String>();
        for (String fs : forwardSet) {
            char wordArray[] = fs.toCharArray();
            for (int i = 0; i < wordArray.length; i++) {
                for (int c = 'a'; c <= 'z'; c++) {
                    char origin = wordArray[i];
                    wordArray[i] = (char) c;
                    String target = String.valueOf(wordArray);
                    if (backwardSet.contains(target))
                        return 2;
                    else if( wordSet.contains(target) && !forwardSet.contains(target) ) {
                        wordSet.remove(target);
                        newSet.add(target);
                    }
                    wordArray[i] = origin;
                }
            }
        }
        if (newSet.size() == 0)
            return 0;
        forwardSet = newSet;
        
        int result = forwardSet.size() > backwardSet.size() ?
            transform(backwardSet, forwardSet, wordSet) :
            transform(forwardSet, backwardSet, wordSet);
        return result == 0 ? 0 : result + 1;
    }

    // Solution 7: Runtime = 11 ms
    public static int ladderLength (String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        return search(beginSet, endSet, dict, true, 1);
    }
    private static int search (Set<String> beginSet, Set<String> endSet, Set<String> dict, boolean isForward, int cnt) {
        if (beginSet.isEmpty() || endSet.isEmpty()) return 0;
        cnt++;
        dict.removeAll(beginSet);
        Set<String> nextSet = new HashSet<>();
        for (String str : beginSet) {
            char[] chs = str.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char c = chs[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    chs[i] = j;
                    String tmp = new String(chs);
                    if (!dict.contains(tmp)) continue;
                    if (endSet.contains(tmp)) return cnt;
                    nextSet.add(tmp);
                }
                chs[i] = c;
            }
        }
        return nextSet.size() > endSet.size() ? search(endSet, nextSet, dict, false, cnt) : search(nextSet, endSet, dict, true, cnt);
    }

    // Leetcode #787
    // Solution 1: Runtime = 8 ms
    static class Edge {
        int dest;
        int cost;

        Edge (int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
    static class Info {
        int cost;
        int vertex;
        int stopNumber;

        Info (int vertex, int cost, int stopNumber) {
            this.cost = cost;
            this.vertex = vertex;
            this.stopNumber = stopNumber;
        }
    }
    private static HashMap<Integer, List<Edge>> createGraph_cheapestFlights (int[][] flights) {
        HashMap<Integer, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < flights.length; i++) {
            if (! graph.containsKey (flights[i][0])) {
                graph.put(flights[i][0], new ArrayList<Edge>());
            }
            graph.get(flights[i][0]).add(new Edge(flights[i][1], flights[i][2]));
            if (! graph.containsKey (flights[i][1])) {
                graph.put(flights[i][1], new ArrayList<Edge>());
            }
        }

        return graph;
    }
    public static int findCheapestPrice_1 (int n, int[][] flights, int src, int dest, int K) {
        HashMap<Integer, List<Edge>> graph = createGraph_cheapestFlights(flights);
        int[] dist = new int[n];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        Queue<Info> q = new LinkedList<>();
        q.add(new Info(src, 0, 0));

        while (! q.isEmpty()) {
            Info curr = q.poll();

            if (curr.stopNumber > K) break;
            if (graph.get(curr.vertex) == null) break;

            for (Edge e : graph.get(curr.vertex)) {
                int v = e.dest;
                int wt = e.cost;

                if (dist[v] > curr.cost + wt && curr.stopNumber <= K) {
                    dist[v] = curr.cost + wt;
                    q.add(new Info(v, dist[v], curr.stopNumber + 1));
                }
            }
        }

        return dist[dest] < Integer.MAX_VALUE ? dist[dest] : -1;
    }

    // Solution 2: Runtime = 3 ms
    public static int findCheapestPrice (int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[n][n];
        for (int[] flight : flights) {
            dp[flight[0]][flight[1]] = flight[2];
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer (new int[]{src,0});

        int[] visit = new int[n];
        Arrays.fill(visit, Integer.MAX_VALUE);

        int min = Integer.MAX_VALUE;
        while (k >= 0 && !q.isEmpty()) {
            k--;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] pair = q.poll();
                int source = pair[0];
                int price = pair[1];
                int[] next = dp[source];
                for (int d = 0; d < n; d++) {
                    if (next[d] != 0) {
                        int p = next[d] + price;
                        if (d != dst && visit[d] > p) {
                            q.offer (new int[]{d,p});
                            visit[d] = p;
                        }
                        else if (d == dst) {
                            min = Math.min(min, p);
                        }
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // Leetcode #1135 (Premium required)
    public static int connectingCitiesWithMinimumCost (int[][] cities) {
        int V = cities.length;

        boolean[] vis = new boolean[V];
        PriorityQueue<Edge> edges = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        edges.add(new Edge(cities[0][0], 0));

        int minCost = 0;
        int vertexCount = 0;
        while (!edges.isEmpty() && vertexCount < V) {
            Edge curr = edges.poll();
            if (vis[curr.dest]) {
                continue;
            }

            vis[curr.dest] = true;
            vertexCount++;
            minCost += curr.cost;

            for (int i = 0; i < cities[curr.dest].length; i++) {
                if (cities[curr.dest][i] != 0) {
                    edges.add(new Edge(i, cities[curr.dest][i]));
                }
            }
        }

        return minCost;
    }

    // Leetcode #733
    public static int[][] floodFill (int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) {
            return image;
        }

        floodFill_Util (image, sr, sc, color, image[sr][sc]);
        return image;
    }
    private static void floodFill_Util (int[][] image, int sr, int sc, int color, int prevColor) {
        image[sr][sc] = color;
        if (sr + 1 < image.length && image[sr + 1][sc] == prevColor) {
            floodFill_Util (image, sr + 1, sc, color, prevColor);
        }
        if (sc + 1 < image[0].length && image[sr][sc + 1] == prevColor) {
            floodFill_Util (image, sr, sc + 1, color, prevColor);
        }
        if (sr > 0 && image[sr - 1][sc] == prevColor) {
            floodFill_Util (image, sr - 1, sc, color, prevColor);
        }
        if (sc > 0 && image[sr][sc - 1] == prevColor) {
            floodFill_Util (image, sr, sc - 1, color, prevColor);
        }
    }

    // Leetcode #1192 (Hard)
    // Solution 1: Runtime = 82 ms (Beats 72.31%)
    // Best Solution if we do not pass so many parameters in one function.
    private static class Time {
        int time;

        Time (int time) {
            this.time = time;
        }
    }
    private static List<List<Integer>> createGraph (int n, List<List<Integer>> conn) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> lst : conn) {
            int u = lst.get(0);
            int v = lst.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> conn) {
        List<List<Integer>> graph = createGraph (n, conn);
        boolean[] vis = new boolean[n];
        int[] disc = new int[n];
        int[] low = new int[n];
        Time t = new Time(0);
        List<List<Integer>> bridges = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (! vis[i]) {
                dfs_bridge(i, -1, graph, vis, disc, low, bridges, t);
            }
        }

        return bridges;
    }
    private static void dfs_bridge (int curr, int par, List<List<Integer>> graph, boolean[] vis, int[] disc, int[] low, List<List<Integer>> bridges, Time t) {
        vis[curr] = true;
        disc[curr] = low[curr] = ++t.time;

        for (Integer i : graph.get(curr)) {
            if (! vis[i]) {
                dfs_bridge(i, curr, graph, vis, disc, low, bridges, t);
                low[curr] = Math.min(low[curr], low[i]);
                if (disc[curr] < low[i]) {
                    bridges.add(new ArrayList<>(Arrays.asList(curr, i)));
                }
            }
            else if (i != par) {
                low[curr] = Math.min (low[curr], disc[i]);
            }
        }
    }

    public static int findMotherVertex (int V, List<List<Integer>> graph) {
        boolean[] vis = new boolean[V];
        int node = -1;
        for (int i = 0; i < V; i++) {
            if (! vis[i]) {
                dfs (i, graph, vis);
                node = i;
            }
        }
        Arrays.fill (vis, false);
        dfs (node, graph, vis);
        
        for (boolean val : vis) {
            if (! val) {
                return -1;
            }
        }
        
        return node;
    }
    private static void dfs (int curr, List<List<Integer>> graph, boolean[] vis) {
        vis[curr] = true;
        for (Integer i : graph.get(curr)) {
            if (! vis[i]) {
                dfs (i, graph, vis);
            }
        }
    }

    public static void main (String[] args) {
        int[][] cit = {{0,1,2,3,4},
                       {1,0,5,0,7},
                       {2,5,0,6,0},
                       {3,0,6,0,0},
                       {4,7,0,0,0}};
        System.out.println(connectingCitiesWithMinimumCost(cit));
    }
}