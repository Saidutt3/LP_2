import java.util.Scanner;

public class KruskalsAlgorithm2 {

    static class Edge {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Graph {
        int V, E;
        Edge[] edges;

        public Graph(int v, int e) {
            V = v;
            E = e;
            edges = new Edge[E];
            for (int i = 0; i < e; i++) {
                edges[i] = new Edge(0, 0, 0);
            }
        }
    }

    static int find(int[] parent, int i) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }

    static void union(int[] parent, int[] rank, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    static void kruskalMST(Graph graph) {
        int V = graph.V;
        Edge[] result = new Edge[V];
        int e = 0;
        int i = 0;

        // Step 1: Sort all the edges in non-decreasing order of their weight
        quickSort(graph.edges, 0, graph.E - 1);

        // Allocate memory for creating V ssubsets
        int[] parent = new int[V];
        int[] rank = new int[V];

        for (int v = 0; v < V; ++v) {
            parent[v] = v;
            rank[v] = 0;
        }

        // Number of edges to be taken is equal to V-1
        while (e < V - 1) {
            Edge next_edge = graph.edges[i++];
            int x = find(parent, next_edge.src);
            int y = find(parent, next_edge.dest);

            // If including this edge does't cause cycle, include it in result and increment
            // the index
            // of result for next edge
            if (x != y) {
                result[e++] = next_edge;
                union(parent, rank, x, y);
            }
            // Else discard the next_edge
        }

        // Print the contents of result[] to display the built MST
        System.out.println("Following are the edges in the constructed MST:");
        int minimumCost = 0;
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree " + minimumCost);
    }

    static void quickSort(Edge[] edges, int low, int high) {
        if (low < high) {
            int pi = partition(edges, low, high);
            quickSort(edges, low, pi - 1);
            quickSort(edges, pi + 1, high);
        }
    }

    static int partition(Edge[] edges, int low, int high) {
        Edge pivot = edges[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (edges[j].weight <= pivot.weight) {
                i++;
                Edge temp = edges[i];
                edges[i] = edges[j];
                edges[j] = temp;
            }
        }
        Edge temp = edges[i + 1];
        edges[i + 1] = edges[high];
        edges[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        Graph graph = new Graph(V, E);

        System.out.println("Enter the edges (src, dest, weight):");
        for (int i = 0; i < E; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.edges[i].src = src;
            graph.edges[i].dest = dest;
            graph.edges[i].weight = weight;
        }

        kruskalMST(graph);

        scanner.close();
    }
}
