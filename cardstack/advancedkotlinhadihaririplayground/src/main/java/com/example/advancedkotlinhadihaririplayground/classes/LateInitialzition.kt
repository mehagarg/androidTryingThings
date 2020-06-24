package com.example.advancedkotlinhadihaririplayground.classes

interface Repository {
    fun getAll(): List<Customer>
}

class CustomController() {
    /**
     * Now here is the problem. Imagine I am using some IOC f/w(Inversion of control)
     * and I want repository to be a property that is initialized by the IOC framework.
     * I am not going to pass on as a constructor parameter, but for whaterver reason i want it to
     * be initialized later.
     * If you look at the code it is a problem. It says
     * `Property must be initialzied of be abstract`. Now, I cannot initalize it myself and I dont
     * want property to be abstract.
     *
     * 1. Solution is to say, potentially this is going to be null. But then what happens is every single
     *    time I access repository, I have to put the `?` mark. And really this property is not null.
     *    I dont want it to be null. I dont want someboday reading this understand that potentially
     *    this is nullable. It is not null as IOC will initialize it at some point.
     * 2. We can solve this problem in Kotlin by using the late init modifier. It is telling the compiler
     *    dont worry about this & this property is going to be initialized little later. However this is
     *    not going to make everything safe.
     * 3. what if you run this and it is not initialized, we will get NPE.
     */
//    var repository: Repository? = null
    lateinit var repository: Repository

    fun index(): String {
//        return repository?.getAll().toString()
        return repository.getAll().toString()
    }
}

fun main(){
    /**
     * You see we dont get a null reference exception, but
     * Exception in thread "main" kotlin.UninitializedPropertyAccessException: lateinit property repository has not been initialized
    at com.example.advancedkotlinhadihaririplayground.classes.CustomController.index(LateInitialzition.kt:31)
    at com.example.advancedkotlinhadihaririplayground.classes.LateInitialzitionKt.main(LateInitialzition.kt:43)

     This is a hint saying that it is not NPE but you have a property that you promised me that you will
     initialize but you used it before you did so.
     */
    val customController = CustomController()
    customController.index()
}