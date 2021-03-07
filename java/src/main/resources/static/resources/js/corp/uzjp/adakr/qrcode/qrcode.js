
var QRCODE = {
    init: function() {
        let self = this;

        $('#btn-generate-qrcode').on('click', function() {
            self.generate();
        });
        $('#btn-cancel').on('click', function() {
            self.cancelInput();
        });
    },
    generate: () => {

        let inputPath = document.querySelector('#input-path').value || null;
        let inputSize = document.querySelector('#input-size').value || null;
        let formUrl = document.querySelector('#btn-generate-qrcode').dataset.url || null;
        let displayQrcode = document.querySelector('#display-qrcode');

        if (COMMON_UTILS.isEmpty(inputPath) || COMMON_UTILS.isEmpty(formUrl)) {
            QRCODE.cancelInput();
            return;
        }

        if (isNaN(Number(inputSize))) {
            QRCODE.cancelInput();
            return;
        }

        let qrCodeViewEntry = {
            qrcodeUri  : inputPath,
            qrcodeSize : Number(inputSize)
        };

        $.ajax({
            url: formUrl,
            type: 'POST',
            data: qrCodeViewEntry,
            dataType: 'json',
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
            displayQrcode.innerHTML =
                    `<img src='${result.qrCodeUrl}' alt='qrcode' />`;
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

        let displayQrcode = document.querySelector('#display-qrcode');
        let inputPath = document.querySelector('#input-path');
        let inputSize = document.querySelector('#input-size');

        displayQrcode.innerHTML = '';
        inputPath.value = '';
        inputSize.value = '';
    }
}

QRCODE.init();