fetch(`api/current-user`).then(res => {
        res.json().then(u => {
            let tempU = ""
            tempU += '<td>' + u.id + '</td>'
            tempU += '<td>' + u.username+ '</td>'
            tempU += '<td>' + u.firstname+ '</td>'
            tempU += '<td>' + u.lastname+ '</td>'
            tempU += '<td>' + u.email+ '</td>'
            tempU += '<td>' + u.roles.map(r => r.role) + '</td>'
            document.getElementById('currentUserData').innerHTML = tempU
        })
    }
)