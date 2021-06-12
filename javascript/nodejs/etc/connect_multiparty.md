## Connect Multiparty
 - Encoding :  multipart/form-data
 - 파일 전송에 사용되는 모듈

### install module
```shell
    $ npm install connect-multiparty
```

### 특정 라우터에서만 모듈 사용하기
```js
    import multipart from "connect-multiparty";

    const uploadFilePath = __dirname + '/resources/multipart/';
    const multiparts = multipart({
        uploadDir: uploadFilePath
    });
    app.post('/', multiparts, (req, res) => {
        
    });
```