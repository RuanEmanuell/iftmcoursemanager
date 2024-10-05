package com.example.prova1pdm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.add_screen)

        val banco = Banco(this)
        val cursoDAO = CursoDAO(banco)

        var codigoCurso : Int? = null;
        var isEditingMode = false;

        val bundle = intent.extras

        if(bundle != null){
            codigoCurso = bundle.getInt("codigo")
        }

        val saveButton = findViewById<FloatingActionButton>(R.id.saveButton)

        val codigoInput = findViewById<EditText>(R.id.cursoInput)
        val nomeInput = findViewById<EditText>(R.id.cursoInput2)
        val numeroDeAlunosInput = findViewById<EditText>(R.id.cursoInput3)
        val notaMecInput = findViewById<EditText>(R.id.cursoInput4)
        val areaInput = findViewById<EditText>(R.id.cursoInput5)

        if(codigoCurso != null){
            val cursoEncontrado : Curso? = cursoDAO.searchCurso(codigoCurso!!)

            if(cursoEncontrado != null){
                codigoInput.setText(cursoEncontrado.codigo.toString())
                nomeInput.setText(cursoEncontrado.nome)
                numeroDeAlunosInput.setText(cursoEncontrado.numeroDeAlunos.toString())
                notaMecInput.setText(cursoEncontrado.notaMec.toString())
                areaInput.setText(cursoEncontrado.area)

                isEditingMode = true;
            }
        }

        saveButton.setOnClickListener {
            if(codigoInput.text.isNotBlank() &&
                nomeInput.text.isNotBlank()  &&
                areaInput.text.isNotBlank()  &&
                notaMecInput.text.isNotBlank()  &&
                numeroDeAlunosInput.text.isNotBlank()){
                val codigo = codigoInput.text.toString()
                val nome = nomeInput.text.toString()
                val numeroDeAlunos = numeroDeAlunosInput.text.toString()
                val notaMec = notaMecInput.text.toString()
                val area = areaInput.text.toString()

                if (isEditingMode) {
                    cursoDAO.editCurso(
                        Curso(
                            codigo.toInt(),
                            nome,
                            numeroDeAlunos.toInt(),
                            notaMec.toFloat(),
                            area
                        )
                    )
                    Toast.makeText(this, "Curso atualizado", Toast.LENGTH_SHORT).show()
                } else {
                    val cursoEncontrado: Curso? = cursoDAO.searchCurso(codigo.toInt())
                    if (cursoEncontrado == null) {
                        cursoDAO.insertCurso(
                            Curso(
                                codigo.toInt(),
                                nome,
                                numeroDeAlunos.toInt(),
                                notaMec.toFloat(),
                                area
                            )
                        )
                        Toast.makeText(this, "Curso salvo", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Curso com código $codigo já existe!", Toast.LENGTH_SHORT).show()
                    }
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
        }else {
                Toast.makeText(this, "Insira todos os campos para salvar!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}