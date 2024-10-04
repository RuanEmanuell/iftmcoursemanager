package com.example.prova1pdm

class Curso (codigo: Int, nome: String, numeroDeAlunos: Int, notaMec: Float, area: String){
    val codigo : Int
    val nome : String
    val numeroDeAlunos : Int
    val notaMec : Float
    val area : String

    init {
        this.codigo = codigo
        this.nome = nome
        this.numeroDeAlunos = numeroDeAlunos
        this.notaMec = notaMec
        this.area = area
    }

    override fun toString(): String {
        return "\nCodigo: $codigo \n" +
                "Nome: $nome \n" +
                "Numero de alunos : $numeroDeAlunos \n" +
                "Nota Mec : $notaMec \n" +
                "Área : $area \n"

    }
}