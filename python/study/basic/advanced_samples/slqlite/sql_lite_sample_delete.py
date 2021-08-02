import sqlite3

'''
    Delete
'''

conn = sqlite3.connect('./examples/python_db_sample.sqlite')
cur = conn.cursor()

table_name = ' hanbit_books '
delete_basic_sql = 'delete from ' + table_name + 'where author = :name'
params = {
    "name": "김신애",
    "isbn": "9791157845287"
}
cur.execute(delete_basic_sql, params)
conn.commit()

''' Select  '''
select_basic_sql = 'select * from' + table_name + 'where author = :name'
cur.execute(select_basic_sql, params)

print(cur.fetchone())
