import java.util.*;

public class Ejercicio2 {
    static final int V = 6;
    static final int INF = 9999;
    static int[][] grafo = {
        {0, 2, INF, 6, INF, INF},
        {2, 0, 3, 8, 5, INF},
        {INF, 3, 0, INF, 7, INF},
        {6, 8, INF, 0, 9, INF},
        {INF, 5, 7, 9, 0, INF},
        {INF, INF, INF, INF, INF, 0} // vértice aislado
    };

    public static void main(String[] args) {
        if (!esConexo()) {
            System.out.println("El grafo no tiene árbol de expansión mínima.");
        } else {
            int costo = primMST();
            System.out.println("Costo MST con Prim: " + costo);
        }
    }

    static boolean esConexo() {
        boolean[] visitado = new boolean[V];
        dfs(0, visitado);
        for (boolean v : visitado) if (!v) return false;
        return true;
    }

    static void dfs(int u, boolean[] visitado) {
        visitado[u] = true;
        for (int v = 0; v < V; v++) {
            if (grafo[u][v] != INF && !visitado[v]) dfs(v, visitado);
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
}
