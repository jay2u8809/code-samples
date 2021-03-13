"""
    Calculate sum of number without using formula
    ex) 1 ~ 10, 3 ~ 45
    :param start_num:
    :param end_num:
"""
def calc_sum_total_number(start_num, end_num):
    result = 0

    end_num += 1
    for idx in range(start_num, end_num):
        result += idx

    return result


print('calc_sum_total_number : ', calc_sum_total_number(4, 15))


"""
    Calculate sum of number using formula
    ex) 1 ~ 10, 3 ~ 45
    formula : num  (num + 1) / 2
    :param start_num:
    :param end_num:
"""
def calc_sum_total_number_using_formula(start_num, end_num):
    start_num -= 1

    # Subtract the sum up to start_num from the sum up to end_num
    end_sum = end_num * (end_num + 1) // 2           # the sum up to end_num
    start_sum = start_num * (start_num + 1) // 2     # the sum up to start_num
    result = end_sum - start_sum

    return result


print('calc_sum_total_number_using_formula : ', calc_sum_total_number_using_formula(4, 15))


"""
    Calculate sum of squares without using formula
    ex) 11 ~ 1010, 33 ~ 4545
    :param start_num:
    :param end_num:
"""
def calc_sum_total_square_number(start_num, end_num):
    result = 0

    for idx in range(start_num, end_num + 1):
        result += idx ** 2

    return result


print('calc_sum_total_square_number : ', calc_sum_total_square_number(1, 4))


"""
    Calculate sum of number using formula
    ex) 1 ~ 10, 3 ~ 45
    formula : num  (num + 1)  (2  num + 1) / 6
    :param start_num:
    :param end_num:
"""
def calc_sum_total_square_number_using_formula(start_num, end_num):
    start_num -= 1

    end_sum = end_num * (end_num + 1) * (2 * end_num + 1) // 6
    start_sum = start_num * (start_num + 1) * (2 * start_num + 1) // 6

    result = end_sum - start_sum

    return result


print('calc_sum_total_square_number_using_formula : ', calc_sum_total_square_number_using_formula(1, 4))


"""
    Calculate the sum of the product of a number and index without using a formula
    ex) 3 ~ 5 : 3*1 + 4*2 + 5*3
    :param start_num:
    :param end_num:
"""
def calc_sum_number_multiple_index(start_num, end_num):
    result = 0

    index = 1
    for value in range(start_num, end_num + 1):
        result += value * index
        index += 1

    return result


print('calc_sum_number_multiple_index : ', calc_sum_number_multiple_index(3, 5))