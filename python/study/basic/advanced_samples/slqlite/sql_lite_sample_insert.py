import sqlite3

'''
    Insert
'''

conn = sqlite3.connect('./examples/python_db_sample.sqlite')
cur = conn.cursor()

'''
    # Example
    insert_basic_sql = 'insert into hanbit_books values (?, ?, ?, ?, ?)'
    insert_content_sql = ("책 이름2", "저자 이름2", "번역자 이름2", "2016-08-22", "9788968480012")
    
    cur.execute(insert_basic_sql, insert_content_sql)
    conn.commit()
'''

# insert_basic_sql = 'insert into hanbit_books values (:title, :authorName, :translatorName, :pubDate, :isbnNo)'
table_name = ' hanbit_books '
insert_basic_sql = 'insert into' + table_name + 'values (:title, :authorName, :translatorName, :pubDate, :isbnNo)'
query_params = {
    "title": "Amazon Web Services로 시작하는 클라우드 입문",
    "authorName": "아사 시호",
    "translatorName": "양성건",
    "pubDate": "2018-04-95",
    "isbnNo": "978-89-314-5707-0"
}
cur.execute(insert_basic_sql, query_params)
conn.commit()


