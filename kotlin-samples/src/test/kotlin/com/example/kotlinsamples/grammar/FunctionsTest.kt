package com.example.kotlinsamples.grammar

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FunctionsTest {

    @DisplayName("기본 인자 함수 테스트")
    @Test
    fun default_argument_func_test() {
        // argument
        this.castFireBallDefaultArgs(3)
        // default argument
        this.castFireBallDefaultArgs()
    }

    private fun castFireBallDefaultArgs(numFireBall: Int = 2) {
        println("FIRE BALL $numFireBall")
    }

    @DisplayName("단일 표현식 함수 테스트")
    @Test
    fun single_expression_func_test() {
        this.castFireBallSingleExp(3)
        this.castFireBallSingleExp2()
    }

    private fun castFireBallSingleExp(numFireBall: Int = 2) = println("FIRE BALL $numFireBall")
    private fun castFireBallSingleExp2(healthPoint: Int = 50) =
        when (healthPoint) {
            100 -> "The Best Status"
            in 70 .. 90 -> "Normal Status"
            in 50 .. 69 -> if (healthPoint % 2 == 0) {
                "Even"
            } else {
                "Odd"
            }
            else -> "The Worst Status"
        }

    /**
     * Unit: 데이터를 Return 하지 않는 함수, return 이 없는 함수를 의미
     *   아무것도 return 하지 않는 함수의 return 타입을 의미
     *   Generic 표현이 가능(제네릭 함수는 반드시 반환 타입을 나타내야 한다.)
     * Void: 반환 타입이 없으니 생략, Unit 과 다른 의미
     *   Generic 표현이 불가능
     */
    @DisplayName("Unit 함수 테스트")
    @Test
    fun unit_func_test() {
        this.castFireBallSingleExp(3)
        this.castFireBallSingleExp2()
    }

    @DisplayName("지명 함수 인자 테스트")
    @Test
    fun named_function_argument_test() {
        this.namedFunctionArgument("James", 22, 175.6)
        this.namedFunctionArgument(name = "James", age = 22, height = 175.6)
        this.namedFunctionArgument(age = 22, height = 175.6, name = "James")
    }

    private fun namedFunctionArgument(name: String, age: Int, height: Double) = println("$name - $age, $height")

    /**
     * JAVA 와 KOTLIN 의 예약어 등이 다르기에 백틱(`) 을 이용하여 자바의 메소드를 호출할 수 있다.
     */
    @DisplayName("백틱(`) 함수 테스트")
    @Test
    fun backtick_function_test() {
        this.`back tick functions test code`("James", 22, 175.6)
        this.`back tick functions test code`(name = "James", age = 22, height = 175.6)
        this.`back tick functions test code`(age = 22, height = 175.6, name = "James")
    }

    private fun `back tick functions test code`(name: String, age: Int, height: Double) = println("$name - $age, $height")
}