
class DSU {

    int[] parent;
    int[] rank;

    DSU(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;   // each node is its own parent
            rank[i] = 0;
        }
    }

    // Find with Path Compression
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // compress path
        }
        return parent[x];
    }

    // Union by Rank
    void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU == rootV)
            return;

        if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } 
        else if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } 
        else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
    }

    boolean connected(int u, int v) {
        return find(u) == find(v);
    }
}

public class DisjointSet {

    public static void main(String[] args) {

        int n = 6;
        DSU dsu = new DSU(n);

        // Given connections
        dsu.union(0, 1);
        dsu.union(1, 2);
        dsu.union(3, 4);
        dsu.union(4, 5);

        // Queries
        System.out.println(dsu.connected(0, 2) ? "YES" : "NO");
        System.out.println(dsu.connected(0, 5) ? "YES" : "NO");
        System.out.println(dsu.connected(3, 5) ? "YES" : "NO");
    }
}
