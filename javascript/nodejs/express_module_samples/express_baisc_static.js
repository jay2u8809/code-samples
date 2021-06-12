
import express from "express";

// package.json : commonJS -> module
import path from 'path';

const __dirname = path.resolve();
let app = express();

// setting middleware
app.use(express.static(__dirname + "/resources"));

app.use((req, res) => {
    res.writeHead(200, {    "Content-Type": "text/html" });
    res.end("<img src='/img/testWallPaper.jpg' width='100%' />");
});

app.listen(52273, () => {
    console.log(`Server Running Static Module`);
});