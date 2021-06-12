import express from "express";
import session from "express-session";

const app = express();
const sessionApp = session({
    secret: 'secret key',
    resave: false,
    saveUninitialized: true,
    cookie: {
        maxAge: 60 * 1000
    }
});

app.use(sessionApp);

app.use((req, res) => {
    // save session data
    req.session.now = (new Date()).toUTCString();

    res.send(req.session);
});

app.listen(52273, () => {
    console.log(`Running Server Express Session`);
});
