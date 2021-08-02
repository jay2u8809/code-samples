import sqlite3

'''
    Select
'''

conn = sqlite3.connect('./examples/python_db_sample.sqlite')
cur = conn.cursor()

table_name = ' hanbit_books '
select_basic_sql = 'select * from' + table_name + 'where author = ?'
cur.execute(select_basic_sql, ('박태웅',))

print(cur.fetchone())

select_basic_sql2 = 'select * from' + table_name + 'where author = :name'
params = {
    "name": "윤인성"
}
# 최대 n개 까지 검색 : execute()메소드를 이용해 검색을 각각 실행 후 fetch()메소드를 실행해 결과를 가져온다.
cur.execute(select_basic_sql2, params)
result = list(cur.fetchmany(size=4))
print(result)

# 전체 다 검색
cur.execute(select_basic_sql2, params)
result1 = list(cur.fetchall())
for item in result1:
    print(item[0])