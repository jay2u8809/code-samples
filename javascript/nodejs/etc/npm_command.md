### npm 自体のアップデート
```shell
    $ npm install -g npm  // or $ npm install -g npm@latest
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

### uninstall --save：アンインストール
```shell
    $ npm uninstall --save jade     // or $ npm uninstall jade --save
    $ npm uninstall --save ejs@1    // or $ npm uninstall ejs@1 --save
```

### Ci
npm install コマンドは package.jsonファイルのdependenciesとdevDependenciesを基準でパッケージファイルをインストールする。
npm ci コマンドは package.jsonより package-lock.jsonの優先度が高い。
package.jsonファイルとpackage-lock.jsonファイルのバージョンが異なる場合、package-lock.jsonファイルを基準でpackage.jsonファイルを修正し、
明示されない部分でエラーを発生させますので、Application管理の安定性が高い
```shell
    $ npm ci  
```

### productionレベルまでPackageインストール
```shell
    $ npm ci --only=production    
```

### install Express
```shell
    $ sudo npm install -g express-generator@4 // mac or linux
    $ npm install -g express-generator@4      // windows
```

### generate Express Project
```shell
    $ express ProjectName
```

### install Express Dependencies
```shell
    $ cd ProjectName && npm install
```

### Set DEBUG Variable and start express project
```shell
    // mac or linux
    $ export DEBUG=ProjectName:*
    $ npm start
     // windows
    $ SET DEBUG=ProjectName:* & npm start
```

### Set NodeEnv Variable and start express project
```shell
    // mac or linux
    $ export NODE_ENV=development  // production
    $ npm start
     // windows
    $ SET NODE_ENV=development     // production
    $ npm start
```

### start Express App
```shell
    $ node ./bin/www
```