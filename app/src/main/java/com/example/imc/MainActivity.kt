package com.example.imc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextPeso = findViewById<EditText>(R.id.edit_text_peso)
        val editTextAltura = findViewById<EditText>(R.id.edit_text_altura)
        val buttonCalcular = findViewById<Button>(R.id.btn_calcular)
        val textViewResultado = findViewById<TextView>(R.id.text_imc)

        buttonCalcular.setOnClickListener {
            val peso = editTextPeso.text.toString().toDoubleOrNull()
            val altura = editTextAltura.text.toString().toDoubleOrNull()
            if (peso != null && altura != null && altura > 0) {
                val imc = calcularIMC(peso, altura)
                val resultado = when {
                    imc < 18.5 -> "Abaixo do peso"
                    imc in 18.5..24.9 -> "Peso normal"
                    imc in 25.0..29.9 -> "Sobrepeso"
                    imc in 30.0..34.9 -> "Obesidade grau I"
                    imc in 35.0..39.9 -> "Obesidade grau II"
                    else -> "Obesidade grau III"
                }
                textViewResultado.text = "IMC: %.2f ($resultado)".format(imc)
            } else {
                textViewResultado.text = "Por favor, insira valores válidos."
            }
        }
    }
    private fun calcularIMC(peso: Double, altura: Double): Double {
        return peso / (altura * altura)
    }
}