import java.util.*;

public class Ejercicio3 {
    static final int V = 5;
    static final int INF = 9999;
    static int[][] grafo = {
        {0, 2, INF, 6, INF},
        {2, 0, 3, 8, 5},
        {INF, 3, 0, INF, 7},
        {6, 8, INF, 0, 9},
        {INF, 5, 7, 9, 0}
    };

    public static void main(String[] args) {
        int costo = kruskalMST();
        System.out.println("Costo MST con Kruskal: " + costo);
    }

    static int kruskalMST() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (grafo[i][j] != INF) edges.add(new Edge(i, j, grafo[i][j]));
            }
        }
        Collections.sort(edges);

        UnionFind uf = new UnionFind(V);
        int costoTotal = 0, aristasUsadas = 0, ciclosEvitados = 0;

        for (Edge e : edges) {
            if (uf.find(e.u) != uf.find(e.v)) {
                uf.union(e.u, e.v);
                costoTotal += e.peso;
                aristasUsadas++;
                if (aristasUsadas == V - 1) break;
            } else {
                ciclosEvitados++;
            }
        }

        System.out.println("Ciclos evitados: " + ciclosEvitados);
        return costoTotal;
    }

    static class Edge implements Comparable<Edge> {
        int u, v, peso;
        Edge(int u, int v, int peso) { this.u = u; this.v = v; this.peso = peso; }
        public int compareTo(Edge other) { return this.peso - other.peso; }
    }

    static class UnionFind {
        int[] parent, rank;
        UnionFind(int n) { parent = new int[n]; rank = new int[n]; for (int i=0;i<n;i++) parent[i]=i; }
        int find(int x) { return parent[x]==x?x:(parent[x]=find(parent[x])); }
        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx!=ry) {
                if (rank[rx]<rank[ry]) parent[rx]=ry;
                else if (rank[rx]>rank[ry]) parent[ry]=rx;
                else { parent[ry]=rx; rank[rx]++; }
            }
        }
    }
}
