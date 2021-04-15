package com.example.prova4sc2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import retrofit2.Callback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var preferencias: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun comprar(view: View) {
        preferencias = getSharedPreferences("id", Context.MODE_PRIVATE)
        var find1:Boolean = false
        var find2:Boolean = false

        val tela2 = Intent(this, NaoEncontrado::class.java)
        val tela3 = Intent(this, Encontrado::class.java)

        val apiCachorros = ConexaoApiCachorros.criar()

        val id1:EditText = findViewById(R.id.et_id1)
        val id2:EditText = findViewById(R.id.et_id1)

        val idOne = id1.text.toString().toInt()
        val idTwo = id2.text.toString().toInt()
        println(idOne)
        println(idTwo)

        apiCachorros.get(idOne).enqueue(object : Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                val cachorro = response.body()
                val tvDescricao: TextView = findViewById(R.id.tv_descricao)

                if (cachorro != null) {
                    tvDescricao.text = "Raça: ${cachorro.raca}"
                } else {
                    find1 = true
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }
        })

        apiCachorros.get(idTwo).enqueue(object : Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                val cachorro = response.body()
                val tvDescricao: TextView = findViewById(R.id.tv_descricao)

                if (cachorro != null) {
                    tvDescricao.text = "Raça: ${cachorro.raca}"
                } else {
                    find2 = true
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }
        })

        tela2.putExtra("id1", idOne)
        tela2.putExtra("id2", idTwo)

        startActivity(tela2)
    }

}