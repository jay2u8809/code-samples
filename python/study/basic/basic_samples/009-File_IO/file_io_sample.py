# open('FILE_NAME', OPTION)
# OPTION
# r : read      기본값, 읽기 전용으로 파일 열기
# w : write     파일 쓰기, 파일이 존재한다면 해당 파일을 비운다
# x             배타적 생성. 파일이 존재한다면 open() 실행은 실패
# a             파일 쓰기, 파일이 존재한다면 파일의 끝에 내용을 붙인다
# b             바이너리 모드로 연다
# t             텍스트 모드로 연다
# +             읽기와 쓰기를 다 한다

f = open('examples/lorem.txt', 'r')

# 파일 전체 읽기
print(f.read())

# 파일 한 행 읽기
print(f.readline())

# 파일 전부를 읽고 한 행마다 리스트 아이템으로 가져오기
texts = f.readlines()

for line in f:
    print(line)

# 파일 닫기
f.close()

# with : 파일을 열고 닫는 과정을 자동으로 하고 그 과정에서 오류가 발생하면 알아서 처리
with open("examples/lorem.txt") as f:
    print(f.readline())