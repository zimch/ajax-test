document.addEventListener("DOMContentLoaded", function () {
    loadEntries();

    document.getElementById('addEntryBtn').addEventListener('click', function () {
        document.getElementById('saveNewEntryForm').style.display = 'block';
    });
});

function loadEntries() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/api/entry', true);

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = JSON.parse(xhr.responseText);
            renderEntries(data);
        }
    };

    xhr.send();
}

function renderEntries(entries) {
    const tableBody = document.querySelector('#phoneBook tbody');
    tableBody.innerHTML = '';

    entries.forEach(entry => {
        const row = tableBody.insertRow();
        row.insertCell(0).textContent = entry.subscriberName;
        row.insertCell(1).textContent = entry.phoneNumber;
        row.insertCell(2).textContent = entry.lastModifiedDate;

        const actionsCell = row.insertCell(3);
        const editButton = document.createElement('button');
        editButton.textContent = 'Редактировать';
        editButton.addEventListener('click', function () {
            document.getElementById('editEntryForm').style.display = 'block';
            document.getElementById('editEntryBtn').addEventListener('click', function () {
                editEntry(entry.id);
            });
        });
        actionsCell.appendChild(editButton);

        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Удалить';
        deleteButton.addEventListener('click', function () {
            deleteEntry(entry.id);
        });
        actionsCell.appendChild(deleteButton);
    });
}

function saveEntry() {
    const subscriberName = document.getElementById('subscriberName').value;
    const phoneNumber = document.getElementById('phoneNumber').value;

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/entry', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (xhr.status === 201) {
            var data = JSON.parse(xhr.responseText);

            loadEntries();

            document.getElementById('subscriberName').value = '';
            document.getElementById('phoneNumber').value = '';

            document.getElementById('saveNewEntryForm').style.display = 'none';
        }
    };

    xhr.send(JSON.stringify({ subscriberName, phoneNumber }));
}

function editEntry(id) {
    const subscriberName = document.getElementById('subscriberEditingName').value;
    const phoneNumber = document.getElementById('phoneEditingNumber').value;

    var xhr = new XMLHttpRequest();
    xhr.open('PUT', `/api/entry/${id}`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (xhr.status === 201) {
            var data = JSON.parse(xhr.responseText);

            loadEntries();

            document.getElementById('subscriberEditingName').value = '';
            document.getElementById('phoneEditingNumber').value = '';

            document.getElementById('editEntryForm').style.display = 'none';
        }
    };

    xhr.send(JSON.stringify({ subscriberName, phoneNumber }));
}

function cancelForm(type) {
    switch (type) {
        case 'SAVE':
            document.getElementById('subscriberName').value = '';
            document.getElementById('phoneNumber').value = '';
            document.getElementById('saveNewEntryForm').style.display = 'none';
            break;

        case 'EDIT':
            document.getElementById('subscriberEditingName').value = '';
            document.getElementById('phoneEditingNumber').value = '';
            document.getElementById('editEntryForm').style.display = 'none';
            break;
    }

}

function deleteEntry(id) {
    var xhr = new XMLHttpRequest();
    xhr.open('DELETE', `/api/entry/${id}`, true);

    xhr.onreadystatechange = function() {
        if (xhr.status === 204) {
            loadEntries();
        }
    };

    xhr.send();
}