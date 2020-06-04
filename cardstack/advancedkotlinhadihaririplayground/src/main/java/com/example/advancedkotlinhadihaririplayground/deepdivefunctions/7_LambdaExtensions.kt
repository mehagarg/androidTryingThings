package com.example.advancedkotlinhadihaririplayground.deepdivefunctions

/***
 * Aim: Ability for kotlin to have lambda extensions or lamdba with receivers allow to create fluent expressive DSLs
 * convention to enclose in brackets, multiline, a nice DSL
 * Markup html builders copy of groovy html builders with the difference that all these are functions / classes
 *
 */
class Request(val method: String, val query: String, val contentType: String)
//class Response(var contents: String, var status: Int)
class Response(var contents: String, var status: Status) {
    fun status(f: Status.() -> Unit): Status.() -> Unit = f
}
class Status(var code: Int, var description: String)

class RouteHandler(val request: Request, val response: Response){
    var executeNext = false
    fun next(){
        executeNext = true
    }
}

// going to take as a parameter a lambda extension and going to return Lambda extension as well.
//and it is equal to the value passed in. Why i am doing this? Lets go to main and see how to use it.

fun routeHandler(path: String, f: RouteHandler.() -> Unit): RouteHandler.() -> Unit = f
fun response(f: Response.() -> Unit): Response.() -> Unit = f

fun main(){
    /**
     * I am going to invoke routeHandler function here. I have signle lambad param.
     * As I start to write it, I get access to Request.
     * Request is the property of RouteHandler.
     * Given this lambda is the extension function to the RouteHandler, all extension fun have
     * access to the members of the class they are extending.
     * */
    routeHandler(
        "/index.html"
    ) {
        if (request.query != "") {
            // process the query
        }
//        response.status.code // we dont want like this.
        response {
//            status.code = 404
//            status.description = "Not found!"
            status { // Here we reached where we needed as a DSL
                code = 404
                description = "Not Found!"
            }
        }
    }
}

