package com.ozge.tipproject
import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etBaseAmount: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var tvTipPercentLabel: TextView
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView
    private lateinit var tvTipFeedback: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBaseAmount = findViewById(R.id.etBaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvTipPercentLabel = findViewById(R.id.tvTipPercentLabel)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)
        tvTipFeedback = findViewById(R.id.tvTipFeedback)

        // Default tip percent
        seekBarTip.progress = 15
        tvTipPercentLabel.text = "Tip: 15%"

        // Update values when SeekBar is changed
        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvTipPercentLabel.text = "Tip: $progress%"
                calculateTipAndTotal()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Optional
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Optional
            }
        })
    }

    // Function to calculate tip and total
    private fun calculateTipAndTotal() {
        val baseAmountString = etBaseAmount.text.toString()

        if (baseAmountString.isNotEmpty()) {
            val baseAmount = baseAmountString.toDouble()
            val tipPercent = seekBarTip.progress
            val tipAmount = baseAmount * tipPercent / 100
            val totalAmount = baseAmount + tipAmount

            // Update the TextViews with formatted values
            tvTipAmount.text = String.format("%.2f", tipAmount)
            tvTotalAmount.text = String.format("%.2f", totalAmount)

            // Update feedback text based on tip percentage
            tvTipFeedback.text = when {
                tipPercent > 20 -> {
                    tvTipFeedback.setTextColor(resources.getColor(android.R.color.holo_green_dark))
                    "Amazing"
                }
                tipPercent < 10 -> {
                    tvTipFeedback.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                    "Poor"
                }
                else -> {
                    tvTipFeedback.setTextColor(resources.getColor(android.R.color.holo_orange_dark))
                    "Good"
                }
            }
        } else {
            tvTipAmount.text = "0.00"
            tvTotalAmount.text = "0.00"
            tvTipFeedback.text = "Good"
        }
    }
}
