/*
*
*   Made by: Armando Arredondo Valle  | A01424709
*   Date: 22/08/2023
*
* */

package mx.tec.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import mx.tec.calculadora.databinding.ActivityMainBinding
import android.util.Log


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    private var numero1: String = ""
    private var operador: String = ""

    companion object {
        private const val TAG = "CalculatorApp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = this
        val buttonIds = arrayOf(
            R.id.btnCero, R.id.btnUno, R.id.btnDos, R.id.btnTres, R.id.btnCuatro,
            R.id.btnCinco, R.id.btnSeis, R.id.btnSiete, R.id.btnOcho, R.id.btnNueve,
            R.id.btnMas, R.id.btnMenos, R.id.btnPor, R.id.btnEntre, R.id.btnIgual,
            R.id.btnReset
        )

        for (buttonId in buttonIds) {
            binding.root.findViewById<View>(buttonId).setOnClickListener(listener)
        }

    }

    private var firstInput: Int = 0
    private var result: Int = 0

    val numberButtonMap = mapOf(
        R.id.btnCero to "0",
        R.id.btnUno to "1",
        R.id.btnDos to "2",
        R.id.btnTres to "3",
        R.id.btnCuatro to "4",
        R.id.btnCinco to "5",
        R.id.btnSeis to "6",
        R.id.btnSiete to "7",
        R.id.btnOcho to "8",
        R.id.btnNueve to "9"
    )

    override fun onClick(v: View?) {
        when (v?.id) {
            // Number buttons
            in numberButtonMap.keys -> {
                val digit = numberButtonMap[v?.id]
                numero1 += digit
                updateResultText(numero1)
            }


            R.id.btnMas -> {
                firstInput = numero1.toInt() // Store the first input
                operador = "+"
                numero1 = ""
            }

            R.id.btnMenos -> {
                firstInput = numero1.toInt() // Store the first input
                operador = "-"
                numero1 = ""
            }

            R.id.btnPor -> {
                firstInput = numero1.toInt() // Store the first input
                operador = "*"
                numero1 = ""
            }

            R.id.btnEntre -> {
                firstInput = numero1.toInt() // Store the first input
                operador = "/"
                numero1 = ""
            }

            R.id.btnIgual -> {
                Log.d(TAG, "Equal button clicked")

                if (numero1.isNotEmpty() && operador.isNotEmpty()) {
                    val numero2 = numero1.toIntOrNull() ?: 0
                    Log.d(TAG, "numero1: $firstInput, operador: $operador, numero2: $numero2")

                    result = when (operador) {
                        "+" -> firstInput + numero2
                        "-" -> firstInput - numero2
                        "*" -> firstInput * numero2
                        "/" -> {
                            if (numero2 != 0) {
                                firstInput / numero2
                            } else {
                                Log.e(TAG, "Division by zero")
                                0
                            }
                        }

                        else -> {
                            Log.e(TAG, "Invalid operator: $operador")
                            0
                        }
                    }

                    binding.txtResultado.text = result.toString() // Display the result
                    numero1 = result.toString() // Update numero1 with the result
                    operador = "" // Clear the operator
                }
            }


            // Reset button
            R.id.btnReset -> {
                numero1 = ""
                operador = ""
                binding.txtResultado.text = ""
            }
        }
    }

    private fun updateResultText(text: String) {
        binding.txtResultado.text = text
    }
}