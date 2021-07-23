# How to Use TypeORM

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
