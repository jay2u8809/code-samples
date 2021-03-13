"""
    Find same name in list
"""

# Bran, Suzuki
dummy_name_list = ['Ian', 'Bran', 'Nick', 'Suzuki', 'Lee', 'Bran', 'Suzuki']


def find_same_name(param_list):
    list_len = len(param_list)
    result = set()

    for i in range(0, list_len-1):          # index : 0 ~ list_len -2

        for j in range(i+1, list_len):      # index : i ~ list_len -1

            if param_list[j] == param_list[i]:
                result.add(param_list[i])

    return result


print('find_same_name : ', find_same_name(dummy_name_list))


def make_deduplicated_list(param_list):
    list_len = len(param_list)
    result = set()

    for i in range(0, list_len-1):          # index : 0 ~ list_len -2

        for j in range(i+1, list_len):      # index : i ~ list_len -1

            if param_list[j] != param_list[i]:
                result.add(param_list[i])

    return list(param_list)                 # set -> list


print('make_deduplicated_list : ', make_deduplicated_list(dummy_name_list))