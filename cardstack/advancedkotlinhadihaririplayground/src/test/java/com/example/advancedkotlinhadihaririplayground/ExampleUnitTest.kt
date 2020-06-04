package com.example.advancedkotlinhadihaririplayground

import com.example.advancedkotlinhadihaririplayground.deepdivefunctions.andThen
import com.example.advancedkotlinhadihaririplayground.deepdivefunctions.compose
import com.example.advancedkotlinhadihaririplayground.deepdivefunctions.curried
import com.example.advancedkotlinhadihaririplayground.deepdivefunctions.forwardCompose
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val add5 = { i: Int -> i + 5 }
    private val multiplyBy2 = { i: Int -> i * 2 }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testCompose() {
        val mutliplyBy2andAdd5 = add5 compose multiplyBy2
        // { p1: P1 -> this(f(p1)) }
        // first f(p1) -> multiply by 2 ie. 2*2
        // second this(4) -> add 5 + f(p1) = 5 + 4  = 9
        assertEquals(mutliplyBy2andAdd5(2), 9)
    }

    @Test
    fun testForwardCompose() {
        val add5andMutliplyBy2 = add5 forwardCompose multiplyBy2
        // { p1: P1 -> f(this(p1)) }
        // first this(p1) ie. 5 + (p1 = 2) = 7
        // second f(IP = 7) ie. multiply 2 * ip=7 = 14
        assertEquals(add5andMutliplyBy2(2), 14)
    }

    @Test
    fun testAndThen() {
        val add5andMutliplyBy2 = add5 andThen multiplyBy2
        // same as forwardCompose
        assertEquals(add5andMutliplyBy2(2), 14)
    }


    // currying test
    @Test
    fun testCurrying() {
        val sum2Ints = { x: Int, y: Int -> x + y }
        val curried = sum2Ints.curried() // This creates another fun for me that only takes 1 param
        // so, sum2Ints.curried will chop the sum2Ints into multiple func that take single parame
        // native to Haske;
        assertEquals(curried(2)(4), 6)
        val add5 = curried(5)
        assertEquals(add5(7), 12)
    }

    @Test
    fun testUncurrying() {
        val sum2Ints: (Int, Int) -> Int = { x, y -> x + y }
        val curried: (Int)->(Int)-> Int = sum2Ints.curried()
        assertEquals(curried(2)(4), 6)
    }
}