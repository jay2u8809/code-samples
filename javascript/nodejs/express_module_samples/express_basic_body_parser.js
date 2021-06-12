import fs from 'fs';
import express from 'express';
import cookieParser from 'cookie-parser';
import bodyParser from "body-parser";

const app = express();

// middleware setup
app.use(cookieParser());
app.use(bodyParser.urlencoded({ extended: false }));

// router setup
app.get('/', (req, res) => {
    if (req.cookies.auth) {
        res.send('<h1>Login Success</h1>');
    } else {
        res.redirect('/login');
    }
});
app.get('/login', (req, res) => {
    fs.readFile('./resources/html/login.html', (err, data) => {
        res.send(data.toString());
    });
});
app.post('/login', (req, res) => {
    // make cookie
    let login = req.body.login;
    let pass = req.body.password;

    console.log(`Login : ${login}, Password : ${pass}`);
    console.log(req.body);

    // Confirm Login
    if (login == 'rint' && pass == '1234') {
        // login success
        res.cookie('auth', true);
        res.redirect('/');
    } else {
        // login fail
        res.redirect('/login');
    }
});

// server running
app.listen(52273, () => {
    console.log(`Server Running Body Parser`);
});