package com.example.credit.core

import java.util.regex.Pattern

enum class CardType() {

    UNKNOWN,
    VISA("^4[0-9]{12}(?:[0-9]{3}){0,2}$"),
    MASTERCARD("^(?:5[1-5]|2(?!2([01]|20)|7(2[1-9]|3))[2-7])\\d{14}$"),
    AMERICAN_EXPRESS("^3[47][0-9]{13}$");

    private var pattern: Pattern? = null

    private constructor(pattern: String) {
        this.pattern = Pattern.compile(pattern)
    }

    companion object {
        fun detect(cardNumber: String): CardType {
            for (cardType in CardType.values()) {
                return if (cardType.pattern?.matcher(cardNumber)!!.matches()) cardType else continue
            }
            return UNKNOWN
        }

        fun isValid(cardNumber: String): Boolean {
            var sum = 0
            var alternate = false
            for (i in cardNumber.length - 1 downTo 0) {
                var n = Integer.parseInt(cardNumber.substring(i, i + 1))
                if (alternate) {
                    n *= 2
                    if (n > 9) {
                        n = n % 10 + 1
                    }
                }
                sum += n
                alternate = !alternate
            }
            return sum % 10 == 0
        }
    }

}