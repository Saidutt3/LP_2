class Graph:
    def __init__(self, edges, n):
        self.adjList = [[] for _ in range(n)]
        for (src, dest) in edges:
            self.adjList[src].append(dest)
            self.adjList[dest].append(src)

    def DFS(self, v, discovered, result):
        discovered[v] = True
        result.append(v)

        for u in self.adjList[v]:
            if not discovered[u]:
                self.DFS(u, discovered, result)

if __name__ == '__main__':
    edges = [(1, 2), (1, 7), (1, 8), (2, 3), (2, 6), (3, 4), (3, 5), (8, 9), (8, 12), (9, 10), (9, 11)]
    n = 18 # Adjust the value of n to match the maximum vertex index in the edges
    graph = Graph(edges, n)
    discovered = [False] * n

    result = []
    start_vertex = 1
    graph.DFS(start_vertex, discovered, result)

    print(' '.join(map(str, result)))