"""
    Insertion Sort
"""


def find_insert_index(data_list, data):
    list_len = len(data_list)

    if list_len == 0:
        return list_len

    for idx in range(0, list_len):
        if data < data_list[idx]:
            return idx

    return list_len


def insertion_sort(data_list):
    result = []

    while data_list:
        value = data_list.pop(0)
        insert_idx = find_insert_index(result, value)
        result.insert(insert_idx, value)

    return result


dummy_list = [3, 4, 1, 5, 2]
print("insertion_sort", insertion_sort(dummy_list))


def insertion_sort_2(data_list):
    list_len = len(data_list)

    for idx in range(1, list_len):
        key = data_list[idx]
        j = idx - 1

        while j >= 0 and data_list[j] > key:
            data_list[j + 1] = data_list[j]
            j -= 1

        data_list[j + 1] = key


dummy_list2 = [3, 4, 1, 5, 2]
insertion_sort_2(dummy_list2)
print("insertion_sort_2", dummy_list2)