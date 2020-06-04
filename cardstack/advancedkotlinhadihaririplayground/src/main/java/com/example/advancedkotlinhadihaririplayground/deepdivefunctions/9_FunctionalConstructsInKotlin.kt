package com.example.advancedkotlinhadihaririplayground.deepdivefunctions

infix fun <P1, IP, R> Function1<P1, IP>.andThen(f: (IP) -> R): (P1) -> R = forwardCompose(f)

infix fun <P1, IP, R> Function1<P1, IP>.forwardCompose(f: (IP) -> R): (P1) -> R {
    return { p1: P1 -> f(this(p1)) }
}

infix fun <IP, R, P1> Function1<IP, R>.compose(f: (P1) -> IP): (P1) -> R {
    return { p1: P1 -> this(f(p1)) }
}

// Functional programming, std library does not have direct support.
// Open source library Funktionale add series of new fun to new tool box to already availabel fun
// what is composition? one fun that takes input a and produces b, another fun takes b and produces c and we can compose together
// fun that takes input a and produces c


// and then there is currying, memoization
// lets see what currying does

fun <P1, P2, R> Function2<P1, P2, R>.curried(): (P1) -> (P2) -> R {
    return { p1: P1 -> { p2: P2 -> this(p1, p2) } }
}

fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.curried(): (P1) -> (P2) -> (P3) -> R {
    return { p1: P1 -> { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } } }
}