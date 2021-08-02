import sqlite3

'''
    Update
'''

conn = sqlite3.connect('./examples/python_db_sample.sqlite')
cur = conn.cursor()

table_name = ' hanbit_books '
update_basic_sql = 'update ' + table_name + 'set isbn = :isbn where author = :name'
params = {
    "name": "박태웅",
    "isbn": "9791157845287"
}

# isbn 9791157845286 -> 9791157845287
cur.execute(update_basic_sql, params)
conn.commit()

''' Select  '''
select_basic_sql = 'select * from' + table_name + 'where author = :name'
cur.execute(select_basic_sql, params)

print(cur.fetchone())

