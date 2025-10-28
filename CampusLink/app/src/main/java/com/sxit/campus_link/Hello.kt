package com.sxit.campus_link

internal object HelloWorldKt {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello, World!")

        val result = add(1, 2)
        println("Sum: $result")
    }

    fun add(num1: Int, num2: Int): Int {
        return num1 + num2
    }
}
