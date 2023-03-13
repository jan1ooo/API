const formulario = document.querySelector("form");
const inputNome =document.querySelector(".nome");
const inputEmail = document.querySelector(".email");
const inputSenha = document.querySelector(".senha");
const inputTelefone = document.querySelector(".telefone");

function cadastrar(){

    fetch("http://localhost:8080/usuarios",
        {
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                nome: inputNome.value,
                email: inputEmail.value,
                senha: inputSenha.value, 
                telefone: inputTelefone.value
            })    
        }
    )
    .then(function (res) {console.log(res)})
    .then(function (res) {console.log(res)})
};

function limpar(){
    inputNome.value = "",
    inputEmail.value = "",
    inputSenha.value = "", 
    inputTelefone.value = ""
};

formulario.addEventListener('submit', function (event){
    event.preventDefault();

    cadastrar();
    limpar();
    
})
