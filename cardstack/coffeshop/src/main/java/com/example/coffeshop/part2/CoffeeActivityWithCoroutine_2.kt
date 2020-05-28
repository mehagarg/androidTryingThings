package com.example.coffeshop.part2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeshop.R
import com.example.coffeshop.log
import com.example.coffeshop.model.Menu
import kotlinx.android.synthetic.main.activity_coffee.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlin.system.measureTimeMillis

class CoffeeActivityWithCoroutine_2 : AppCompatActivity() {

    private val barista = Barista2()
    private val coroutineScope = CoroutineScope(Job()) + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee)
        calculateTimeToProcessOrders()
    }

    private fun calculateTimeToProcessOrders() {
        coroutineScope.launch {
            val ordersChannel = Channel<Menu.Cappuccino>()
            launch(CoroutineName("cashier")) {
                barista.orders.forEach { ordersChannel.send(it) }
                ordersChannel.close()
            }

            val time = measureTimeMillis {
                coroutineScope {
                    launch(CoroutineName("barista-1")) { processOrders(ordersChannel) }
                    launch(CoroutineName("barista-2")) { processOrders(ordersChannel) }
                }
            }
            log("time: $time ms")
        }
    }

    private suspend fun processOrders(orders: Channel<Menu.Cappuccino>) {
        orders.consumeEach { it ->
            val groundBeans = barista.grindCoffeeBeans(it.beans)
            val espresso = barista.pullEspressoShot(groundBeans)
            val steamedMilk = barista.steamMilk(it.milk)
            val cappuccino = barista.makeCappuccino(it, espresso, steamedMilk)
            cappuccinoDetails.text = "order details $cappuccino"
            log(" ==============   serve:  $cappuccino  ==============   ==============   ")
        }
    }
}


