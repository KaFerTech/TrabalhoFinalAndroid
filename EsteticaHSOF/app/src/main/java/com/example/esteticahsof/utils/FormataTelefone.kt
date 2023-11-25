package com.example.esteticahsof.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class FormataTelefone(private val editText: EditText) : TextWatcher {

    private var isFormatting: Boolean = false
    private var deletedDigit: Boolean = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        deletedDigit = count > after
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        if (isFormatting) {
            return
        }

        isFormatting = true

        val digits = editable.toString().replace("[^\\d]".toRegex(), "")
        val formatted = StringBuilder()

        if (digits.length == 1) {
            formatted.append("(${digits}")
        } else if (digits.length == 2) {
            formatted.append("(${digits}")
        } else if (digits.length in 3..6) {
            formatted.append("(${digits.substring(0,2)}) ${digits.substring(2,digits.length)}")
        } else if (digits.length in 7..10) {
            formatted.append("(${digits.substring(0,2)}) ${digits.substring(2,6)}-${digits.substring(6,digits.length)}")
        } else if (digits.length == 11) {
            formatted.append("(${digits.substring(0,2)}) ${digits.substring(2,3)} ${digits.substring(3,7)}-${digits.substring(7,digits.length)}")
        } else if (digits.length > 11) {
            formatted.append("(${digits.substring(0,2)}) ${digits.substring(2,3)} ${digits.substring(3,7)}-${digits.substring(7,11)}")
        }

        editText.setText(formatted.toString())
        editText.setSelection(formatted.length.coerceAtMost(16))

        isFormatting = false
    }
}