
var main = {
    init: function() {
        let _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });
    },
    save: function () {
        let data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#author').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts/',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert("Success");
            window.location.href="/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();