INF = 9999999

# Take input for the number of vertices (V)
V = int(input("Enter the number of vertices: "))

# Initialize the graph matrix (G) with all zeros
G = [[0 for _ in range(V)] for _ in range(V)]

# Take input for the edges and their weights
print("Enter the edges and their weights (format: u v w), enter -1 to stop:")
while True:
    edge_input = input().split()
    if edge_input == ['-1']:
        break
    u, v, w = map(int, edge_input)
    G[u][v] = w
    G[v][u] = w

selected = [False] * V
no_edge = 0

selected[0] = True
print("Edge : Weight\n")
while no_edge < V - 1:
    minimum = INF
    x = 0
    y = 0
    for i in range(V):
        if selected[i]:
            for j in range(V):
                if not selected[j] and G[i][j]:
                    if minimum > G[i][j]:
                        minimum = G[i][j]
                        x = i
                        y = j
    print(str(x) + "-" + str(y) + ":" + str(G[x][y]))
    selected[y] = True
    no_edge += 1
