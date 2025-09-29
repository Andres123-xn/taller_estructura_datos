import java.util.*;

public class Ejercicio6 {
    static final int V = 4;
    static final int INF = 9999;
    static int[][] grafo = {
        {0, 5, INF, 10},
        {INF, 0, 3, INF},
        {INF, INF, 0, 1},
        {INF, INF, INF, 0}
    };

    public static void main(String[] args) {
        int[][] dist = new int[V][V];
        int[][] next = new int[V][V];

        for (int i=0;i<V;i++) {
            for (int j=0;j<V;j++) {
                dist[i][j]=grafo[i][j];
                if (grafo[i][j]!=INF && i!=j) next[i][j]=j;
                else next[i][j]=-1;
            }
        }

        for (int k=0;k<V;k++) {
            for (int i=0;i<V;i++) {
                for (int j=0;j<V;j++) {
                    if (dist[i][k]+dist[k][j]<dist[i][j]) {
                        dist[i][j]=dist[i][k]+dist[k][j];
                        next[i][j]=next[i][k];
                    }
                }
            }
        }

        for (int i=0;i<V;i++) {
            for (int j=0;j<V;j++) {
                if (i!=j) {
                    if (next[i][j]==-1) System.out.println("No hay camino de " + i + " a " + j);
                    else {
                        System.out.print("Distancia " + i + "→" + j + " = " + dist[i][j] + ", Camino: ");
                        imprimirCamino(i,j,next);
                        System.out.println();
                    }
                }
            }
        }
    }

    static void imprimirCamino(int u,int v,int[][] next){
        if (next[u][v]==-1) return;
        System.out.print(u);
        while (u!=v){
            u=next[u][v];
            System.out.print(" → " + u);
        }
    }
}
