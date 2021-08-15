package com.example.tiptime

import java.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    private fun calculateTip() {
        binding.tipResult.text = ""
        val amount = binding.costOfServiceEditText.text.toString().toDoubleOrNull() ?: return
        val tipPercent = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_eighteen_percent -> 0.18
            R.id.option_twenty_percent -> 0.20
            else -> 0.15
        }

        val calculatedTip = amount * tipPercent
        val finalTip = if (binding.roundUpSwitch.isChecked)  ceil(calculatedTip) else calculatedTip
        val formatedTip = NumberFormat.getCurrencyInstance().format(finalTip)

        binding.tipResult.text = getString(R.string.tip_amount, formatedTip)
    }
}