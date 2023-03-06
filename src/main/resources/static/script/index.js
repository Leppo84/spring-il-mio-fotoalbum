
photoList();

function photoList() {
    axios.get('http://localhost:8080/api').then((res) => {
        //codice da eseguire se la richiesta è andata a buon fine
        console.log('richiesta ok', res);
        document.querySelector('#listaFoto').innerHTML='';
        res.data.forEach(foto => {
            console.log(foto);
            document.querySelector('#listaFoto').innerHTML+= `
            <tr>
                <td>
                    <a href="./detail.html?id=${foto.id}">${foto.id}</a>
                </td>
                <td>${foto.title}</td>
                <td>${foto.description}</td>
                <td>${foto.url}</td>    
                <td>${foto.tag}</td>    
                <td>
                    <a class="btn btn-primary" onclick="deletePhoto(${foto.id})">
                        <i class="fa-solid fa-trash-can"> </i>
                    </a>
                </td>      
                <td>
                    <a class="btn btn-primary" href="./edit.html?id=${foto.id}">
                        <i class="fa-solid fa-pen-to-square"> </i>
                    </a>
                </td>         
            </tr>
            `
        });
    }).catch((res) => {
        //codice da eseguire se la richiesta non è andata a buon fine
        console.error('errore nella richiesta', res);
        alert('Errore durante la richiesta!');
    } )
}


function deletePhoto(photoId) {
    const risposta=confirm('Sei sicuro?');

    if (risposta) {
        axios.delete('http://localhost:8080/photos/'+photoId).then((res) => {
            //ok => ricarico l'elenco dei libri
            photoList();
        }).catch((res) => {
            console.error('errore nella richiesta', res);
            alert('Errore durante la richiesta!');
        });
    }

}