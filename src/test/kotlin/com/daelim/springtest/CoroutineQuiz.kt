package com.daelim.springtest

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.datafaker.Faker
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import kotlin.random.Random

@SpringBootTest
class CoroutineQuiz {
//    @Test
//    fun testDataFaker(){
//        val faker = Faker(Locale.KOREA)
//        println(faker.name().name())
//
//    }

    /*
        100명의 가상 사용자 이름을 로그로 출력하세요
    */
    @Test
    fun quiz1Test() {
        val faker = Faker(Locale.KOREA)
        runBlocking {
        List(100) {
            launch {
                delay(1000)

                println(faker.name().name())
            }
        }
    }
}


    /*
        50명의 가상 사용자의 이름, 이메일, 주소를 로그 출력하세요
     */
    @Test
    fun quiz2Test() {
        val faker = Faker(Locale.KOREA)
        runBlocking {
            List(50) {
                launch {
                    delay(1000)
                    println("${faker.name().fullName()} / ${faker.internet().emailAddress()} / ${faker.address().fullAddress()}")
                }
            }
        }
    }
    /*
        30명의 가상 사용자의 이름과 나이 생성를 데이터클래스로 생성하고, 어린 나이 순으로 정렬 후 출력하세요
     */
    data class User(val name: String, val age: Int)

    @Test
    fun generateSortAndPrintUserAges() {
        runBlocking {
            val faker = Faker()
            val users = List(30) {
                async {
                    User(
                        name = faker.name().fullName(),
                        age = faker.number().numberBetween(18, 60)
                    )
                }
            }
            users.map { it.await() }.sortedBy { it.age }.map {
                println("${it.name}, ${it.age}")
            }
        }
    }
}