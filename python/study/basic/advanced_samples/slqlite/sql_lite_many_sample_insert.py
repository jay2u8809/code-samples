import sqlite3
import csv

'''
    Insert Many Data
'''
# Open Csv File
csv_file = open('../crawling_app/book_list.csv')
# Read Csv File
csv_reader = csv.reader(csv_file)
# Convert to list type
book_list = list(csv_reader)
# Remove Header Part
book_list = book_list[1:]
insert_book_list = []
# Remove whitespace
for book in book_list:
    if len(book) == 0:
        continue
    book[1] = book[1].strip()
    book[2] = book[2].strip()
    insert_book_list.append(book)

# print(book_list)
# print(len(book_list))
# print(len(insert_book_list))

# Connect DB
conn = sqlite3.connect('./examples/python_db_sample.sqlite')
cur = conn.cursor()

table_name = ' hanbit_books '
insert_basic_sql = 'insert into' + table_name + 'values (?, ?, ?, ?, ?)'

# Insert Many Data
cur.executemany(insert_basic_sql, insert_book_list)
conn.commit()