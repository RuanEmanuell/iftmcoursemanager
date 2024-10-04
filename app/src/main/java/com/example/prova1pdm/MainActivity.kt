package com.example.prova1pdm

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var codigoCurso = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val addButton = findViewById<FloatingActionButton>(R.id.addButton)
        val searchButton = findViewById<FloatingActionButton>(R.id.viewButton)
        val viewButton = findViewById<FloatingActionButton>(R.id.viewButton)
        val editButton = findViewById<FloatingActionButton>(R.id.editButton)

        addButton.setOnClickListener{
            val intent = Intent(this, AddScreenActivity::class.java)
            startActivity(intent)
        }

        searchButton.setOnClickListener{

        }

        viewButton.setOnClickListener{
            val intent = Intent(this, AllCoursesActivity::class.java)
            startActivity(intent)
        }

        editButton.setOnClickListener{
            openDialog()
        }

    }

    private fun openDialog() {
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
            bundle.putInt("codigo", codigoCurso)
            intent.putExtras(bundle)
            startActivity(intent)
        }


        dialog.show()
    }
}