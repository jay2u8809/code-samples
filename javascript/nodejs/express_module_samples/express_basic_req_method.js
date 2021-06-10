
import express from "express";

let app = express();

app.use((req, res, next) => {
    req.number = 52;
    res.number = 273;
    console.log(`1st`);
    next();
});
app.use((req, res, next) => {
    req.number = 50;
    res.number = 270;
    console.log(`3rd`);
    next();
});
app.use((req, res, next) => {
    let name = req.query.name;
    console.log(`2nd ${name}`);
    next();
});
app.use((req, res, next) => {
    let region = req.query.region;
    console.log(`5th ${region}`);
    next();
});

app.use((req, res, next) => {

    console.log(`4th ${req.number} ${res.number}`);

    // USER-AGENT Properties
    let agent = req.header("User-Agent");
    console.log(req.headers);
    console.log(`AGENT : ${agent}`);

    res.writeHead(200, {    "Content-Type": "text/html" });
    if (agent.toLowerCase().match(/chrom/)) {
        // res.sendStatus(200).send("<h1>CHROME BROWSER</h1>");
        res.end("<h1>CHROME BROWSER</h1>")
    } else {
        // res.sendStatus(200).send("<h1>OTHER BROWSER</h1>");
        res.end("<h1>OTHER BROWSER</h1>")
    }


}).listen(52273, () => {
    console.log("Server Running Basic Req Method");
});


