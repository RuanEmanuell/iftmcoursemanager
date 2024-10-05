package com.example.prova1pdm

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File

class MainActivity : AppCompatActivity() {
    var codigoCurso : Int? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val addButton = findViewById<FloatingActionButton>(R.id.addButton)
        val viewButton = findViewById<FloatingActionButton>(R.id.viewButton)
        val editButton = findViewById<FloatingActionButton>(R.id.editButton)
        val deleteButton = findViewById<FloatingActionButton>(R.id.deleteButton)
        val statsButton = findViewById<FloatingActionButton>(R.id.statsButton)
        val saveFileButton = findViewById<FloatingActionButton>(R.id.saveFileButton)
        val readFileButton = findViewById<FloatingActionButton>(R.id.readFileButton)

        addButton.setOnClickListener{
            val intent = Intent(this, AddScreenActivity::class.java)
            startActivity(intent)
        }

        viewButton.setOnClickListener{
            val intent = Intent(this, AllCoursesActivity::class.java)
            startActivity(intent)
        }

        editButton.setOnClickListener{
            openEditDialog()
        }

        deleteButton.setOnClickListener{
            openDeleteDialog()
        }

        statsButton.setOnClickListener{
            val intent = Intent(this, StatsScreenActivity::class.java)
            startActivity(intent)
        }

        saveFileButton.setOnClickListener {
            val banco = Banco(this)
            val cursoDAO = CursoDAO(banco)

            val fileName = "database.txt"

            val fileOutputStream = openFileOutput(fileName, MODE_PRIVATE)

            val allDataText = cursoDAO.getAllDataString()
            fileOutputStream.write(allDataText.toByteArray())
            fileOutputStream.close()

            Toast.makeText(this, "Dados salvos em arquivo de texto com sucesso.", Toast.LENGTH_SHORT).show()
        }

        readFileButton.setOnClickListener{
            val intent = Intent(this, FileDataScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openEditDialog() {
        val editText = EditText(this)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Pesquisar curso")
            .setMessage("Por favor, insira o código do curso:")
            .setView(editText)
            .setPositiveButton("OK") { dialogInterface, _ ->
                val inputText = editText.text.toString()
                codigoCurso = inputText.toInt()
                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancelar") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()

        dialog.setOnDismissListener {
            val intent = Intent(this, AddScreenActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("codigo", codigoCurso!!)
            intent.putExtras(bundle)
            codigoCurso = null
            startActivity(intent)
        }


        dialog.show()
    }

    private fun openDeleteDialog() {
        val editText = EditText(this)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Remover curso")
            .setMessage("Por favor, insira o código do curso:")
            .setView(editText)
            .setPositiveButton("OK") { dialogInterface, _ ->
                val inputText = editText.text.toString()
                codigoCurso = inputText.toInt()
                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancelar") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()

        dialog.setOnDismissListener {
            val banco = Banco(this)
            val cursoDAO = CursoDAO(banco)
            cursoDAO.removeCurso(codigoCurso!!)
            Toast.makeText(this, "Curso ${codigoCurso} removido", Toast.LENGTH_SHORT).show()
            codigoCurso = null
        }


        dialog.show()
    }
}