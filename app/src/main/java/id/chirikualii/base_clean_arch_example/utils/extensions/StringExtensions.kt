package id.chirikualii.base_clean_arch_example.utils.extensions

import android.util.Patterns

fun String.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String?.safeToString(): String {
    return this ?: ""
}