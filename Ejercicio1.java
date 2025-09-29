import java.util.*;

public class Ejercicio1 {
    static final int V = 10;
    static final int INF = 9999;
    static int[][] grafo = new int[V][V];

    public static void main(String[] args) {
        generarGrafoAleatorio(15);
        System.out.println("Matriz de adyacencia:");
        imprimirMatriz();

        int costoPrim = primMST();
        int costoKruskal = kruskalMST();
        System.out.println("Costo MST con Prim: " + costoPrim);
        System.out.println("Costo MST con Kruskal: " + costoKruskal);

        if (costoPrim == costoKruskal) {
            System.out.println(" Ambos algoritmos coinciden.");
        } else {
            System.out.println(" Los algoritmos difieren.");
        }
    }

    static void generarGrafoAleatorio(int minAristas) {
        Random rand = new Random();
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                grafo[i][j] = (i == j) ? 0 : INF;

        int aristas = 0;
        while (aristas < minAristas) {
            int u = rand.nextInt(V);
            int v = rand.nextInt(V);
            int peso = rand.nextInt(20) + 1;
            if (u != v && grafo[u][v] == INF) {
                grafo[u][v] = peso;
                grafo[v][u] = peso;
                aristas++;
            }
        }
    }

    static void imprimirMatriz() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (grafo[i][j] == INF) System.out.print("∞\t");
                else System.out.print(grafo[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static int primMST() {
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];
        Arrays.fill(key, INF);
        key[0] = 0;
        int costoTotal = 0;

        for (int count = 0; count < V; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            costoTotal += key[u];
            for (int v = 0; v < V; v++) {
                if (grafo[u][v] != INF && !mstSet[v] && grafo[u][v] < key[v])
                    key[v] = grafo[u][v];
            }
        }
        return costoTotal;
    }

    static int minKey(int[] key, boolean[] mstSet) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
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
        int costoTotal = 0, aristasUsadas = 0;
        for (Edge e : edges) {
            if (uf.find(e.u) != uf.find(e.v)) {
                uf.union(e.u, e.v);
                costoTotal += e.peso;
                aristasUsadas++;
                if (aristasUsadas == V - 1) break;
            }
        }
        return costoTotal;
    }

    static class Edge implements Comparable<Edge> {
        int u, v, peso;
        Edge(int u, int v, int peso) { this.u = u; this.v = v; this.peso = peso; }
        public int compareTo(Edge other) { return this.peso - other.peso; }
    }

    static class UnionFind {
        int[] parent, rank;
        UnionFind(int n) {
            parent = new int[n]; rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) { return parent[x] == x ? x : (parent[x] = find(parent[x])); }
        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx != ry) {
                if (rank[rx] < rank[ry]) parent[rx] = ry;
                else if (rank[rx] > rank[ry]) parent[ry] = rx;
                else { parent[ry] = rx; rank[rx]++; }
            }
        }
    }
}
