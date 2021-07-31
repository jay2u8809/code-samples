# Single Quote, Double Quote
diva = "Miku"
another_diva = 'Song Hana'

print(type(diva))
print(type(another_diva))

# Escape 1
escape_s1 = "This is \"double quote\""
escape_s2 = 'This isn\'t'

print(escape_s1)
print(escape_s2)

# Escape 2
s1 = "Tab \tThis"
s2 = "New Line\nHello"

print(s1)
print(s2)

# Raw String Expression
raw_s1 = r'C:Programs\new program\"'
raw_s2 = r"\\t\n\b\s"
raw_s3 = r'\'"'
raw_s4 = r"\"'"

print(raw_s1)
print(raw_s2)
print(raw_s3)
print(raw_s4)

# Multi Line
multi_s = """This String is
Multi Line
String
"""

print(multi_s)

# \를 붙이면 라인이 변경되지 않음
multi_s2 = '''This String is
Multi Line \
String
'''

print(multi_s2)


# String Operand
diva = "Miku"
first_sound = "Hatsune"

print(3 * diva)
print(diva * 3)
print(first_sound + diva)

print(dir(diva))
print("Capitalize : ", diva.capitalize())
print("is 'first_sound' end with 'e'?", first_sound.endswith("e"))                      # True
print("is 'first_sound' end with 'a'?", first_sound.endswith("a"))                      # False
print("join strings with 'diva' str : ", diva.join(["kagamine", "len", "megurine"]))    # kagamineMikulenMikumegurine