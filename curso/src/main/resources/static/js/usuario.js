// Call the dataTables jQuery plugin
$(document).ready(function() {
 cargarUsuario()

  $('#usuarios').DataTable();
  actualizarEmailDelUsuario();
});
function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

async function cargarUsuario(){
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token
    },
  });
  const usuarios = await request.json();
  console.log(usuarios);


  let listadoHtml=""
  for (let usuario of usuarios){
      let botonEliminar ='<a href="#" onClick="eliminarUsuario('+ usuario.id +')" class="btn btn-danger btn-circle" ><i class="fas fa-trash"></i></a>'
      let editarUsuario ='<a href="#" onClick="editarUsuario('+ usuario.id +')" class="btn btn-primary btn-circle btn-sm" ><i class="fas fa-edit"></i></a>'
      let usuariohtml = ' <tr><td>'+usuario.id+'</td><td>'+usuario.nombre+ ' '+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+usuario.telefono+'</td><td>'+botonEliminar+ ' '+editarUsuario+'</td></tr>'
      listadoHtml += usuariohtml;
  }
   console.log(listadoHtml);
   var tbody= document.getElementById("tbody");
   tbody.innerHTML = listadoHtml;
}

async function eliminarUsuario(id){
        if(!confirm('Desea eliminar este usuario?')){
            return;
        }
        const request = await fetch('api/usuarios/'+id, {
            method: 'DELETE',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
              'Authorization': localStorage.token
            },
          });
   }
    function editarUsuario(id) {
        window.location.href = 'editar_usuario.html?id=' + id;
    }
