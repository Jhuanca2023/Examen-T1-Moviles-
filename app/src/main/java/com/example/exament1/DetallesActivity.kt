package com.example.exament1

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exament1.databinding.ActivityDetallesBinding

class DetallesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val peso = intent.getDoubleExtra("peso", 0.0)
        val altura = intent.getDoubleExtra("altura", 0.0)
        val imc = intent.getDoubleExtra("imc", 0.0)
        val categoria = intent.getStringExtra("categoria") ?: ""

        mostrarInformacion(peso, altura, imc, categoria)
        mostrarRecomendaciones(categoria)
        mostrarGraficoIMC(imc)
    }

    private fun mostrarInformacion(peso: Double, altura: Double, imc: Double, categoria: String) {
        binding.tvPesoDetalle.text = String.format("Peso: %.1f kg", peso)
        binding.tvAlturaDetalle.text = String.format("Altura: %.2f m", altura)
        binding.tvIMCDetalle.text = String.format("IMC: %.1f", imc)
        binding.tvCategoriaDetalle.text = "Categoría: $categoria"
        binding.tvCategoriaDetalle.setTextColor(CalculoIMC.obtenerColorCategoria(categoria))
    }

    private fun mostrarRecomendaciones(categoria: String) {
        binding.tvRecomendaciones.text = CalculoIMC.obtenerRecomendaciones(categoria)
    }

    private fun mostrarGraficoIMC(imc: Double) {
        val bitmap = Bitmap.createBitmap(800, 100, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()


        canvas.drawColor(Color.WHITE)


        paint.color = Color.LTGRAY
        paint.strokeWidth = 2f
        canvas.drawLine(0f, 50f, 800f, 50f, paint)

        val marcas = listOf(
            Pair(0f, "0"),
            Pair(185f, "18.5"),
            Pair(250f, "25"),
            Pair(300f, "30"),
            Pair(800f, "40+")
        )

        paint.textSize = 30f
        for ((x, texto) in marcas) {
            canvas.drawLine(x, 45f, x, 55f, paint)
            canvas.drawText(texto, x - 15f, 80f, paint)
        }

        val posX = (imc * 20).toFloat().coerceIn(0f, 800f)
        paint.color = Color.RED
        paint.strokeWidth = 4f
        canvas.drawLine(posX, 0f, posX, 100f, paint)

        binding.ivRangoIMC.setImageBitmap(bitmap)
    }
} 