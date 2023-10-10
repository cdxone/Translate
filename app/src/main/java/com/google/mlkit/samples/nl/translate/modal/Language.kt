package com.google.mlkit.samples.nl.translate.modal

import java.util.Locale

class LanguageN(val code: String) : Comparable<LanguageN> {

    private val displayName: String
        get() = Locale(code).displayName

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }

        if (other !is LanguageN) {
            return false
        }

        val otherLang = other as LanguageN?
        return otherLang!!.code == code
    }

    override fun toString(): String {
        return "$code - $displayName"
    }

    override fun compareTo(other: LanguageN): Int {
        return this.displayName.compareTo(other.displayName)
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}