package com.example.advancedkotlinhadihaririplayground.deepdivefunctions

/***
 * If you look at lambda extensions to create DSLs, i wasnt very happy with actual code.
 */

class Request1(val method: String, val query: String, val contentType: String)
class Response1(var contents: String, var status1: Status1) {
    // lambda extension
    // we removed status fun and added a new fun invoke which has a modifier operator
    // it magically worked
    // invoke allows us to call response object directly and perform some functionality
    // response is coming from the RouteHandler as its property as we have its instance.
    // response functionality is taking a HOF and thats why we have the brackets

//    response {
//        code = 404
//          ...
//    }

    // We got rid of Status fun, but still able to access code and description inside response block.
    //Look at simple class below
    operator fun invoke(status1: Status1.() -> Unit) {

    }
}
class Status1(var code: Int, var description: String)
fun routeHandler1(path: String, f: RouteHandler1.() -> Unit): RouteHandler1.() -> Unit = f
//fun response1(f: Response1.() -> Unit): Response1.() -> Unit = f

class RouteHandler1(val request1: Request1, val response1: Response1){
    var executeNext = false
    fun next(){
        executeNext = true
    }
}
fun main() {
    /**
     * I am going to invoke routeHandler function here. I have signle lambad param.
     * As I start to write it, I get access to Request.
     * Request is the property of RouteHandler.
     * Given this lambda is the extension function to the RouteHandler, all extension fun have
     * access to the members of the class they are extending.
     * */
    routeHandler1(
        "/index.html"
    ) {
        if (request1.query != "") {
        }
        // response is a Function
//        response {
//            // status is a member fun
//            status { // I dont like the status here.i want to encapsulate as a part of status, but I dont like it much.
//                // these are properties of the status
//                code = 404
//                description = "Not Found!"
//            }
//        }

        // what i want to achieve is:
        /**
         * response1 {
        // I cant do that directly unless I move the whole code and description as a part
        // of response property as I want it to be a part of status
        // essentially try to get rid of status word.
        // first step is to get rid of response fun
        code = 404
        description = "Not found!"
        }*/
        response1 {
            code = 404
            description = "Not found!"
        }
    }

    // if i want an instance of class manager, i will do something like
    val manager = Manager()
    // lets say i want to invoke the functionality of that class just by using the instance.
    // kotlin allows us to call the fun by creating an instance and this has enabled us to do
    // above.
    manager("Do something")
}

// Create a class Manager and create an instance of this class
class Manager {
    operator fun invoke(value: String) {

    }
}