const URLParams=new URLSearchParams(window.location.search);
const photoId=URLParams.get('id');

document.querySelector('#save-comment').addEventListener("click", function(event){
	createComment(event);
})

function createComment(event) {
	        console.log("Ha preso il click");
    
	event.preventDefault();
	
	const comment = {
		author : document.querySelector('#author').value,
		content : document.querySelector('#content').value, 
		photo : {id:photoId},
	};
    
    axios.post('http://localhost:8080/api', comment).then((res) => {
        console.log("inserimento ok");
        location.href="/guest/show?id={id:photoId}";
     
    }).catch((res) => {
        console.error("errore nell'inserimento",res);
//        showValidationErrors(res.response.data.errors);
    })
	
};

//axios.get(`http://localhost:8080/guest/${bookId}`).then((res) => {

axios.get(`http://localhost:8080/api/${photoId}`).then((res) => {
    console.log('richiesta ok', res);
    //res.data è una foto 
 	document.querySelector('#url').innerHTML+= `
				<img src="${res.data.url}" class="img-fluid rounded" alt="..."></img>
				`
    document.querySelector('#title').innerHTML= res.data.title;
    document.querySelector('#description').innerHTML= res.data.description;
    document.querySelector('#tag').innerHTML=`#`+ res.data.tag;
    res.data.categories.forEach(category => {
        document.querySelector('#ul_categories').innerHTML+=
        '<li>'+category.name+'</li>';
    });
    res.data.comments.forEach(comment => {
        document.querySelector('#comments_table').innerHTML+=
        `<tr>
        <td>`+comment.id+`</td>
        <td>`+comment.author+`</td>
        <td>`+comment.content+`</td>
        </tr>
        `;
    });
}).catch((res) => {
    console.error('errore nella richiesta', res);
    alert('Errore durante la richiesta!');
});