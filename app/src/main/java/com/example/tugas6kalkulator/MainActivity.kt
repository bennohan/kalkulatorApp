package com.example.tugas6kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.tugas6kalkulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var strNumber = StringBuilder()

    private lateinit var numberButtons : Array<Button>
    private lateinit var operationButtons : List<Button>


    private var operator : Operator = Operator.NONE
    private var isOperatorClicked : Boolean = false
    private var operand1 : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        numberButtons = arrayOf(binding.oneBtn,binding.twoBtn,binding.threeBtn,binding.fourBtn,binding.fiveBtn,binding.sixBtn,binding.sevenBtn,binding.eightBtn,binding.nineBtn,binding.zeroBtn)
        operationButtons = listOf(binding.divBtn,binding.mulBtn,binding.plusBtn,binding.minBtn)
        for (i in numberButtons) { i.setOnClickListener { buttonClicked(i) } }
        for (i in operationButtons) { i.setOnClickListener { operationClicked(i) } }

        binding.equalsBtn.setOnClickListener { buttonEqualClicked() }
    }

    private fun buttonEqualClicked() {
        val operand2 = strNumber.toString().toInt()
        val result : Int = when(operator) {
            Operator.MUL -> operand1 * operand2
            Operator.DIV -> operand1 / operand2
            Operator.ADD -> operand1 + operand2
            Operator.SUB -> operand1 - operand2
            else -> 0
        }
        strNumber.clear()
        strNumber.append(result.toString())
        binding.resultTv.text = strNumber
        isOperatorClicked = true
    }

    private fun operationClicked(btn: Button) {
        operator = when (btn.text) {
            "X" -> {
                Log.d("operator yang dipilih", "perkalian" )
                Operator.MUL
            }
            "/" -> {
                Log.d("operator yang dipilih", "pembagian" )
                Operator.DIV
            }

            "+" -> {
                Log.d("operator yang dipilih", "pertambhan" )
                Operator.ADD
            }
            "-" ->{
                Log.d("operator yang dipilih", "pengurangan" )
                Operator.SUB
            }
            else ->{
                Log.d("operator yang dipilih", "nothing" )
                Operator.NONE
            }
        }

        isOperatorClicked = true
    }

    private fun buttonClicked(btn: Button) {
        if (isOperatorClicked) {
            operand1 = strNumber.toString().toInt()
            strNumber.clear()
            isOperatorClicked = false
        }
        strNumber.append(btn.text)
        binding.inputTv.text = strNumber }


}

enum class Operator {MUL, DIV, ADD, SUB, NONE}



