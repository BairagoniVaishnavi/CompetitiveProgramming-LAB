import java.util.*;

public class MaxFlowMinCut {

    static final int V = 7; // S, A, B, C, D, E, T

    // BFS for finding augmenting path
    static boolean bfs(int[][] residual, int s, int t, int[] parent) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        q.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && residual[u][v] > 0) {
                    q.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return visited[t];
    }

    // DFS to find reachable vertices in residual graph
    static void dfs(int[][] residual, int s, boolean[] visited) {
        visited[s] = true;

        for (int v = 0; v < V; v++) {
            if (residual[s][v] > 0 && !visited[v]) {
                dfs(residual, v, visited);
            }
        }
    }

    static void minCut(int[][] graph, int s, int t) {

        int[][] residual = new int[V][V];

        // Copy graph
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                residual[i][j] = graph[i][j];

        int[] parent = new int[V];

        int maxFlow = 0;

        // Edmonds-Karp
        while (bfs(residual, s, t, parent)) {

            int pathFlow = Integer.MAX_VALUE;

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residual[u][v]);
            }

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        // Step: find reachable nodes from source
        boolean[] visited = new boolean[V];
        dfs(residual, s, visited);

        System.out.println("Maximum Flow = " + maxFlow);

        System.out.println("\nMin Cut Edges:");

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (visited[i] && !visited[j] && graph[i][j] > 0) {
                    System.out.println(nodeName(i) + " -> " + nodeName(j) + " : " + graph[i][j]);
                }
            }
        }
    }

    // Helper to print node names
    static String nodeName(int i) {
        switch (i) {
            case 0: return "S";
            case 1: return "A";
            case 2: return "B";
            case 3: return "C";
            case 4: return "D";
            case 5: return "E";
            case 6: return "T";
        }
        return "";
    }

    public static void main(String[] args) {

        int[][] graph = new int[V][V];

        // S → A, B, D
        graph[0][1] = 5;
        graph[0][2] = 10;
        graph[0][4] = 4;

        // A → C, T
        graph[1][3] = 1;
        graph[1][6] = 3;

        // B → C, D, E
        graph[2][3] = 7;
        graph[2][4] = 3;
        graph[2][5] = 7;

        // C → T
        graph[3][6] = 5;

        // D → E
        graph[4][5] = 6;

        // E → T
        graph[5][6] = 4;

        minCut(graph, 0, 6);
    }
}