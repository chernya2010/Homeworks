class A:
    def __init__(self, x):
        self.x = x

    def __add__(self, other):
        return A(self.x + other)


a = A(4)
b = a + 1

print(b.x)
