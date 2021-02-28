
var main = {
    init: function() {
        let self = this;
        $('#btn-save').on('click', function() {
            self.save();
        });
        $('#btn-update').on('click', function() {
            self.update();
        });
        $('#btn-delete').on('click', function() {
            self.delete();
        });
    },
    save: function () {
        let data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts/',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(result) {
            alert(`Success : ${result}`);
            window.location.href="/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        let data = {
            title: $('#title').val(),
            content: $('#content').val(),
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(result) {
            alert(`Update Success : ${result}`);
            window.location.href="/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
     delete: function () {

         let id = $('#id').val();

         $.ajax({
             type: 'DELETE',
             url: '/api/v1/posts/' + id,
             dataType: 'json',
             contentType: 'application/json; charset=utf-8',
         }).done(function(result) {
             alert(`Delete Success : ${result}`);
             window.location.href="/";
         }).fail(function(error) {
             alert(JSON.stringify(error));
         });
     }
};

main.init();