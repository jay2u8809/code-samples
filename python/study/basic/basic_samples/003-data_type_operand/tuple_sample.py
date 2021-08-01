# 요소가 1개인 튜플
t = 1,
# 요소가 2개이 튜플
t_ = 1, 2
print(type(t))
print(type(t_))

# 콤마(,)로 구분한 객체의 나열이나 괄호() 를 이용하여 튜플임을 명확하게 한다
t1 = (1,)
t2 = (1, 2)
t3 = (5, 6, 7, 8, 9)

print(type(t1))
print(type(t2))
print(t3)
print(t3[0])
# [start:stop]
print(t3[3:])
print(t3[:2])
print(t3[-3:])

# 변경불가
t3[0] = 1