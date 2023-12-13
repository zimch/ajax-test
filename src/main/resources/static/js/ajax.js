$(document).ready(function () {
    loadEntries();

    $('#addEntryBtn').click(function () {
        $('#saveNewEntryForm').show();
    });
});

function loadEntries() {
    $.get('/api/entry', function (data) {
        renderEntries(data);
    });
}

function renderEntries(entries) {
    const tableBody = $('#phoneBook tbody');
    tableBody.empty();

    $.each(entries, function (index, entry) {
        const row = tableBody.append('<tr></tr>').children('tr:last');
        row.append('<td>' + entry.subscriberName + '</td>');
        row.append('<td>' + entry.phoneNumber + '</td>');
        row.append('<td>' + entry.lastModifiedDate + '</td>');

        const actionsCell = row.append('<td></td>').children('td:last');

        const editButton = $('<button>Редактировать</button>').click(function () {
            $('#subscriberEditingName').val(entry.subscriberName);
            $('#phoneEditingNumber').val(entry.phoneNumber);
            $('#editEntryForm').show();

            $('#editEntryBtn').click(function () {
                editEntry(entry.entryId);
            });
        });
        actionsCell.append(editButton);

        const deleteButton = $('<button>Удалить</button>').click(function () {
            deleteEntry(entry.entryId);
        });
        actionsCell.append(deleteButton);
    });
}

function saveEntry() {
    const subscriberName = $('#subscriberName').val();
    const phoneNumber = $('#phoneNumber').val();

    $.ajax({
        type: 'POST',
        url: '/api/entry',
        contentType: 'application/json',
        data: JSON.stringify({ subscriberName, phoneNumber }),
        success: function () {
            loadEntries();
            $('#subscriberName, #phoneNumber').val('');
            $('#saveNewEntryForm').hide();
        }
    });
}

function editEntry(id) {
    const subscriberName = $('#subscriberEditingName').val();
    const phoneNumber = $('#phoneEditingNumber').val();

    $.ajax({
        type: 'PUT',
        url: `/api/entry/${id}`,
        contentType: 'application/json',
        data: JSON.stringify({ subscriberName, phoneNumber }),
        success: function () {
            loadEntries();
            $('#subscriberEditingName, #phoneEditingNumber').val('');
            $('#editEntryForm').hide();
        }
    });
}

function cancelForm(type) {
    switch (type) {
        case 'SAVE':
            $('#subscriberName, #phoneNumber').val('');
            $('#saveNewEntryForm').hide();
            break;

        case 'EDIT':
            $('#subscriberEditingName, #phoneEditingNumber').val('');
            $('#editEntryForm').hide();
            break;
    }
}

function deleteEntry(id) {
    $.ajax({
        type: 'DELETE',
        url: `/api/entry/${id}`,
        success: function () {
            loadEntries();
        }
    });
}