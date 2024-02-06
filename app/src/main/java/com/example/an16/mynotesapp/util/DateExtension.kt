package com.example.an16.mynotesapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val SIMPLE_DATE_PATTERN = "dd/MM/yyyy"

fun Date.getSimpleDate(): String {
    val dateFormat = SimpleDateFormat(SIMPLE_DATE_PATTERN, Locale.ROOT)
    return dateFormat.format(this)
}