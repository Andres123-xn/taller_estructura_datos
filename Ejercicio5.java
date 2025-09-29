import java.util.*;

public class Ejercicio5 {
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
        System.out.print("Ingrese origen (0-4): ");
        int origen = sc.nextInt();
        System.out.print("Ingrese destino (0-4): ");
        int destino = sc.nextInt();

        int[] dist = new int[V];
        int[] parent = new int[V];
        dijkstra(origen, dist, parent);

        if (dist[destino]==INF) {
            System.out.println("No hay ruta posible.");
        } else {
            System.out.print("Camino: ");
            imprimirCamino(destino, parent);
            System.out.println(" (costo " + dist[destino] + ")");
        }
    }

    static void dijkstra(int src, int[] dist, int[] parent) {
        boolean[] vis = new boolean[V];
        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);
        dist[src]=0;

        for (int count=0; count<V-1; count++) {
            int u = minDist(dist, vis);
            if (u==-1) break;
            vis[u]=true;
            for (int v=0; v<V; v++) {
                if (!vis[v] && grafo[u][v]!=INF && dist[u]+grafo[u][v]<dist[v]) {
                    dist[v]=dist[u]+grafo[u][v];
                    parent[v]=u;
                }
            }
        }
    }

    static int minDist(int[] dist, boolean[] vis) {
        int min=INF, idx=-1;
        for (int i=0;i<V;i++) if (!vis[i] && dist[i]<min) {min=dist[i]; idx=i;}
        return idx;
    }

    static void imprimirCamino(int v, int[] parent) {
        if (v==-1) return;
        imprimirCamino(parent[v], parent);
        System.out.print(v + " ");
    }
}
