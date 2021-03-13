"""
    Sequential Search
"""

dummy_list = [12, 43, 546, 65, -43, 43, -78, 65, 100]


def sequential_search_first_index(num_list, search_num):
    list_len = len(num_list)
    result = -1

    if list_len < 1:
        return result

    for idx in range(0, list_len):

        if num_list[idx] == search_num:
            result = idx
            return result

    return result


print('sequential_search_first_index', sequential_search_first_index(dummy_list, 12))
print('sequential_search_first_index', sequential_search_first_index(dummy_list, -78))
print('sequential_search_first_index', sequential_search_first_index(dummy_list, 43))
print('sequential_search_first_index', sequential_search_first_index(dummy_list, 65))
print('sequential_search_first_index', sequential_search_first_index(dummy_list, 101))


def sequential_search_all_index(num_list, search_num):
    list_len = len(num_list)
    result = []

    if list_len < 1:
        return result

    for idx in range(0, list_len):

        if num_list[idx] == search_num:
            result.append(idx)

    return result


print('sequential_search_all_index', sequential_search_all_index(dummy_list, 12))
print('sequential_search_all_index', sequential_search_all_index(dummy_list, -78))
print('sequential_search_all_index', sequential_search_all_index(dummy_list, 43))
print('sequential_search_all_index', sequential_search_all_index(dummy_list, 65))
print('sequential_search_all_index', sequential_search_all_index(dummy_list, 101))


"""
    Find car name by car number     
"""
car_num_list = [5993, 2032, 1020, 4300, 3201]
car_name_list = ['Ferrari', 'Benz', 'BMW', 'RangeRover', 'Audi']


def search_car_name(car_num):

    result = '?'

    if car_num is None:
        return result

    car_num_list_len = len(car_num_list)
    for idx in range(0, car_num_list_len):

        if car_num_list[idx] == car_num:
            result = car_name_list[idx]
            return result

    return result


print('search_car_name', search_car_name(1020))
print('search_car_name', search_car_name(3201))
print('search_car_name', search_car_name(2032))
print('search_car_name', search_car_name(1111))
