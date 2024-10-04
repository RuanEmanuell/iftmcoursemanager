package com.example.prova1pdm

class CursoDAO (banco: Banco) {
    val banco : Banco
    init {
        this.banco = banco
    }

    fun insertCurso(curso: Curso){
        val codigo = curso.codigo
        val nome = curso.nome
        val numeroDeAlunos = curso.numeroDeAlunos
        val notaMec = curso.notaMec
        val area = curso.area

        val sql = "INSERT INTO cursos (codigo, nome, numero_de_alunos, nota_mec, area) VALUES (?, ?, ?, ?, ?)"
        val db = this.banco.writableDatabase
        db.execSQL(sql, arrayOf(curso.codigo, curso.nome, curso.numeroDeAlunos, curso.notaMec, curso.area))
    }

    fun editCurso(curso: Curso){
        val codigo = curso.codigo
        val nome = curso.nome
        val numeroDeAlunos = curso.numeroDeAlunos
        val notaMec = curso.notaMec
        val area = curso.area

        val sql = "UPDATE cursos SET nome = ?, numero_de_alunos = ?, nota_mec = ?, area = ? WHERE codigo = ?"
        val db = this.banco.writableDatabase
        db.execSQL(sql, arrayOf(curso.nome, curso.numeroDeAlunos, curso.notaMec, curso.area, curso.codigo))

    }

    fun removeCurso(codigo: Int){
        val sql = "DELETE FROM cursos where codigo = $codigo"
        val db = this.banco.writableDatabase
        db.execSQL(sql)
    }

    fun getAllCursos() : List<Curso> {
        val sql = "SELECT * FROM cursos"
        val db = this.banco.writableDatabase
        val cursor = db.rawQuery(sql, null)
        val listCursos = mutableListOf<Curso>()

        cursor.use {
            val codigoIndex = it.getColumnIndex("codigo")
            val nomeIndex = it.getColumnIndex("nome")
            val numeroDeAlunosIndex = it.getColumnIndex("numero_de_alunos")
            val notaMecIndex = it.getColumnIndex("nota_mec")
            val areaIndex = it.getColumnIndex("area")

            while (it.moveToNext()) {
                val codigo = it.getInt(codigoIndex)
                val nome = it.getString(nomeIndex)
                val numeroDeAlunos = it.getInt(numeroDeAlunosIndex)
                val notaMec = it.getFloat(notaMecIndex)
                val area = it.getString(areaIndex)

                val curso = Curso(codigo, nome, numeroDeAlunos, notaMec, area)
                listCursos.add(curso)
            }
        }

        return listCursos
    }




}