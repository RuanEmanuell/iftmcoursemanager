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

    fun searchCurso(codigoCurso: Int): Curso? {
        val sql = "SELECT * FROM cursos WHERE codigo = ?"
        val db = this.banco.readableDatabase
        val cursor = db.rawQuery(sql, arrayOf(codigoCurso.toString()))

        cursor.use {
            if (it.moveToFirst()) {
                val codigoIndex = it.getColumnIndex("codigo")
                val nomeIndex = it.getColumnIndex("nome")
                val numeroDeAlunosIndex = it.getColumnIndex("numero_de_alunos")
                val notaMecIndex = it.getColumnIndex("nota_mec")
                val areaIndex = it.getColumnIndex("area")

                val codigo = it.getInt(codigoIndex)
                val nome = it.getString(nomeIndex)
                val numeroDeAlunos = it.getInt(numeroDeAlunosIndex)
                val notaMec = it.getFloat(notaMecIndex)
                val area = it.getString(areaIndex)

                return Curso(codigo, nome, numeroDeAlunos, notaMec, area)
            }
        }
        return null
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

    fun getAllCursosString() : List<String> {
        val listStringCursos = mutableListOf<String>()
        val listCursos = getAllCursos()

        for (curso in listCursos){
            listStringCursos.add(curso.toString())
        }

        return listStringCursos
    }

    fun getCourseWithMoreStudents() : String? {
            val sql = "SELECT * FROM cursos WHERE numero_de_alunos = (SELECT MAX(numero_de_alunos) from cursos)"
            val db = this.banco.readableDatabase
            val cursor = db.rawQuery(sql, null)

            cursor.use {
                if (it.moveToFirst()) {
                    val codigoIndex = it.getColumnIndex("codigo")
                    val nomeIndex = it.getColumnIndex("nome")
                    val numeroDeAlunosIndex = it.getColumnIndex("numero_de_alunos")
                    val notaMecIndex = it.getColumnIndex("nota_mec")
                    val areaIndex = it.getColumnIndex("area")

                    val codigo = it.getInt(codigoIndex)
                    val nome = it.getString(nomeIndex)
                    val numeroDeAlunos = it.getInt(numeroDeAlunosIndex)
                    val notaMec = it.getFloat(notaMecIndex)
                    val area = it.getString(areaIndex)

                    return Curso(codigo, nome, numeroDeAlunos, notaMec, area).toString()
                }
            }
        return null
    }

    fun getTotalStudentsInUniversity() : String? {
        val sql = "SELECT SUM(numero_de_alunos) AS totalAlunos FROM cursos"
        val db = this.banco.readableDatabase
        val cursor = db.rawQuery(sql, null)

        cursor.use {
            if (it.moveToFirst()) {
                val totalAlunosIndex = it.getColumnIndex("totalAlunos")
                val totalAlunos = "${it.getString(totalAlunosIndex)} alunos"
                return totalAlunos
            }
        }
        return null
    }





}