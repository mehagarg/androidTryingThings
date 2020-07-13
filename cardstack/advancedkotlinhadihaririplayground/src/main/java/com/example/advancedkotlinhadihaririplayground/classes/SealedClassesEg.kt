package com.example.advancedkotlinhadihaririplayground.classes

class SealedClassesEg {

}


sealed class PageResult {
    class Success(val content: String) : PageResult()
    class Error(val code: Int, val message: String) : PageResult()
}
class Success(val content: String) : PageResult()
class Error(val code: Int, val message: String) : PageResult()

fun getURLPage(url: String): PageResult {
    val wasValidCall = false
    if(wasValidCall) {
        return PageResult.Success("The content")
    } else {
        return PageResult.Error(123, "Error")
    }
}

fun main(){
    /***
     * The caller of the fun will have to do something like below
     *
     * Single class PageResult representing two distinct values. It is kind of confusiong.
     * Why not PageResult return an algebric data type, return a type ie. for valid result and
     * for error instead of sharing these things.
     */
    val pageResult = getURLPage("/")
//    if(pageResult.isError){
//
//    }

}

/***
 * Boolean data type - means that I can be of two different types
 * true or false
 *
 * Algebric data types are not in Kotlin
 * and allows to create Sealed class.
 * what classes can inherit from base class.
 */
