package com.example.prova1pdm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val banco = Banco(this)
        val cursoDAO = CursoDAO(banco)
        cursoDAO.insertCurso(Curso(1, "ADS", 30, 4.5F, "T.I"))
        val listCursos = cursoDAO.getAllCursos()

        for (curso in listCursos){
            Toast.makeText(this, curso.nome, Toast.LENGTH_SHORT).show()
        }

    }
}