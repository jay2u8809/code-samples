if True:
    print('Yes')

if False:
    print('No')


# True Examples
number_true = 39
string_true = "miku"
list_true = [3, "M", 3.4]
tuple_true = (3, "M", 3.4)
dict_true = {"name": "Song Hana", "age": 12}

# False Examples
number_false = 0
string_false = ""
list_false = []
tuple_false = ()
dict_false = {}

print(bool(number_true))


# elif
number = 20

if number <= 10:
    print('First if Block')
elif 10 < number < 39:
    print('Second elif Block')
else:
    print('Oh..')


# and, or, not
name = "miku"
number = 39

if name == "miku" and number == 39:
    print("You are digital diva miku")
else:
    print("D.va?")

nick = "D.va"
name = "Song Hana"

if nick == "D.va" or nick == "Diva":
    print("You must be", nick, "!!")

if nick == "Diva" or  name == "Song Hana":
    print("Welcome back to overwatch")

print(not True)
print(not False)
is_diva = False
if not is_diva:
    print("You ar diva!")


# in keyword
l = [1, 2, 3]
s = {4, 5, 6, 6}
d = {"one": 1, "two": 2, "three": 3}
t = (7, 8, 9)

print(1 in l)
print(6 in s)
print(7 in s)
# 딕셔너리의 키(key)를 식별하는 방법
print("one" in d)
# 딕셔너리의 값(value)을 식별하는 방법
print(4 in d.values())
print(9 in t)
print(10 in t)
