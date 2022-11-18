package com.example.usandosharedpreference

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var botaoSalvar: Button? = null
    var editNome: EditText? = null
    var textResultado: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editNome = findViewById(R.id.editNome)
        botaoSalvar = findViewById(R.id.botaoSalvar)
        textResultado = findViewById(R.id.textResultado)
        botaoSalvar.setOnClickListener(View.OnClickListener {
            val preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0)
            val editor = preferences.edit()
            if (editNome.getText().toString() == "") {
                Toast.makeText(applicationContext, "Preencha seu nome", Toast.LENGTH_LONG).show()
            } else {
                val nome = editNome.getText().toString()
                editor.putString("nome", nome)
                editor.commit()
                textResultado.setText("Olá, $nome")
            }
        })
        val preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0)
        if (preferences.contains("nome")) {
            val nome = preferences.getString("nome", "\"Olá, usuário não definido\"")
            textResultado.setText("olá $nome")
        } else {
            textResultado.setText("Olá, usuário não definido")
        }
    }

    companion object {
        private const val ARQUIVO_PREFERENCIA = "ArquivoPreferencia"
    }
}