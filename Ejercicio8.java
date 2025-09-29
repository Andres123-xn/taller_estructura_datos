import java.util.*;

public class Ejercicio8 {
    static final int V = 5;
    static final int INF = -1;
    static int[][] ancho = {
        {0, 10, INF, 30, 100},
        {10, 0, 50, INF, INF},
        {INF, 50, 0, 20, 10},
        {30, INF, 20, 0, 60},
        {100, INF, 10, 60, 0}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Origen (0-4): ");
        int o=sc.nextInt();
        System.out.print("Destino (0-4): ");
        int d=sc.nextInt();

        int[] mejor = rutaMasRapida(o);
        System.out.println("Mayor ancho de banda disponible de " + o + " a " + d + " = " + mejor[d]);
    }

    static int[] rutaMasRapida(int src) {
        int[] maxAncho=new int[V];
        boolean[] vis=new boolean[V];
        Arrays.fill(maxAncho,-1);
        maxAncho[src]=Integer.MAX_VALUE;

        for (int count=0;count<V-1;count++){
            int u=maxIndex(maxAncho,vis);
            if (u==-1) break;
            vis[u]=true;
            for (int v=0;v<V;v++){
                if (ancho[u][v]!=INF && !vis[v]){
                    int posible=Math.min(maxAncho[u], ancho[u][v]);
                    if (posible>maxAncho[v]) maxAncho[v]=posible;
                }
            }
        }
        return maxAncho;
    }

    static int maxIndex(int[] maxAncho, boolean[] vis){
        int max=-1, idx=-1;
        for (int i=0;i<V;i++){
            if (!vis[i] && maxAncho[i]>max){ max=maxAncho[i]; idx=i; }
        }
        return idx;
    }
}
