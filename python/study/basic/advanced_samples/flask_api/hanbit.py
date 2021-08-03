import sqlite3
from flask import Flask
import json
from flask import request


def get_db_con() -> sqlite3.connect:
    return sqlite3.connect('../slqlite/examples/python_db_sample.sqlite')


app = Flask(__name__)

app.debug = True


@app.route('/')
def hello():
    with get_db_con() as conn:
        cur = conn.cursor()

        q = 'select * from hanbit_books'
        result = cur.execute(q)

    return jsonize(result)


@app.route('/books/by/author/')
def get_books_by_author():
    name = request.args.get('name')

    with get_db_con() as conn:
        # Encoding : 유니코드 인코딩 문제가 발생하면 주석을 푼다
        conn.text_factory = str
        cur = conn.cursor()

        q = 'SELECT * FROM hanbit_books where author LIKE :name ORDER BY title'
        params = {
            'name': '%' + name + '%'
        }
        result = cur.execute(q, params)

        return jsonize(result)

# @app.route('books/by/month/')
# def get_books_by_month():
#     pass


def jsonize(result):
    return json.dumps(list(result.fetchall()), ensure_ascii=False).encode('utf-8')


if __name__ == '__main__':
    app.run()


