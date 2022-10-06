package com.junior.testepi.model

data class Postagem (
    var id: Long,
//    var nome: String,
    var imagem: String,
    var descricao: String,
//    var data: String,
    var tema: Tema
        ){
}