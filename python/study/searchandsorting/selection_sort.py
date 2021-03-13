"""
    Selection sort
"""


# remove and create new list
dummy_list = [2, 4, 5, 1, 3]


def find_min_num_index(num_list):
    num_list_len = len(num_list)
    min_num_idx = 0

    for idx in range(1, num_list_len):
        if num_list[idx] < num_list[min_num_idx]:
            min_num_idx = idx

    return min_num_idx


def selection_sort(data_list):
    result = []

    while data_list:
        min_num_idx = find_min_num_index(data_list)
        value = data_list.pop(min_num_idx)
        result.append(value)

    return result


print('selection_sort : ', selection_sort(dummy_list))


# change list value
dummy_list2 = [2, 4, 5, 1, 3]


def selection_sort_change_index(data_list):
    data_list_len = len(data_list)

    for idx in range(0, data_list_len - 1):

        min_num_idx = idx
        for j in range(idx + 1, data_list_len):
            if data_list[j] < data_list[min_num_idx]:   # Search for smaller numbers to the end of the list
            # if data_list[j] > data_list[min_num_idx]:   # Sort Descending
                min_num_idx = j

        data_list[idx], data_list[min_num_idx] = data_list[min_num_idx], data_list[idx]

    return data_list


print('selection_sort_change_index : ', selection_sort_change_index(dummy_list2))
