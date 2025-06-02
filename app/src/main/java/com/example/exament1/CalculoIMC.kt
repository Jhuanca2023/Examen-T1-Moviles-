package com.example.exament1

data class CalculoIMC(
    val peso: Double,
    val altura: Double,
    val imc: Double,
    val categoria: String
) {
    companion object {
        fun calcularCategoria(imc: Double): String {
            return when {
                imc < 18.5 -> "Bajo peso"
                imc < 24.9 -> "Peso normal"
                imc < 29.9 -> "Sobrepeso"
                else -> "Obesidad"
            }
        }

        fun obtenerRecomendaciones(categoria: String): String {
            return when (categoria) {
                "Bajo peso" -> "• Consulta con un nutricionista\n• Aumenta la ingesta de calorías saludables\n• Realiza ejercicios de fortalecimiento"
                "Peso normal" -> "• Mantén una dieta balanceada\n• Realiza ejercicio regular\n• Continúa con tus buenos hábitos"
                "Sobrepeso" -> "• Reduce la ingesta de calorías\n• Aumenta la actividad física\n• Evita alimentos procesados"
                else -> "• Consulta con un profesional de la salud\n• Sigue un plan de alimentación supervisado\n• Realiza actividad física moderada"
            }
        }

        fun obtenerColorCategoria(categoria: String): Int {
            return when (categoria) {
                "Bajo peso" -> android.graphics.Color.BLUE
                "Peso normal" -> android.graphics.Color.GREEN
                "Sobrepeso" -> android.graphics.Color.YELLOW
                else -> android.graphics.Color.RED
            }
        }
    }
} 