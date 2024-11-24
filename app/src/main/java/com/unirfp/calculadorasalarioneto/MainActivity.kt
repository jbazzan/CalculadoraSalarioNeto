package com.unirfp.calculadorasalarioneto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Variables privadas para recoger elementos visuales, inicializacion tardia (Lazy)
    private lateinit var btnCalcular: Button
    private lateinit var inputSalarioBruto: EditText

    // Atributos necesarios para la lógica de nuestros componentes

    // Creación de un companion object que es accesible desde todas las activities
    companion object {
        const val SALARIO_BRUTO = "salario_bruto"
        const val IRPF = "irpf"
        const val SALARIO_NETO = "salario_neto"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Iniciar componentes visuales
        initComponents()

        // Iniciar los listeners de los eventos
        initListeners()

        // Configuraciones visuales de los componentes
        initUI()


    }

    private fun initUI() {

    }

    private fun initListeners() {
        this.btnCalcular.setOnClickListener {
            val salarioBruto = inputSalarioBruto.text.toString().toDoubleOrNull() ?: 0.0
            val irpf = calcularIrpf(salarioBruto)
            val salarioNeto = calcularSalarioNeto(salarioBruto, irpf)

            navigateToResult(salarioBruto, irpf, salarioNeto)
        }
    }

    private fun navigateToResult(salarioBruto: Double, irpf: Double, salarioNeto: Double) {
        // Crear el intent para iniciar ResultadoActivity
        val intent = Intent(this, ResultadoSalarioNeto::class.java)

        // Pasar datos relevantes a la siguiente actividad
        intent.putExtra(SALARIO_BRUTO, salarioBruto)
        intent.putExtra(IRPF, irpf)
        intent.putExtra(SALARIO_NETO, salarioNeto)

        // Iniciar la segunda actividad
        startActivity(intent)
    }

    private fun calcularSalarioNeto(salarioBruto: Double, irpf: Double): Double {
        val salarioNeto = salarioBruto * (1 - irpf)
        Log.i(
            "calcularSalarioNeto",
            "Salario Bruto: $salarioBruto, IRPF: $irpf, Salario Neto: $salarioNeto"
        )
        return salarioNeto
    }

    private fun calcularIrpf(salarioBruto: Double): Double {
        val salarioBruto = inputSalarioBruto.text.toString().toDoubleOrNull() ?: 0.0

        return when {
            salarioBruto <= 0 -> 0.0 // No hay IRPF si el salario bruto no es válido
            salarioBruto <= 12450 -> 0.19 // 19%
            salarioBruto <= 20199 -> 0.24 // 24%
            salarioBruto <= 35199 -> 0.30 // 30%
            salarioBruto <= 59999 -> 0.37 // 37%
            salarioBruto <= 299999 -> 0.45 // 45%
            else -> 0.47 // 47%
        }
    }

private fun initComponents() {
    this.btnCalcular = findViewById(R.id.btnCalcular)
    this.inputSalarioBruto = findViewById(R.id.inputSalarioBruto)
}
}


