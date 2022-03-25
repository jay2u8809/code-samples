package com.example.kotlinsamples.grammar

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.math.cos

/**
 * 익명 함수 (Anonymous Functions) 는 암시적으로 함수 정의의 마지막 코드 결과를 반환한다.
 * 따라서 return 키워드를 사용하지 않고 생략한다.
 * (익명 함수에서 return 을 사용하면 컴파일러가 어떤 곳으로 제어가 복귀되어야하는지 알 수 없기 때문)
 */

class AnonymousFunctionsTest {

    @DisplayName("Count 함수의 익명 함수 처리")
    @Test
    fun count_func_test() {
        val lettersCnt = "Mississippi".count { letter -> letter == 's' }
        println("Letter Count : $lettersCnt")
    }

    @DisplayName("익명 함수 1")
    @Test
    fun anonymous_func_1_test() {
        println({
            val num = 30
            "My age: $num"
        }())
    }

    @DisplayName("함수 타입")
    @Test
    fun func_type_test() {
        val greetingFunctions: () -> String = {
            val num = 30
            "My age: $num"
        }
        println(greetingFunctions())
    }

    @DisplayName("함수 인자")
    @Test
    fun func_argument_test() {
        val greetingFunctions: (Int, String) -> String = { num, str ->
            "My age: $num, $str"
        }
        println(greetingFunctions(50, "key"))
    }

    /**
     * 익명 함수의 파라미터가 1개인 경우 it 키워드를 사용하여 매개변수로 사용할 수 있다.
     * 파라미터가 2개 이상의 경우에는 it 키워드를 사용할 수 없다.
     */
    @DisplayName("it 키워드")
    @Test
    fun func_argument_it_test() {
        val greetingFunctions: (Int) -> String = {
            "My age: $it"
        }
        println(greetingFunctions(50))
        println("Mississippi".count { it == 's' })
    }

    /**
     * 익명 함수가 변수의 값으로 지정될 경우, 해당 변수의 타입을 자동으로 추론하기에 지정할 필요가 없다.
     */
    @DisplayName("타입 추론")
    @Test
    fun type_inference_test() {
        // no param
        val greetingFunctions: () -> String = {
            val num = 30
            "My age: $num"
        }
        println(greetingFunctions())

        val greeting = {
            val num = 30
            "My age: $num"
        }
        println(greeting())

        // param
        val twoParamFunctions: (Int, String) -> Boolean = { num, str ->
            str.length % num == 0
        }
        println(twoParamFunctions(3, "Mississippi"))

        val twoParamValue = { num: Int, str: String ->
            str.length % num == 0
        }
        println(twoParamValue(3, "Mississippi"))
    }

    @DisplayName("함수를 파라미터로 받는 함수")
    @Test
    fun func_param_func_test() {
        val greetingFunctions = { num: Int, str: String ->
            "My age: $num, $str"
        }
        this.runSimulation("player1", greetingFunctions)
    }

    @DisplayName("단축 문법")
    @Test
    fun func_param_func_2_test() {
        this.runSimulation("player1") { num, str ->
            "My age: $num, $str"
        }
    }

    private fun runSimulation(name: String, func: (Int, String) -> String) {
        val numBuildingShuffledFirst = (1..10).shuffled().first()
        val numBuildingShuffledLast = (1..10).shuffled().last()
        val numBuildingRandom = (1..10).random()
        println("$numBuildingShuffledFirst, $numBuildingShuffledLast, $numBuildingRandom")
        println(func(numBuildingShuffledLast, name))
    }

    /**
     * 익명함수(람다)는 JVM 에서 객체로 생성되고 람다를 사용하는 모든 변수의 메모리 할당을 수행하므로 메모리에 부담이 될 수 있다.
     * 따라서 함수의 인자로서 사용되는 람다를 사용할 때 이러한 부담을 줄이기 위한 "인라인" 이라는 방법을 사용한다.
     * 인라인을 사용하면 람다의 객체 사용과 변수의 메모리 할당을 JVM 이 하지 않아도 된다.
     * 인라인이 키워드가 붙은 함수는 파라미터 람다가 객체로 생성되지 않고 람다의 몸체가 그대로 복사/붙여넣기 된다.
     * 재귀함수의 경우, 호출 된 수만큼 붙여넣기 때문에 컴파일러가 Loop 형태로 변경한다.
     */
    @DisplayName("인라인 함수")
    @Test
    fun inline_func_test() {
        this.runInlineSimulation("player1") { num, str ->
            "My age: $num, $str"
        }
    }
    private inline fun runInlineSimulation(name: String, func: (Int, String) -> String) {
        val numBuildingShuffledFirst = (1..10).shuffled().first()
        val numBuildingShuffledLast = (1..10).shuffled().last()
        val numBuildingRandom = (1..10).random()
        println("$numBuildingShuffledFirst, $numBuildingShuffledLast, $numBuildingRandom")
        println(func(numBuildingShuffledLast, name))
    }

    @DisplayName("함수 참조")
    @Test
    fun func_reference_test() {
        this.runRefSimulation("player1", ::printConstructionCost) { num, str ->
            "My age: $num, $str"
        }
    }
    private inline fun runRefSimulation(name: String, costPrinter: (Int) -> Unit, func: (Int, String) -> String) {
        val numBuildingShuffledFirst = (1..10).shuffled().first()
        val numBuildingShuffledLast = (1..10).shuffled().last()
        val numBuildingRandom = (1..10).random()
        println("$numBuildingShuffledFirst, $numBuildingShuffledLast, $numBuildingRandom")
        costPrinter(numBuildingShuffledLast)
        println(func(numBuildingShuffledLast, name))
    }
    fun printConstructionCost(numBuildings: Int) {
        val cost = 500
        println("Cost: ${cost * numBuildings}")
    }

    /**
     * Kotlin 이 함수는 클로져 closure 이다. (Closure: Close Over)
     *   클로져: 다른 함수(configureGreetingFunctions())에 포함된 함수(return 의 람다 함수)에서 자신을 포함하는 함수의 매개변수와 변수(numBuildings)를 사용할 수 있는 것
     */
    @DisplayName("반환 Return 타입으로서의 함수")
    @Test
    fun func_return_test() {
        this.runReturnSimulation()
    }
    private fun runReturnSimulation() {
        val greeting = this.configureGreetingFunctions()
        println(greeting("Kim"))
    }
    private fun configureGreetingFunctions(): (String) -> String {
        val structureType = "Hospital"
        var numBuildings = 5
        // TODO 인자(Kim) 가 어떻게 playerName 으로 전달되는가?
        return { playerName: String ->
            val currentYear = 2022
            numBuildings += 1
            println("add $structureType, $numBuildings")
            "Welcome to SimVillage $playerName!, $currentYear"

        }
    }
}