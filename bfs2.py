from collections import deque

class Graph:
    def __init__(self, edges, n):
        self.adjList = [[] for _ in range(n)]
        for (src, dest) in edges:
            self.adjList[src].append(dest)
            self.adjList[dest].append(src)
    
    def print_graph(self):
        for i, neighbors in enumerate(self.adjList):
            print(f"Adjacency list of vertex {i}: {neighbors}")

def BFS(graph, start):
    visited = [False] * len(graph.adjList)
    queue = deque([start])
    visited[start] = True

    while queue:
        vertex = queue.popleft()
        print(vertex, end=' ')
        for neighbor in graph.adjList[vertex]:
            if not visited[neighbor]:
                visited[neighbor] = True
                queue.append(neighbor)

if __name__ == '__main__':
    edges = [
        (1, 2), (1, 3), (1, 4), (2, 5), (2, 6), (5, 9),
        (5, 10), (4, 7), (4, 8), (7, 11), (7, 12)
    ]
    num_vertices = 17

    graph = Graph(edges, num_vertices)
    print("Printing Adjacency List of Given Graph:")
    graph.print_graph()

    print("\nBFS Traversal starting from vertex 1:")
    BFS(graph, 1)  # Start BFS traversal from vertex 1
