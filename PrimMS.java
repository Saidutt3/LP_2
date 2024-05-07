import java.util.Scanner;

public class PrimMS {
    private static final int INF = 9999999;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        int[][] G = new int[V][V];

        System.out.println("Enter the edges and their weights (format: u v w), enter -1 to stop:");
        while (true) {
            int u = scanner.nextInt();
            if (u == -1) break;
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            G[u][v] = w;
            G[v][u] = w;
        }

        primMST(G, V);
    }

    private static void primMST(int[][] graph, int V) {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = INF;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V ; count++) {
            int u = minKey(key, mstSet, V);

            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph, V);
    }

    private static int minKey(int[] key, boolean[] mstSet, int V) {
        int min = INF, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private static void printMST(int[] parent, int[][] graph, int V) {
        System.out.println("Edge : Weight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + " : " + graph[i][parent[i]]);
        }
    }
}
