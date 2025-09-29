import java.util.*;

public class Ejercicio4 {
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
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese vértice origen (0-4): ");
        int origen = sc.nextInt();
        System.out.print("Ingrese vértice destino (0-4): ");
        int destino = sc.nextInt();

        int[] dist = dijkstra(origen);
        if (dist[destino] == INF) {
            System.out.println("No hay ruta posible entre los vértices.");
        } else {
            System.out.println("Distancia mínima: " + dist[destino]);
        }
    }

    static int[] dijkstra(int src) {
        int[] dist = new int[V];
        boolean[] vis = new boolean[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int count=0; count<V-1; count++) {
            int u = minDist(dist, vis);
            if (u==-1) break;
            vis[u]=true;
            for (int v=0; v<V; v++) {
                if (!vis[v] && grafo[u][v]!=INF && dist[u]+grafo[u][v]<dist[v]) {
                    dist[v] = dist[u] + grafo[u][v];
                }
            }
        }
        return dist;
    }

    static int minDist(int[] dist, boolean[] vis) {
        int min = INF, idx=-1;
        for (int i=0;i<V;i++) {
            if (!vis[i] && dist[i]<min) { min=dist[i]; idx=i; }
        }
        return idx;
    }
}
