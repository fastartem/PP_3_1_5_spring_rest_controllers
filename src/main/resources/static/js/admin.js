const userFetchService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    findAllUsers: async () => await fetch('api/admin/users'),
    findAllRoles: async () => await fetch('api/admin/roles'),
    findOneUser: async (id) => await fetch(`api/admin/user?id=${id}`, {method: 'GET'}),
    saveUser: async (user) => await fetch('api/admin/user', {
        method: 'POST',
        headers: userFetchService.head,
        body: JSON.stringify(user)
    }),
    updateUser: async (user) => await fetch(`api/admin/user`, {
        method: 'PUT',
        headers: userFetchService.head,
        body: JSON.stringify(user)
    }),
    deleteUser: async (id) => await fetch(`api/admin/user?id=${id}`, {method: 'DELETE', headers: userFetchService.head})
}

fillAllUsersTable()

//all users table
function fillAllUsersTable() {
    userFetchService.findAllUsers().then(res => {
        let table = ""
        document.getElementById('usersData').innerHTML = table

        res.json().then(data => {
            if (data.length > 0) {
                data.forEach(user => {
                    table = ""
                    table += '<td>' + user.id + '</td>'
                    table += '<td>' + user.username + '</td>'
                    table += '<td>' + user.firstname + '</td>'
                    table += '<td>' + user.lastname + '</td>'
                    table += '<td>' + user.email + '</td>'
                    table += '<td>' + user.roles.map(r => r.role) + '</td>'
                    table += '<td>' + '<button data-bs-toggle="modal" class="btn btn-primary"' +
                        ' data-bs-target="#editModal" onclick=moduleEdit(' + user.id + ')' + '>Edit</button>' + '</td>'
                    table += '<td>' + '<button data-bs-toggle="modal" class="btn btn-danger"' +
                        ' data-bs-target="#deleteModal" onclick=moduleDelete(' + user.id + ')' + '>Delete</button>' + '</td>'
                    document.getElementById('usersData').innerHTML += table
                })
            }
        })
    })
}

//edit
function moduleEdit(id) {
    userFetchService.findOneUser(id).then(res => {
        res.json().then(userData => {
                document.getElementById('editId').value = userData.id
                document.getElementById('editUsername').value = userData.username
                document.getElementById('editFirstname').value = userData.firstname
                document.getElementById('editLastname').value = userData.lastname
                document.getElementById('editEmail').value = userData.email
                document.getElementById('editPassword').value = userData.password
            }
        )
    })
    userFetchService.findAllRoles().then(rol => {
        let editRoles = document.getElementById('editRole')
        rol.json().then(data => {
            editRoles.innerHTML = ''
            data.forEach((el) => {
                const option = document.createElement('option')
                option.innerHTML = el.role
                editRoles.innerHTML += `<option value="${el.role}">${el.role}</option>>`
            })
        })
    })
}

//delete
function moduleDelete(id) {
    userFetchService.findOneUser(id).then(res => {
            res.json().then(userData => {
                document.getElementById('deleteId').value = userData.id
                document.getElementById('deleteUsername').value = userData.username
                document.getElementById('deleteFirstname').value = userData.firstname
                document.getElementById('deleteLastname').value = userData.lastname
                document.getElementById('deleteEmail').value = userData.email

                let editRoles = document.getElementById('deleteRole')
                editRoles.innerHTML = ''
                document.createElement('option')
                editRoles.innerHTML += `<option>${userData.roles.map(r => r.role)}</option>>`
            })
        }
    )
}

//event edit
document.querySelector('#editUser').addEventListener('click', async (e) => {
    let editUser = {
        id: parseInt($('#editId').val()),
        username: $('#editUsername').val(),
        firstname: $('#editFirstname').val(),
        lastname: $('#editLastname').val(),
        email: $('#editEmail').val(),
        password: $('#editPassword').val(),
        roles: $("#editRole").val()
    }
    await userFetchService.updateUser(editUser, editUser.id);
    await fillAllUsersTable();
})

//event delete
document.querySelector('#deleteUser').addEventListener('click', async (e) => {
    await userFetchService.deleteUser(parseInt($('#deleteId').val()));
    await fillAllUsersTable();
})

//event new user
document.querySelector('#newUserTabLink').addEventListener('click', () => {
    document.getElementById('newUsername')
    document.getElementById('newFirstname')
    document.getElementById('newLastname')
    document.getElementById('newEmail')
    document.getElementById('newPassword')

    userFetchService.findAllRoles().then(rol => {
        let newRoles = document.getElementById('newRoles')
        rol.json().then(data => {
            newRoles.innerHTML = ''
            data.forEach((el) => {
                const option = document.createElement('option')
                option.innerHTML = el.role
                newRoles.innerHTML += `<option value="${el.role}">${el.role}</option>>`
            })
        })
    })
})

document.querySelector('#newUser').addEventListener('click', async (e) => {
    const newUser = {
        id: parseInt($('#newId').val()),
        username: $('#newUsername').val(),
        firstname: $('#newFirstname').val(),
        lastname: $('#newLastname').val(),
        email: $('#newEmail').val(),
        password: $('#newPassword').val(),
        roles: $("#newRoles").val()
    }
    await userFetchService.saveUser(newUser)
    fillAllUsersTable()
    $('.nav-tabs a[href="#table"]').tab('show');
})