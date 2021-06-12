
import express from 'express';
import cookieParserModule from 'cookie-parser';

const app = express();
const cookieParser = cookieParserModule();

app.use(cookieParser);

app.get('/getCookie', (req, res) => {
    if (req.cookies.length !== 0) {
        console.log(`COOKIE : ${req.cookies.stringSample}`)
        // remove cookie
        res.clearCookie('stringSample', {path: '/'})
    }
    res.send(req.cookies);
});

app.get('/setCookie', (req, res) => {

    let cookieValue = {
        name: '',
        property: ''
    };

    // make Cookie data
    res.cookie('stringSample', 'cookie');

    cookieValue.name = 'jsonSampleNameTest';
    cookieValue.property = 'jsonSamplePropertyTest';
    res.cookie('jsonSample', cookieValue);

    cookieValue.name = 'SampleNameTest';
    cookieValue.property = 'SamplePropertyTest';
    res.cookie('sampleTest', cookieValue, {
        maxAge: 6000,
        secure: true,
        path: '/sample'
    });

    res.redirect('/getCookie');
});

app.listen(52273, () => {
    console.log("Server Running Cookie Parser");
});
