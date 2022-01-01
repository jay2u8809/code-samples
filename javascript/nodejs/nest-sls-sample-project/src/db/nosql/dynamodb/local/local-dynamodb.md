## 0. 필수 설치 프로그램
- `Docker Desktop`
- `dynamodb-admin`: devDependencies
  + `$ npm i dynamodb-admin -D`
- `aws-cli`
  + `$ aws configure`
  + credential 및 profile 설정
    - `aws_access_key_id = DUMMYIDEXAMPLE`
    - `aws_secret_access_key = DUMMYEXAMPLEKEY`
    - `region = eu-west-1`
    - `output = json`
- `aws-sdk`

## 1. Docker Desktop 실행

## 2. Run Docker Compose
- `docker-compose.yml` 파일이 있는 폴더로 이동하여 아래의 명령어를 실행한다.
```shell
    $ docker-compose up -d
```

## 3. 초기 데이터 및 테이블 정보 입력
### 3-1. Shell 파일을 이용하는 방법
- `local-dynamodb-init.sh` 파일이 있는 디렉터리로 이동하여 아래의 명령어를 실행
```shell
    $ ./local-dynamodb-init.sh
```
- 특정 테이블만 사용하고 싶다면 파라미터에 테이블명을 입력
```shell
    $ ./local-dynamodb-init.sh member
```

### 3-2. Aws cli를 이용하는 방법 
- 더미 테이블 데이터 입력 
```shell
    $ aws dynamodb create-table --cli-input-json file://${PATH}/member_table.json --endpoint-url http://localhost:8000
```

- 초기 더미 데이터 입력
```shell
    $ aws dynamodb batch-write-item --request-items file://${PATH}/input_data.json --endpoint-url http://localhost:8000
```

- 초기 데이터 및 테이블 확인
```shell
    $ aws dynamodb scan --table-name ${TABLE_NAME} --endpoint-url http://localhost:8000
```

## 4. dynamodb-admin 확인
- `http://localhost:8001`

## 5. Docker Compose 종료
```shell
  $ docker-compose down
```


[Local Dynamodb Options](https://docs.aws.amazon.com/ko_kr/amazondynamodb/latest/developerguide/DynamoDBLocal.UsageNotes.html)