//==============
/**
 * Register Product
 * @author : J.ian
 */
//==============

const REGISTER_PROD = {
    init: function() {
        let self = this;

        // Button 1
        $('#btn1-register-product').on('click', function() {
            self.register()
        });
    },
    register: () => {

        let formUrl = document.querySelector('#btn1-register-product').dataset.url || null;

        let product = {
            productName: document.querySelector('#input-productName').value || null,
            productDesc: document.querySelector('#input-productDesc').value || null,
            productCode: document.querySelector('#input-productCode').value || null,
            productPrice: document.querySelector('#input-productPrice').value || null,
            productSalePrice: document.querySelector('#input-productSalePrice').value || null
        };

        $.ajax({
            url: formUrl,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(product),
            beforeSend: () => {
            },
            // Success : 1
            success: (result) => {
                console.log(result);
            },
            // Fail : 1
            error: (err) => {
                console.log(err);
                console.log(err.responseJSON);
                console.log(err.responseText);
                console.log(err.statusText);
                console.log(err.status);
            },
            // Success & Fail : 4
            complete : (data) => {
                console.log(data.responseJSON);
                console.log(data.responseText);
                console.log(data.statusText);
                console.log(data.status);
            }
        })
        // Success : 2
        .done((result) => {
            console.log(result);
            if (result.productSn != null) {
                alert("Success");
            }
            REGISTER_PROD.cancelInput();
        })
        // Fail : 2
        .fail((jqXHR, textStatus, errorThrown) => {
            let err = jqXHR;
            console.log(err.responseJSON);
            console.log(err.responseText);
            console.log(err.statusText);    // textStatus == err.statusText
            console.log(err.status);
        })
        // Success & Fail : 3
        .always((data) => {
            data;
            data.responseJSON;
            data.responseText;
            data.responseText;
            data.responseText;
        });
    },
    cancelInput: () => {

        let inputProductName = document.querySelector('#input-productName');
        let inputProductDesc = document.querySelector('#input-productDesc');
        let inputProductCode = document.querySelector('#input-productCode');
        let inputProductPrice = document.querySelector('#input-productPrice');
        let inputProductSalePrice = document.querySelector('#input-productSalePrice');

        inputProductName.value = '';
        inputProductDesc.value = '';
        inputProductCode.value = '';
        inputProductPrice.value = '';
        inputProductSalePrice.value = '';
    }

};

REGISTER_PROD.init();