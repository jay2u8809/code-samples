
import express from "express";

let app = express();
app.use((req, res, next) => {
    let output = [];
    for (let i = 0; i < 3; i++) {
        output.push({
            count: i,
            name: 'name - ' + i
        });
    }

    res.status(404).send("<h1> ERROR </h1>");

}).listen(52273, () => {
    console.log("Server Running")
});