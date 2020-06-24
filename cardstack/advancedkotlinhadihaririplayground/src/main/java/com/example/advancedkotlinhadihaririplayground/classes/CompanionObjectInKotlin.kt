package com.example.advancedkotlinhadihaririplayground.classes

import com.example.advancedkotlinhadihaririplayground.classes.Log.Factory.createFileLog

/**
 *  We have seen in Kotlin that we dont have
 *  static methods
 *  static classes
 *
 *  If we want to define something static, we define some
 *  top level functions.
 *
 *  Or we can create an object and attach it to that object.
 *  Is there a need of equivalent of static method or classes and access from java?
 *  How do we create one in Kotlin.
 *
 *  thats where companion objects come into.
 *
 * **/


class Log() {

    companion object Factory {
        @JvmStatic fun createFileLog(fileName: String): Log = Log(fileName)
    }

    /**
     * Each class can only have a single companion object.
     * You cannot do this.
     */
//    companion object Factory1 {
//        fun createFileLog(fileName: String): Log = Log(fileName)
//    }

    constructor(fileName: String) : this() {

    }

    /**
     * I dont want to do this. Here I can use createFileLog by actually prefixing with the
     * object.
     */
//    fun usingFactoryFunction(){
//        Factory.createFileLog("aaa")
//    }

}


fun main(){
    val log = Log()

    /**
     * How do we do such?
     */
    val fileLog = Log.createFileLog("file.txt")

    val myLog = Log()
}