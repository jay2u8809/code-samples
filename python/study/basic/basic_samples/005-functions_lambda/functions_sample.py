def hello(world):
    print("Hello", world)

to = "Miku"
hello(to)

def hello_ret(world):
    # if type(world) is not str:
    #     world = str(world)
    # ret_value = "Hello, " + world
    ret_value = "Hello, " + str(world)
    return ret_value

print(hello_ret("D.va"))
print(hello_ret(233))

def func(number):
    def func_in_func(number):
        print(number)

    print("In func")
    func_in_func(number + 1)

func(1)

# Type Hinting
# @param str
# @parma int
# @return  int
def count_length(word: str, num: int) -> int:
    return len(word) * num

print(count_length("miku", 39))
print(type(count_length("miku", 39)))

def add_with_transform(left, right, transform_func):
    tmp_val = transform_func(left) + transform_func(right)
    return transform_func(tmp_val)

def add_plus_1(number):
    return number + 1

# (2 + 1) + (3 + 1) + 1 = 8
ret_val = add_with_transform(2, 3, add_plus_1)

print(ret_val)
