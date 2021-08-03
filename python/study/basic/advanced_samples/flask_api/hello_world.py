from flask import Flask
from flask import request

# Execute Flask App
app = Flask(__name__)

# On Debugging Mode
app.debug = True


# Router Decorator
@app.route('/')
def hello():
    return 'Hello World!'


# Path Variable
@app.route('/hello/<name>')
def hello_to(name):
    return 'Hello World, {}!'.format(name)


# QueryString
@app.route('/hello/')
def hello_to_get_param():
    # QueryString의 name변수의 값을 가져온다.
    # 예) /hello/?name=변수
    name = request.args.get('name')
    return 'Hello, {}'.format(name)


# 이 파일을 실행할 때, 함께 실행할 코드
if __name__ == '__main__':
    app.run()
