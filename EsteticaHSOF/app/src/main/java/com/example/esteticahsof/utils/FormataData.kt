package com.example.esteticahsof.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class FormataData(private val editText: EditText) : TextWatcher {
    private var isFormatting = false
    private val datePattern = "##/##/####"
    private val dateDivider = '/'

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        if (isFormatting) {
            return
        }

        isFormatting = true

        val digits = editable.toString().replace("[^\\d]".toRegex(), "")
        val formatted = StringBuilder()

        var datePatternIndex = 0
        for (i in 0 until digits.length) {
            if (datePatternIndex < datePattern.length) {
                if (datePattern[datePatternIndex] == dateDivider) {
                    formatted.append(dateDivider)
                    datePatternIndex++
                }
                formatted.append(digits[i])
                datePatternIndex++
            }
        }

        editText.setText(formatted.toString())
        editText.setSelection(formatted.length)

        isFormatting = false
    }
}