package com.example.advancedkotlinhadihaririplayground.deepdivefunctions


// Create a High Order fun
// takes an int and fun that takes and int and return int and return int
fun op(x: Int, op: (Int) -> Int): Int {
    return op(x)
}

fun op2(x: Int, y: Int, op: (Int, Int) -> Int): Int {
    return op(x, y)
}

fun main() {
    // op2
    val x = 4
    val y = 5
    println(
        op2(
            x = 4,
            y = 5,
            op = { x, y -> x * y })
    )

    //1
    println(
        op(
            x = 3,
            op = { it * it })
    )

    // Anonymous fun is a way in which I can create a fun, using a ````fun```` keyword and full body
    // But not giving it a name. So, fun that takes a param x and returns a result.
    //2
    op(
        2,
        fun(x): Int {
            println(x * x)
            return x * x
        })

    // 1 & 2 are doing exactly the same thing, difference is in 2 we are using anonymous fun!
    // Here we dont have a name of the function. We are using only the fun keyword.

    // There are couple of differences though.
    // (1)With AnonymousFun I can express the actual return type.
    // (2) With lamdba fun we dont specify the return type. Even if I am using implicit  it param, and if I want to do something explicit
    // like: op(3, { x -> x * x} ), I cannot return the actual return type, it is inferred
    // (3) we have a `return` in anony fun, what does that mean? we can do something like this like code below. With
    // AnonyFun, we can have multiple return points. With Lamdba expression, I cannot do that.
    // This will impact a concept called LocalReturns.
    op(
        2,
        fun(x): Int {
            println(x)
            if (x > 10) {
                return 0
            } else {
                return x * x
            }
        })
}