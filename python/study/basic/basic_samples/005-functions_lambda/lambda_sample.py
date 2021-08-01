
def add_1(number):
    return number + 1

add_plus_one = add_1
print(add_1(5))
print(add_plus_one(6))

add_plus_one = lambda x: x + 1
print(add_plus_one(7))