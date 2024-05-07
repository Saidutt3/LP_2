import java.util.Scanner;

public class DijkstraAlgorithm {
    static final int INF = Integer.MAX_VALUE;

    static class Graph {
        int V;
        int[][] graph;

        public Graph(int vertices) {
            V = vertices;
            graph = new int[V][V];
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    graph[i][j] = 0;
                }
            }
        }

        void printSolution(int[] dist) {
            System.out.println("Vertex \tDistance from Source");
            for (int i = 0; i < V; i++) {
                System.out.println(i + "\t" + dist[i]);
            }
        }

        int minDistance(int[] dist, boolean[] sptSet) {
            int min = INF, minIndex = -1;
            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && dist[v] < min) {
                    min = dist[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }

        void dijkstra(int src) {
            int[] dist = new int[V];
            boolean[] sptSet = new boolean[V];

            for (int i = 0; i < V; i++) {
                dist[i] = INF;
                sptSet[i] = false;
            }

            dist[src] = 0;

            for (int count = 0; count < V - 1; count++) {
                int u = minDistance(dist, sptSet);
                sptSet[u] = true;

                for (int v = 0; v < V; v++) {
                    if (!sptSet[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }

            printSolution(dist);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();

        Graph g = new Graph(numVertices);

        System.out.println("Enter the edges and their weights (format: u v w), enter -1 to stop:");
        while (true) {
            int u = scanner.nextInt();
            if (u == -1) break;
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            g.graph[u][v] = w;
            g.graph[v][u] = w;
        }

        System.out.print("Enter the source vertex: ");
        int srcVertex = scanner.nextInt();

        g.dijkstra(srcVertex);
    }
}
