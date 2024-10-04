package com.example.prova1pdm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StatsScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.stats_screen)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val banco = Banco(this)
        val cursoDAO = CursoDAO(banco)

        val cursoMaisAlunosText = findViewById<TextView>(R.id.cursoMaisAlunosText)
        cursoMaisAlunosText.setText(cursoDAO.getCourseWithMoreStudents())
        val totalAlunosText = findViewById<TextView>(R.id.totalAlunosText)
        totalAlunosText.setText(cursoDAO.getTotalStudentsInUniversity())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
