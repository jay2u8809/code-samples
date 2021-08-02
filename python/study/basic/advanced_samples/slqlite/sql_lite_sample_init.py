import sqlite3

'''
    connect() : 데이터베이스 연결
    cursor() : 데이터베이스 동작
'''

# 데이터베이스 연결: 데이터베이스 파일이 저장될 경로와 파일이름 설정
conn = sqlite3.connect('./examples/python_db_sample.sqlite')

# Encoding : 유니코드 인코딩 문제가 발생하면 주석을 푼다
# conn.text_factory = str

# 메모리에서 직접 데이터베이스를 이용하는 코드
# conn = sqlite3.connect(':memory:')

# 데이터베이스를 동작시키기 위한 Cursor 객체 생성
# 데이터베이스를 사용하기 위한 마지막 준비
cur = conn.cursor()


'''
    테이블 생성
'''
create_sql = """
    create table hanbit_books (
                title varchar(100),
                author varchar(100),
                translator varchar(100),
                pub_date date,
                isbn varchar(100)
                )
"""
cur.execute(create_sql)
conn.commit()

