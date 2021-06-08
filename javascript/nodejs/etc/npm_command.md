### npm 自体のアップデート
```shell
    $ npm install -g npm
```

### install：モジュールのバージョンを指定してインストールする場合
```shell
    $ npm install ejs@2.4.1       // install version 2.4.1
    $ npm install ejs@2.3         // Install the latest version of version 2.3
    $ npm install ejs@2           // Install the latest version of version 2
```

### install--save：インストールバージョンを保存可能
```shell
    $ npm install --save jade     // or $ npm install jade --save
    $ npm install --save ejs@1    // or $ npm install ejs@1 --save
```