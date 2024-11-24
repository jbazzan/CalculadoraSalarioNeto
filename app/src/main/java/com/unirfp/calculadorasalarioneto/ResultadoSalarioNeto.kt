package com.unirfp.calculadorasalarioneto

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoSalarioNeto : AppCompatActivity() {

    // Variables privadas para recoger elementos visuales, inicializacion tardia (Lazy)
    private lateinit var btnVolver: Button
    private lateinit var txtSalarioBrutoResultado: TextView
    private lateinit var txtIrpf: TextView
    private lateinit var txtSalarioNeto: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_salario_neto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener datos del intent
        val salarioBruto = intent.extras?.getDouble("salario_bruto") ?: -1.0
        val irpf = intent.extras?.getDouble("irpf") ?: -1.0
        val salarioNeto = intent.extras?.getDouble("salario_neto") ?: -1.0

        // Mostrar los valores
//        txtSalarioBrutoResultado.text = "Salario Bruto: €${String.format("%.2f", salarioBruto)}"
//        txtIrpf.text = "IRPF: ${String.format("%.2f", irpf * 100)}%"
//        txtSalarioNeto.text = "Salario Neto: €${String.format("%.2f", salarioNeto)}"


        initComponents()

        initListeners()

        initUI(salarioBruto, irpf, salarioNeto)

    }

    private fun initUI(salarioBruto: Double, irpf: Double, salarioNeto: Double) {
        txtSalarioBrutoResultado.text = "Salario Bruto: €${String.format("%.2f", salarioBruto)}"
        txtIrpf.text = "IRPF: ${String.format("%.2f", irpf * 100)}%"
        txtSalarioNeto.text = "Salario Neto: €${String.format("%.2f", salarioNeto)}"
    }

    private fun initListeners() {
        // Configurar el botón "Volver"
        btnVolver.setOnClickListener {
            finish() // Cierra esta actividad y regresa a la anterior
        }
    }

    private fun initComponents() {
        this.btnVolver = findViewById(R.id.btnVolver)
        this.txtSalarioBrutoResultado = findViewById(R.id.txtSalarioBrutoResultado)
        this.txtIrpf = findViewById(R.id.txtIrpf)
        this.txtSalarioNeto = findViewById(R.id.txtSalarioNeto)
    }
}
