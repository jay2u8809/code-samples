
import express from "express";

let app = express();

let routerA = express.Router();
let routerB = express.Router();

// GET Method
routerA.get("/basic", (req, res, next) => {
    console.log(`${req.query}`);
    res.send("GET /BASIC METHOD");
});

routerB.get("/basic/:id", (req, res, next) => {
    console.log(`${req.params.id}`);
    res.send("GET /BASIC Params");
});

// Router Setting
app.use("/a", routerA); // path : /a/basic
app.use("/b", routerB); // path : /b/basic/XX

// All
app.all("*", (req, res, next) => {
    res.send("GET /BASIC ALL");
});

app.listen(52274, () => {
    console.log(`Get Method`);
});