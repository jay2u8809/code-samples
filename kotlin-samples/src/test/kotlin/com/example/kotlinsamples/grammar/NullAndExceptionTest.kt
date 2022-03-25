package com.example.kotlinsamples.grammar

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class NullAndExceptionTest {

    @DisplayName("Null 처리 방법: 안전 연산 호출자")
    @Test
    fun null_type_method_1_test() {
        var beverage = this.generate_test("test")?.uppercase()
        println(beverage)
        beverage = this.generate_test(null)?.uppercase()
        println(beverage)
    }
    @DisplayName("Null 처리 방법: 안전 연산 호출자 let 사용")
    @Test
    fun null_type_method_1_let_test() {
        var beverage = this.generate_test("test")?.let {
            println("$it")
            if (it.isNullOrEmpty()) {
                "Beer"
            } else {
                it.uppercase()
            }
        }
        println(beverage)
        beverage = this.generate_test(null)?.let {
            println("$it")
            if (it.isNullOrEmpty()) {
                "Beer"
            } else {
                it.uppercase()
            }
        }
        println(beverage)
    }

    @DisplayName("Null 처리 방법: !! non null 단언 연산자")
    @Test
    fun null_type_method_2_test() {
        var beverage = this.generate_test("test")!!.uppercase()
        println(beverage)
        // null pointer exception
//        beverage = this.generate_test(null)!!.uppercase()
//        println(beverage)
    }
    private fun generate_test(str: String?): String? {
        return str
    }

    @DisplayName("Null 처리 방법: ?: null coalescing operator, 엘비스 연산자")
    @Test
    fun null_type_method_3_test() {
        var str: String? = null
        var result = str ?: "beer"
        println("$result")  // beer

        str = "test"
        result = str ?: "beer"
        println("$result")  // test

        str = "test"
        result = str.let {
            it.uppercase()
        } ?: "beer"
        println("$result")  // TEST
    }

    @DisplayName("Null 처리 방법: 전제 조건 함수, precondition function")
    @Test
    fun null_type_method_4_test() {
        var num: String? = "Not Empty"
        // 첫 번째 인자 == null -> throw IllegalStateException, 첫 번째 인자 != null -> return 첫 번째 인자
        checkNotNull(num) {
            "Not Null"
        }
        // 첫 번째 인자 == false -> throw IllegalStateException
        require(num != null)
        // 첫 번째 인자 == null -> throw IllegalStateException, 첫 번째 인자 != null -> return 첫 번째 인자
        requireNotNull(num)
        // 첫 번째 인자 == null -> throw IllegalStateException, 첫 번째 인자 != null -> return 첫 번째 인자
//        error(num)
        // 첫 번째 인자 == null -> throw AssertionError and compiler 의 assertion flag 가 활성화 된다.
        assert(num != null)
    }
}