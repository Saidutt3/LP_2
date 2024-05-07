import sys

class Graph:
    def __init__(self, vertices):
        self.V = vertices
        self.graph = [[0 for column in range(vertices)] for row in range(vertices)]

    def printSolution(self, dist):
        print("Vertex \tDistance from Source")
        for node in range(self.V):
            print(node, "\t", dist[node])

    def minDistance(self, dist, sptSet):
        min_dist = sys.maxsize
        min_index = -1

        for u in range(self.V):
            if dist[u] < min_dist and not sptSet[u]:
                min_dist = dist[u]
                min_index = u

        return min_index

    def dijkstra(self, src):
        dist = [sys.maxsize] * self.V
        dist[src] = 0
        sptSet = [False] * self.V

        for _ in range(self.V):
            u = self.minDistance(dist, sptSet)
            sptSet[u] = True

            for v in range(self.V):
                if not sptSet[v] and self.graph[u][v] > 0 and dist[v] > dist[u] + self.graph[u][v]:
                    dist[v] = dist[u] + self.graph[u][v]

        self.printSolution(dist)

if __name__ == "__main__":
    num_vertices = int(input("Enter the number of vertices: "))
    g = Graph(num_vertices)

    print("Enter the edges and their weights (format: u v w), enter -1 to stop:")
    while True:
        edge_input = input().split()
        if edge_input == ['-1']:
            break
        u, v, w = map(int, edge_input)
        g.graph[u][v] = w
        g.graph[v][u] = w

    src_vertex = int(input("Enter the source vertex: "))
    g.dijkstra(src_vertex)
7