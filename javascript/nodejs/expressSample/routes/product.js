const express = require('express');
const router = express.Router();

/* Product */
// GET - /product
router.get('/', (req, res, next) => {
    res.render('index', {
        title: 'Product Page'
    });
});

// GET - /product/insert
router.get('/insert', (req, res, next) => {
    res.render('product/insert', {
        title: 'Insert Page'
    });
});

// GET - /product/edit
router.get('/edit', (req, res, next) => {
    res.render('product/edit', {
        title: 'Edit Page'
    });
});


module.exports = router;
