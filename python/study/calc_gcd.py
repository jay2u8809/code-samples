"""
    Calc GCD(Greatest Common Divisor)
"""


def calc_gcd_num(num1, num2):
    result = min(num1, num2)

    while result > 0:
        if num1 % result == 0 and num2 % result == 0:
            return result
        result -= 1

    return result


print("calc_gcd_num : ", calc_gcd_num(2, 3))
print("calc_gcd_num : ", calc_gcd_num(1, 6))
print("calc_gcd_num : ", calc_gcd_num(4, 26))
print("calc_gcd_num : ", calc_gcd_num(27, 81))


"""
    Euclid Method
    ex) gcd(num1, num2) == gcd(num2, num1 % num2), gcd(num1, 0) = num1
"""


def euclid_gcd(num1, num2):

    if num2 < 1:
        return num1

    result = euclid_gcd(num2, num1 % num2)

    return result


print("euclid_gcd : ", euclid_gcd(2, 3))
print("euclid_gcd : ", euclid_gcd(1, 6))
print("euclid_gcd : ", euclid_gcd(4, 26))
print("euclid_gcd : ", euclid_gcd(27, 81))