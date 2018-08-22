var form;
var serverUrl = "tickets";

function makeEditable() {
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });
    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});

}

function add() {
    makeEditable();
    $('#modalTitle').html("Add record");
    form.find(":input").val("");
    $('#editRow').modal();

    setTokens()
}

function setTokens() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

// function updateRow(id) {
//     makeEditable();
//     $('#modalTitle').html("Edit record");
//     $.get(serverUrl + '/' + id, function (data) {
//         $.each(data, function (key, value) {
//             form.find("input[name='" + key + "']").val(value);
//         });
//         $('#editRow').modal();
//     });
//     setTokens();
// }
function updateRow(id) {
    makeEditable();
    $('#modalTitle').html("Edit record");
    $.get(serverUrl + '/' + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            form.find("textarea[name='" + key + "']").val(value);
            form.find("select[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
    setTokens();
}

function deleteRow(id) {
    makeEditable();
    setTokens();
    $.ajax({
        url: serverUrl + "/" + id,
        type: 'DELETE',
        success: function () {
            updateTable();
        }
    });
}

function updateTable() {
    window.location.replace(serverUrl);
}

function save() {
    $.ajax({
        type: "POST",
        url: serverUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    noty({
        text: key,
        type: 'success',
        layout: 'bottomRight',
        timeout: 2000
    });
}

function failNoty(jqXHR) {
    closeNoty();
    var errorInfo = $.parseJSON(jqXHR.responseText);
    failedNote = noty({
        text: errorInfo.detail,
        type: 'error',
        layout: 'bottomRight',
        timeout: 8000
    });
}

