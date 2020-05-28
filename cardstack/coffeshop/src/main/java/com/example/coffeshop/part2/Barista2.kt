package com.example.coffeshop.part2

import com.example.coffeshop.log
import com.example.coffeshop.model.CoffeeBean
import com.example.coffeshop.model.Espresso
import com.example.coffeshop.model.Menu
import com.example.coffeshop.model.Milk
import com.example.coffeshop.model.Beverage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep

class Barista2 {

    val orders = listOf(
        Menu.Cappuccino(CoffeeBean.Regular, Milk.Whole),
        Menu.Cappuccino(CoffeeBean.Premium, Milk.Breve),
        Menu.Cappuccino(CoffeeBean.Regular, Milk.NonFat),
        Menu.Cappuccino(CoffeeBean.Decaf, Milk.Whole),
        Menu.Cappuccino(CoffeeBean.Regular, Milk.NonFat),
        Menu.Cappuccino(CoffeeBean.Decaf, Milk.NonFat)
    )

    suspend fun pullEspressoShot(groundBeans: CoffeeBean.GroundBeans): Espresso = withContext(Dispatchers.IO) {
        log("pulling espresso shot")
        delay(600)
        return@withContext Espresso(groundBeans)
    }

    suspend fun grindCoffeeBeans(beans: CoffeeBean): CoffeeBean.GroundBeans  = withContext(Dispatchers.IO){
        log("grinding coffee beans")
        delay(1000)
        return@withContext CoffeeBean.GroundBeans(beans)
    }

    suspend fun steamMilk(milk: Milk): Milk.SteamedMilk  = withContext(Dispatchers.IO){
        log("steaming milk")
        delay(300)
        return@withContext Milk.SteamedMilk(milk)
    }

    suspend fun makeCappuccino(
        order: Menu.Cappuccino,
        espresso: Espresso,
        steamedMilk: Milk.SteamedMilk
    ): Beverage.Cappuccino  = withContext(Dispatchers.IO){
        log("making cappuccino")
        delay(100)
        return@withContext Beverage.Cappuccino(order, espresso, steamedMilk)
    }
}