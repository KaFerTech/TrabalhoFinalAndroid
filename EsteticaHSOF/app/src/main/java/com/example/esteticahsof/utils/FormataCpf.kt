package com.example.esteticahsof.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class FormataCpf(private val editText: EditText) : TextWatcher {
    private var isFormatting = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        if (isFormatting) {
            return
        }

        isFormatting = true

        val digits = editable.toString().replace("[^\\d]".toRegex(), "")
        val formatted = StringBuilder()

        for (i in 0 until digits.length) {
            if (i == 3 || i == 6) {
                formatted.append(".")
            } else if (i == 9) {
                formatted.append("-")
            }
            formatted.append(digits[i])
        }

        editText.setText(formatted.toString())
        editText.setSelection(formatted.length)

        isFormatting = false
    }
}