package com.example.advancedkotlinhadihaririplayground.classes

/**
 * Condtion if value is greater than 100 set it else dont do it
 * You dont have to do validation yourself and there is a way to do it better.
 *
 * Backing field is auto generated for preoperty if it uses default implementaiton for atleast one of the accessors or
 * if there is a custom accessor referencing the field
 *
 * For cases where field is not enough, we just cant use backing field for whatever reason then only way arround is
 * to create a private property myCustomField below. and then just use that instead of `field` because there
 * is no field beyond this autoBackingField that we can reference as the word field.
 */
class Customer {
    private var myCustomField = 10.0

    var lastPurchasedAmount: Double = 0.0
//        get() = field // Built in field variable
        get() = myCustomField // Built in field variable
        set(value) {
            if (value > 100) {
                field = value
            }
        }
}

fun main() {
    val customer = Customer()
    println(customer.lastPurchasedAmount)
    customer.lastPurchasedAmount = 200.0
    println(customer.lastPurchasedAmount)
    customer.lastPurchasedAmount = 50.0
    println(customer.lastPurchasedAmount)
}