package com.example.prova1pdm

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Banco (context: Context) : SQLiteOpenHelper(context, "GerenciamentoIFTM", null, 1){
    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE cursos (codigo int not null, nome varchar(100), " +
                  "numero_de_alunos int, nota_mec float, area varchar(50))"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
