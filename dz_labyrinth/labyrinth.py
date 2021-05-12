import random
height = 10
width = 10
cinema = []
for j in range(height):
    column = []
    for i in range(width):
        column.append(random.randint(0, 1))
    cinema.append(column)
# filling with data
for i in range(len(cinema)):
    cinema[i][0] = 1  # first row
for i in range(len(cinema)):
    cinema[i][9] = 1  # last row


cols = len(cinema)
rows = 0

if cols:
    rows = len(cinema[0])
for j in range(rows):
    for i in range(cols):
        print(cinema[i][j], end=" ")
    print()
