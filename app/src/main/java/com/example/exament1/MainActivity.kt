package com.example.exament1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.exament1.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var calculoIMC: CalculoIMC? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnCalcular.setOnClickListener { calcularIMC() }
        binding.btnLimpiar.setOnClickListener { limpiarCampos() }
        binding.btnMostrarDetalles.setOnClickListener { mostrarDetalles() }
    }

    private fun calcularIMC() {
        val pesoStr = binding.etPeso.text.toString()
        val alturaStr = binding.etAltura.text.toString()

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingrese todos los datos requeridos")
            return
        }

        try {
            val peso = pesoStr.toDouble()
            var altura = alturaStr.toDouble()

            if (altura > 3) {
                altura /= 100
            }

            val imc = peso / altura.pow(2)
            val categoria = CalculoIMC.calcularCategoria(imc)
            
            calculoIMC = CalculoIMC(peso, altura, imc, categoria)
            
            binding.tvResultadoIMC.text = String.format("Tu IMC es: %.1f", imc)
            binding.tvCategoria.text = "Categoría: $categoria"
            binding.tvCategoria.setTextColor(CalculoIMC.obtenerColorCategoria(categoria))
            binding.btnMostrarDetalles.isEnabled = true

        } catch (e: NumberFormatException) {
            mostrarAlerta("Error", "Por favor, ingrese valores numéricos válidos")
        }
    }

    private fun limpiarCampos() {
        binding.etPeso.text?.clear()
        binding.etAltura.text?.clear()
        binding.tvResultadoIMC.text = ""
        binding.tvCategoria.text = ""
        binding.btnMostrarDetalles.isEnabled = false
        calculoIMC = null
    }

    private fun mostrarDetalles() {
        calculoIMC?.let {
            val intent = Intent(this, DetallesActivity::class.java).apply {
                putExtra("peso", it.peso)
                putExtra("altura", it.altura)
                putExtra("imc", it.imc)
                putExtra("categoria", it.categoria)
            }
            startActivity(intent)
        }
    }

    private fun mostrarAlerta(titulo: String, mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .show()
    }
}