d1 = {
    True: "Yes, This is True!!",
    False: "Nope",
    39: "Miku",
    39.39: "Hatsune",
    "Diva": "Song Hana",
    "List": [1, 2, 3]
}

# True and False
print(d1[True])
print(d1[False])
print(d1[1 > 0])
print(d1[-1 > 0])

# Number
miku = 39
print(d1[39])
print(d1[39.39])
print(d1[miku])

# String
print(d1["Diva"])
print(d1["List"])

# dict메소드를 사용할떄는 key는 문자열만 가능
d2 = dict(on = 999, off = 100, l = [1, 2, 3], s = "miku")
print(d2)
print(d2['on'])
print(d2['l'])
