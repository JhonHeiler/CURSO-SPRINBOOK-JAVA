// Call the dataTables jQuery plugin
$(document).ready(function() {
cargarRol()
});

async function registrarUsuario(){

    let datos = {};
    datos.nombre = document.getElementById("nombre").value;
    datos.apellido = document.getElementById("apellido").value;
    datos.email = document.getElementById("email").value;
    datos.telefono = document.getElementById("telefono").value;
    datos.password = document.getElementById("password").value;
    datos.id_rol = document.getElementById("rol").value;

    let Rpassword = document.getElementById("Rpassword").value;

    if(Rpassword != datos.password){
        alert('La contrasena no coinciden')
        return
    }
    console.log(datos);
  const request = await fetch('api/usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body:JSON.stringify(datos)
  });
  alert("la cuenta fue creada con exito!");
  window.location.href= 'login.html'
}


async function cargarRol(){
  const request = await fetch('api/rol', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
  });
  const roles = await request.json();


  let listadoHtml=""
  for (let rol of roles){
      let rolhtml = '<option value="'+ rol.id_rol +'">'+rol.nombre+'</option>'
      listadoHtml += rolhtml;
  }
   console.log(listadoHtml);
   var option= document.getElementById("rol");
   option.innerHTML = '<option>Open this select menu</option>'+ listadoHtml;
}
