import fs from "fs";
import express from "express";
import multipart from "connect-multiparty";
// package.json : commonJS -> module
import path from 'path';

const __dirname = path.resolve();
const uploadFilePath = __dirname + '/resources/multipart/';
const app = express();

const multiparts = multipart({
    uploadDir: uploadFilePath
});
// middleware setup
// app.use(multipart({
//     uploadDir: uploadFilePath
// }));

// router setup
app.get('/', (req, res) => {
    fs.readFile('./resources/html/HTMLPage.html', (err, data) => {
        res.send(data.toString());
    });
});

// 특정 라우터에만 모듈을 사용 : multiparts
app.post('/', multiparts, (req, res) => {
    console.log(req.body);
    console.log(req.files);

    let comment = req.body.comment;
    let uploadFile = req.files.image;

    if (uploadFile && uploadFile.size > 0) {

        let fileName = uploadFile.name;
        let filePath = uploadFile.path;
        let fileType = uploadFile.type;

        if (fileType.indexOf('image') != -1) {
            // change file name
            let outputPath = uploadFilePath + Date.now() + '_' + fileName;
            fs.rename(filePath, outputPath, (err) => {
                res.redirect('/');
            });
        } else {
            // remove file
            fs.unlink(filePath, (err) => {
                res.sendStatus(400);
            });
        }

    } else {
        // file is empty
        res.sendStatus(404);
    }

    // res.redirect('/');
});

app.listen(52273, () => {
    console.log(`Running Server Multipart`)
});