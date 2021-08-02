import sqlite3

'''
    Drop DB Table
'''

conn = sqlite3.connect('./examples/python_db_sample.sqlite')
cur = conn.cursor()
table_name = ' hanbit_books '

# Drop Table
cur.execute('drop table' + table_name)

# Disconnect DB
conn.close()
