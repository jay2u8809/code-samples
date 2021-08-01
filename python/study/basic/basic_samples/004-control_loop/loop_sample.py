# While
i = 0
while i < 10:
    print("i is : ", i)
    i += 1

# For
for i in range(10):
    print(i)

names = ["Miku", "Rin", "Ren", "Luka", "Seeu", "D.va"]
for name in names:
    print("You are", name)

# list
lotto_number = [1, 3, 9, 39, 40, 41]
for number in lotto_number:
    print('You Write number', number, "on paper")

# dict
Diva_info = {
    "Name": "Miku",
    "version": 3,
    "company": "Overwatch",
    "like_number": 39
}

for title in Diva_info:
    print(title, ":", Diva_info[title])

for value in Diva_info.values():    # ******************************************************************
    print(value)

# Range()
for i in range(1, 20, 3):
    print(i)

# Break
numbers = [9, 1, 2, 7, 0, 4, 10, 2, 39, 10, 33, 36, 38]
for number in numbers:
    if number == 39:
        print("I Found it! 39!")
        break
    else:
        print("I found", number, "But this is not I want")

# Continue
l = ["1", 2, "3", "4", 5]
for item in l:
    if type(item) is str:   # type(item) == str
        continue

    print("number", item)
    print("Multiply by 2:", item * 2)

# else ******************************************************************
for number in numbers:
    if number == 39:
        print("I Found it! 39!")
        break
    else:
        print("I found", number, "But this is not I want")
# for문에서 break문이 실행되지 않으면 바로 실행
else:
    print("Not Found 39....")

# pass ******************************************************************
# 아무것도 실행하지 않지만 무언가 있어야 할 자리에 넣는 키워드
for i in range(10):
    pass