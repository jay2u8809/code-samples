
// add "type": "module" at package.json
import express_sample from "express";
// let express_sample = require('express');

let app = express_sample();
app.use((req, res) => {
    res.writeHead(200, {    'Content-Type': 'text/html' });
    res.end('<h1>Hello Express Module</h1>');
}).listen(52273, () => {
    console.log('Server Running');
});
