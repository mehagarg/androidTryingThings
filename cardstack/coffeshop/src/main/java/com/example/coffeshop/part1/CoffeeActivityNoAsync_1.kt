package com.example.coffeshop.part1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeshop.R
import com.example.coffeshop.log
import kotlinx.android.synthetic.main.activity_coffee.*
import kotlin.system.measureTimeMillis

class CoffeeActivityNoAsync_1 : AppCompatActivity() {
    private val barista = Barista1()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee)
        orderCoffee()
    }

    // This only prints the last item.
    fun orderCoffee() {
        barista.orders.forEach { log(it) }

        val time = measureTimeMillis {
            barista.orders.forEach {
                val groundBeans = barista.grindCoffeeBeans(it.beans)
                val espresso = barista.pullEspressoShot(groundBeans)
                val steamedMilk = barista.steamMilk(it.milk)
                val cappuccino = barista.makeCappuccino(it, espresso, steamedMilk)
                cappuccinoDetails.text = cappuccino.toString()
                log("serve: $cappuccino")
                log(" ==============   serve:  $cappuccino  ==============   ==============   ")

            }
        }
        log(" ==============   time: ==============   ==============   $time ms   ==============   ==============   ")
    }

}


