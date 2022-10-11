package com.junior.testepi.util

object Validator {

    fun validarEmail(email: String): Boolean{
        return !(email.isEmpty() || email.isBlank())
    }

    fun validarIdade(idade: String): Boolean{
        return !(idade.isEmpty() || idade.isBlank() || idade.toInt() < 18)
    }
    fun validarSenha(senha: String): Boolean{
        return !(senha.isEmpty() || senha.isBlank())
    }

    fun validarNome(nome: String): Boolean{
        return !(nome.isEmpty() || nome.isBlank())
    }


}