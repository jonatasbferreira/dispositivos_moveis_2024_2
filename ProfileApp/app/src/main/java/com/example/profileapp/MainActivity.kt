package com.example.profileapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando componentes
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val nameText = findViewById<TextView>(R.id.nameText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)
        val currentJobText = findViewById<TextView>(R.id.currentJobText)
        val experienceLayout = findViewById<LinearLayout>(R.id.experienceLayout)
        val projectLayout = findViewById<LinearLayout>(R.id.projectLayout) // Novo layout para projetos

        // Definindo informações de perfil
        nameText.text = "Ana Silva"
        descriptionText.text = "Desenvolvedora de software com 5 anos de experiência."
        currentJobText.text = "Emprego Atual: Engenheira de Software na TechX"

        // Lista de experiências
        val experiencias = listOf(
            "Analista de Sistemas - Empresa A",
            "Desenvolvedora Júnior - Empresa B",
            "Estagiária - Empresa C"
        )

        // Adicionando experiências dinamicamente
        for (experiencia in experiencias) {
            val textView = TextView(this)
            textView.text = experiencia
            textView.textSize = 16f
            experienceLayout.addView(textView)
        }

        val projetos = listOf(
            "Projeto 1: Aplicativo de Gestão Financeira - KillBills.",
            "Projeto 2: Plataforma de E-commerce - Jogo do Leãozinho.",
            "Projeto 3: Sistema Ambiental - QuixAlert!."
        )

        for (projeto in projetos) {
            val textView = TextView(this)
            textView.text = projeto
            textView.textSize = 16f
            projectLayout.addView(textView)
        }

        profileImage.setOnClickListener {
            Toast.makeText(this, "Foto de perfil de Ana Silva", Toast.LENGTH_SHORT).show()
        }
    }
}
