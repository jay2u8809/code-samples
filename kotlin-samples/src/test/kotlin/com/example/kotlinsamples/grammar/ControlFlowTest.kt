package com.example.kotlinsamples.grammar

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ControlFlowTest {

    @DisplayName("Conditional Expression 1")
    @Test
    fun conditional_expression_1_test() {
        val playerName = "player1"
        val healthPoint = 89
        val healthStatus = if (healthPoint == 100) {
            "The Best Status"
        } else {
            "The Worst Status"
        }
        println(playerName + " " + healthStatus)
    }

    @DisplayName("Conditional Expression 2")
    @Test
    fun conditional_expression_2_test() {
        val playerName = "player1"
        val healthPoint = 89
        val healthStatus = if (healthPoint >= 80) "The Best Status" else "The Worst Status"
        println(playerName + " " + healthStatus)
    }

    @DisplayName("Range")
    @Test
    fun range_test() {
        val playerName = "player1"
        val healthPoint = 69
        val healthStatus = if (healthPoint in 80..100) {
            "The Best Status"
        } else if (healthPoint in 50 .. 79) {
            "Normal Status"
        } else {
            "The Worst Status"
        }
            println(playerName + " " + healthStatus)
    }

    @DisplayName("Range2")
    @Test
    fun range_2_test() {
        println(2 in 1..3)  // 1 <= 2 <= 3, true
        println((1..3).toList())    // [1, 2, 3]
        println(1 in 3 downTo 1)    // true
        println(4 in 3 downTo 1)    // false
        println(1 in 1 until 3)     // 1 <= x < 3, true
        println(3 in 1 until 3)     // false
        println(2 !in 1 .. 3)       // false
        println('x' in 'a' .. 'z')  // true
    }

    @DisplayName("When")
    @Test
    fun when_test() {
        val race = "gnome"
        val faction = when (race) {
            "dwarf" -> "Keepers of the Mines"
            "gnome" -> "Keepers of the Mines"
            "orc" -> "Free People of the Rolling Hills"
            "Human" -> "Free People of the Rolling Hills"
            else -> ""
        }

        val healthPoint = 69
        val healthStatus = when (healthPoint) {
            100 -> "The Best Status"
            in 70 .. 90 -> "Normal Status"
            in 50 .. 69 -> if (healthPoint % 2 == 0) {
                "Even"
            } else {
                "Odd"
            }
            else -> "The Worst Status"
        }
        println(faction + ", " + healthStatus)
    }

    @DisplayName("String Template")
    @Test
    fun string_template_test() {
        val playerName = "player1"
        val healthPoint = 69
        val healthStatus = if (healthPoint in 80..100) {
            "The Best Status"
        } else if (healthPoint in 50 .. 79) {
            "Normal Status"
        } else {
            "The Worst Status"
        }
        println("$playerName: $healthPoint, $healthStatus")
    }
}