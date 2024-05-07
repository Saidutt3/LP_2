count = 0

def issafe(chess, row, col):
    # Check column up
    for i in range(row, -1, -1):
        if chess[i][col] == 'Q':
            return False
    
    # Check left diagonal
    for i, j in zip(range(row, -1, -1), range(col, -1, -1)):
        if chess[i][j] == 'Q':
            return False
    
    # Check right diagonal
    for i, j in zip(range(row, -1, -1), range(col, len(chess))):
        if chess[i][j] == 'Q':
            return False
    
    return True

def insertq(chess, row):
    global count
    if row == len(chess):
        printchess(chess)
        return
    
    for j in range(len(chess)):
        if issafe(chess, row, j):
            chess[row][j] = 'Q'
            insertq(chess, row + 1)
            chess[row][j] = 'X'

def printchess(chess):
    global count
    print("--------------------------------------------chess board ----------------------------------------------------")
    for row in chess:
        print(" ".join(row))
    print()
    count += 1

def createchess(n):
    chess = [['X' for _ in range(n)] for _ in range(n)]
    insertq(chess, 0)

if __name__ == "__main__":
    n = 4
    createchess(n)
    print(count)
