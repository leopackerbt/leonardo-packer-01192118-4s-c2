package com.example.prova4sc2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NaoEncontrado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nao_encontrado)

        val tvDescricao: TextView = findViewById(R.id.tv_resultado)
        val id1 = intent.getStringExtra("id1")
        val id2 = intent.getStringExtra("id2")

        tvDescricao.text = "Deu ruim... nenhum cachorro encontrado com os ids ${id1} e ${id2}: "
        // Professor, não entendi pq ta retornando null aqui sendo que no println tá retornando os numeros corretos
    }
}