# How to Use TypeORM

* [TypeORM Tutorial](https://www.tutorialspoint.com/typeorm/typeorm_query_builder.htm)

## PostgreSQL
- Install Dependencies
```shell
    $ npm i @nestjs/typeorm typeorm pg
```
### TypeOrm Generator
- `-h` : host
- `-d` : database name
- `-s` : schema name
- `-u` : user id
- `-x` : user password
- `-e` : DBMS
- `-p` : port
```shell
  $ npx typeorm-model-generator -h localhost -d devdb -s dev_schema -u devuser -x devuser -e postgres -p 5432
```
- **ormconfig.json**      
root directory에 위치 해둔다.
  
- TypeOrm Generator로 만든 Entity에서 Option의 Default는 단순 표기용, 변경해도 디비에 설정되어 있는 기본값이 세팅된다.        

