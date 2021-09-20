# DynamoDB CRUD

https://qiita.com/Fujimon_fn/items/66be7b807a8329496899

## 1. DynamoDB DocumentClient 
* install `aws-sdk` dependency [aws sdk](https://www.npmjs.com/package/aws-sdk)

```shell
    $ npm i aws-sdk
```

```typescript
    import AWS from 'aws-sdk'
    import AWS from 'aws-sdk/global'
    import S3 from 'aws-sdk/clients/s3'
```

* confirm `tsconfig.json` file
```json
  {
    "esModuleInterop": true
  }
```

* if it is not 'true', you can use a different method
```typescript
    import * as AWS from 'aws-sdk';
```

