package com.example.prova1pdm

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val addButton = findViewById<FloatingActionButton>(R.id.addButton)
        val searchButton = findViewById<FloatingActionButton>(R.id.viewButton)
        val viewButton = findViewById<FloatingActionButton>(R.id.viewButton)

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

    }
}