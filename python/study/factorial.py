"""
    Factorial
"""


def basic_factorial(num):
    result = 1

    if num <= 1:
        return result

    # 1 ~ num
    for index in range(1, num + 1):
        result *= index

    return result


print("basic_factorial : ", basic_factorial(-2))
print("basic_factorial : ", basic_factorial(0))
print("basic_factorial : ", basic_factorial(1))
print("basic_factorial : ", basic_factorial(2))
print("basic_factorial : ", basic_factorial(3))
print("basic_factorial : ", basic_factorial(4))
print("basic_factorial : ", basic_factorial(5))
print("basic_factorial : ", basic_factorial(10))


def basic_factorial_using_recursion(num):
    result = 1

    if num <= 1:
        return result

    result = num * basic_factorial_using_recursion(num - 1)
    return result


print("basic_factorial_using_recursion : ", basic_factorial_using_recursion(-2))
print("basic_factorial_using_recursion : ", basic_factorial_using_recursion(0))
print("basic_factorial_using_recursion : ", basic_factorial_using_recursion(1))
print("basic_factorial_using_recursion : ", basic_factorial_using_recursion(2))
print("basic_factorial_using_recursion : ", basic_factorial_using_recursion(3))
print("basic_factorial_using_recursion : ", basic_factorial_using_recursion(4))
print("basic_factorial_using_recursion : ", basic_factorial_using_recursion(5))
print("basic_factorial_using_recursion : ", basic_factorial_using_recursion(10))

"""
    Calc Sum of numbers using recursion
"""


def calc_sum_number_using_recursion(num):
    result = 0

    if num < 1:
        return result

    result = num + calc_sum_number_using_recursion(num - 1)

    return result


print("=== calc_sum_number_using_recursion ===", calc_sum_number_using_recursion(10))
print("=== calc_sum_number_using_recursion ===", calc_sum_number_using_recursion(100))

"""
    find max/min num
"""

# Dummy Data
dummy_num_list = [90, 30, 12, 100, 34, 98]


def calc_max_num(num_list):

    list_len = len(num_list)
    max_num = num_list[0]
    if list_len <= 1:
        return max_num

    recursion_result = calc_max_num(num_list[1:])
    if max_num < recursion_result:
        max_num = recursion_result

    return max_num


print('=== calc_max_num === : ', calc_max_num(dummy_num_list))


def calc_min_num(num_list):

    list_len = len(num_list)
    min_num = num_list[0]
    if list_len <= 1:
        return min_num

    recursion_result = calc_min_num(num_list[1:])
    if min_num > recursion_result:
        min_num = recursion_result

    return min_num


print('=== calc_min_num === : ', calc_min_num(dummy_num_list))
