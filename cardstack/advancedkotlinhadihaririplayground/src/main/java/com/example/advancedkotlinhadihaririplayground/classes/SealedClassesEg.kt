package com.example.advancedkotlinhadihaririplayground.classes

class SealedClassesEg {

}


class PageResult(val result: String, var isError: Boolean){

}

fun getURLPage(url: String): PageResult {
    val wasValidCall = false
    if(wasValidCall) {
        return PageResult("The content", false)
    } else {
        return PageResult("ERROR", true)
    }
}

fun main(){

}

/***
 * Boolean data type - means that I can be of two different types
 * true or false
 *
 * Algebric data types are not in Kotlin
 * and allows to create Sealed class.
 * what classes can inherit from base class.
 */
