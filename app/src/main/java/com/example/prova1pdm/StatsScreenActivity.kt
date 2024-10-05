package com.example.prova1pdm

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

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
