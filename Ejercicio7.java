import java.util.*;

public class Ejercicio7 {
    static final int V = 4;
    static int[][] grafo = {
        {0,1,0,0},
        {0,0,1,0},
        {1,0,0,1},
        {0,0,0,0}
    };

    public static void main(String[] args) {
        int[][] alcanz = new int[V][V];
        for (int i=0;i<V;i++) for (int j=0;j<V;j++) alcanz[i][j]=grafo[i][j];

        for (int k=0;k<V;k++)
            for (int i=0;i<V;i++)
                for (int j=0;j<V;j++)
                    alcanz[i][j] = (alcanz[i][j]==1 || (alcanz[i][k]==1 && alcanz[k][j]==1)) ? 1:0;

        System.out.println("Matriz de alcanzabilidad:");
        for (int i=0;i<V;i++) {
            for (int j=0;j<V;j++) System.out.print(alcanz[i][j]+" ");
            System.out.println();
        }

        // componentes fuertemente conexos
        boolean[] visitado=new boolean[V];
        for (int i=0;i<V;i++){
            if (!visitado[i]){
                List<Integer> comp=new ArrayList<>();
                for (int j=0;j<V;j++){
                    if (alcanz[i][j]==1 && alcanz[j][i]==1){
                        comp.add(j);
                        visitado[j]=true;
                    }
                }
                System.out.println("Componente: " + comp);
            }
        }
    }
}
