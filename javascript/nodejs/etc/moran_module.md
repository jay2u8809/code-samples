### morgan's token
| Token | Desc | 
| :req[header] | request header |
| :res[header] | response header |
| :http-version | HTTP version|
| :response-time | response time|
| :remote-addr | remote address |
| :date[format] | request time |
| :method | request method |
| :url | request url |
| :referrer | previous url |
| :User-Agent | User Agent|
| :status | Status Code|

```js
    const express = require('express');
    const logger = require('morgan');
    
    const app = express();
    
    app.use(logger(':method + :date'));
    app.use((req, res) => {
        res.send('<h1>Express Basic</h1>');
    });
```

## morgan type
| Type     | Desc |
| combined | :remote-addr - :remote-ser [:date[clf]] ":method :url HTTP/:http-version" :status :res[content-length] ":referrer" ":user-agent" |
| common   | :remote-addr - :remote-ser [:date[clf]] ":method :url HTTP/:http-version" :status :res[content-length] |
| dev      | :method :url :status :response-time ms - :res[content-length] |
| short    | :remote-addr - :remote-ser :method :url HTTP/:http-version :status :res[content-length] - :response-time ms |
| tiny     | :method :url :status :res[content-length] - response-time ms |

```js
    const express = require('express');
    const logger = require('morgan');
    
    const app = express();
    
    app.use(logger('dev'));
    app.use((req, res) => {
        res.send('<h1>Express Basic</h1>');
    });
```
