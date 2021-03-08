//==============
/**
 * NAVER MAPS API
 * @author : J.ian
 */
//==============

// Naver Map Object
const map = null;

const NAVER_MAP = {
    init: function() {
        let self = this;

        // Button1
        $('#btn1-generate-map').on('click', function() {
            self.generate();
        });
        // Cancel Button
        $('#btn-cancel').on('click', function() {
            self.cancelInput();
        });
    },
    generate: () => {

        let inputAddress = document.querySelector('#input-address').value || null;
        let formUrl = document.querySelector('#btn1-generate-map').dataset.url || null;
        let displayMap = document.querySelector('#display-map');

        if (COMMON_UTILS.isEmpty(inputAddress) || COMMON_UTILS.isEmpty(formUrl)) {
            QRCODE.cancelInput();
            return;
        }

        $.ajax({
            url: formUrl,
            type: 'POST',
            data: {
                address: inputAddress
            },
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

            let lng            = result.mapInfo[0].x;
            let lat            = result.mapInfo[0].y;
            let roadAddress    = result.mapInfo[0].roadAddress;
            let jibunAddress   = result.mapInfo[0].jibunAddress;
            let englishAddress = result.mapInfo[0].englishAddress;

            NAVER_MAP.displayMapInfo(lat, lng, 'display-map', false);

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
    displayMapInfo: (lat, lng, mapAreaId, isContinue) => {

        if(!isContinue) return;

        if(!lat || !lng) {
            document.querySelector('#' + mapAreaId).innerHTML = '';
            return;
        }

        // Map Lat, Lng Setting
        let latInfo = new naver.maps.LatLng(lat, lng);

        // Display Option
        let mapOptions = {
            center: latInfo     // Center Position
            , zoom: 15          // Zoom
        };

        // Generate Map
        map = new naver.maps.Map(mapAreaId, mapOptions);

        // Display Marker Icon
        let marker = new naver.maps.Marker({
            position: latInfo,  // Maker Position
            map: map            // Map Info
        });
    },
    cancelInput: () => {

        let displayMap = document.querySelector('#display-map');
        let inputAddress = document.querySelector('#input-address');

        displayMap.innerHTML = '';
        inputAddress.value = '';
    }
}

NAVER_MAP.init();