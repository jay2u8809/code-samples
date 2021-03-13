# Calc max num and min num of list

"""
    Calc max num and min num of list
    :param num_list:
"""

# Dummy Data
dummy_num_list = [90, 30, 12, 100, 34, 98]


def calc_max_num(num_list):
    max_num = num_list[0]  # initialize
    max_idx = 0
    for idx, num in enumerate(num_list):

        if num > max_num:
            max_num = num
            max_idx = idx

    return max_idx, max_num


print('calc_max_num : ', calc_max_num(dummy_num_list))


def calc_max_num_using_len(num_list):
    list_len = len(num_list)
    max_num = num_list[0]
    max_idx = 0

    for idx in range(0, list_len):

        if num_list[idx] > max_num:
            max_num = num_list[idx]
            max_idx = idx

        idx += 1

    return max_idx, max_num


print('calc_max_num_using_len : ', calc_max_num_using_len(dummy_num_list))


def calc_min_num(num_list):
    min_num = num_list[0]  # initialize
    min_idx = 0
    for idx, num in enumerate(num_list):

        if num < min_num:
            min_num = num
            min_idx = idx

    return min_idx, min_num


print('calc_min_num : ', calc_min_num(dummy_num_list))


def calc_min_num_using_len(num_list):
    list_len = len(num_list)
    min_num = num_list[0]
    min_idx = 0

    for idx in range(0, list_len):

        if num_list[idx] < min_num:
            min_num = num_list[idx]
            min_idx = idx

        idx += 1

    return min_idx, min_num


print('calc_min_num_using_len : ', calc_min_num_using_len(dummy_num_list))