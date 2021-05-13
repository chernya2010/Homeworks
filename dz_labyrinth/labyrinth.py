import random
from colorama import init, Fore
init()

height = 10
width = 10
labyrinth = []
for j in range(height):
    column = []
    for i in range(width):
        column.append(random.randint(0, 1))
    labyrinth.append(column)
# filling with data
for i in range(len(labyrinth[0]) - 1): # side walls
    labyrinth[0][i] = 8
    labyrinth[height - 1][i] = 8
for i in range(len(labyrinth)):        # upper and lower walls
    labyrinth[i][0] = 8
    labyrinth[i][width - 1] = 8

cols = len(labyrinth)
rows = 0

if cols:
    rows = len(labyrinth[0])
for j in range(rows):
    for i in range(cols):
        if labyrinth[i][j] == 1:
            print(Fore.WHITE, labyrinth[i][j], end=" ")
        elif labyrinth[i][j] == 0:
            print(Fore.GREEN, labyrinth[i][j], end=" ")
        else:
            print(Fore.RED, labyrinth[i][j], end=" ")
    print()

startCell = labyrinth[1][1]
if startCell == 1:
    startCell = 0
