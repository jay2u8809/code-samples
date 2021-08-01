l1 = [1, 2, 3]
l2 = [4, 5, 6]

print('l1 + l2 : ', l1 + l2)
print('l1 * 3 : ', l1 * 3)

l1 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]

print('l1 : ', l1)

# list[start:stop:step]
# list[start:]
# list[:stop]
# list[::step]
# list[start:stop]
# list[start::step]
# list[:stop:step]

# 9번째 인덱스 이후만 가져오기
print('l1[9:] = ', l1[9:])

# 15번째 인덱스 이전만 가져오기
print('l1[:15] = ', l1[:15])

# 2번째마다의 아이템 가져오기
print('l1[::2] = ', l1[::2])

# 7번째마다의 아이템 가져오기
print('l1[::7] = ', l1[::7])

# 5번째부터 시작해서 2번째마다 아이템을 가져오기
print('l1[5::2] = ', l1[5::2])

# 17번째 이전까지 매 4번째마다 아이템을 가져오기
print('l1[:17:4] = ', l1[:17:4])

# 7번째부터 시작해서 3번째마다 아이템을 가져오고, 15번째를 전달하지 않기
print('l1[7:15:3] = ', l1[7:15:3])


# 문자열
s1 = "Hatsune Miku"

print("s1 = ", s1)

print("s1[2] = ", s1[2])
print("s1[8:] = ", s1[8:])
print("s1[-4:] = ", s1[-4:])
print("s1[:7] = ", s1[:7])
print("s1[::2] = ", s1[::2])