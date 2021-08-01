class MyFirstClass:
    pass

class Diva:
    # Class Variables
    version = "v3"

    # Constructor
    def __init__(self, name = "Diva"):
        # Instance Variables
        self.name = name

    def song(self, title = "song"):
        print(self.name + " sing the " + title)
    def medley(self):
        self.song()
        self.song("second song")
        self.song("third song")

diva1 = Diva()
diva2 = Diva("Miku")
diva3 = Diva("Hana")

def print_diva_info(diva: Diva):
    print("=====")
    print("Name:", diva.name)
    print("Version:", diva.version)

print(print_diva_info(diva1))
print(print_diva_info(diva2))
print(print_diva_info(diva3))

# Change Class Variable
Diva.version = "v4"
print(print_diva_info(diva1))
print(print_diva_info(diva2))
print(print_diva_info(diva3))

# Class Method
# 클래스 메소드는 첫번째 파라미터로 self 를 필수로 한다
voice_diva = Diva("Hana")
print("==========")
voice_diva.song()
voice_diva.song("World is Mine")
voice_diva.medley()


# Extend
class Miku(Diva):
    def __init__(self, module = "class uniform"):
        self.module = module
        super().__init__("miku")

    def dance(self):
        print("Dancing")

hatsune_miku = Miku()
print(hatsune_miku.module)
print(hatsune_miku.version)
print(hatsune_miku.name)
hatsune_miku.dance()
hatsune_miku.song("Hello Worker")


# Duck Typing =============================================
class Cat:
    def sound(self):
        print("Nya~")

class Dog:
    def sound(self):
        print("Mung")

cat = Cat()
dog = Dog()

animals = [cat, dog]

for animal in animals:
    animal.sound()