package com.example.coffeshop.part1

import com.example.coffeshop.log
import com.example.coffeshop.model.CoffeeBean
import com.example.coffeshop.model.Espresso
import com.example.coffeshop.model.Menu
import com.example.coffeshop.model.Milk
import com.example.coffeshop.model.Beverage
import java.lang.Thread.sleep

class Barista1 {

    val orders = listOf(
        Menu.Cappuccino(CoffeeBean.Regular, Milk.Whole),
        Menu.Cappuccino(CoffeeBean.Premium, Milk.Breve),
        Menu.Cappuccino(CoffeeBean.Regular, Milk.NonFat),
        Menu.Cappuccino(CoffeeBean.Decaf, Milk.Whole),
        Menu.Cappuccino(CoffeeBean.Regular, Milk.NonFat),
        Menu.Cappuccino(CoffeeBean.Decaf, Milk.NonFat)
    )

    fun pullEspressoShot(groundBeans: CoffeeBean.GroundBeans): Espresso {
        log("pulling espresso shot")
        sleep(600)
        return Espresso(groundBeans)
    }

    fun grindCoffeeBeans(beans: CoffeeBean): CoffeeBean.GroundBeans {
        log("grinding coffee beans")
        sleep(1000)
        return CoffeeBean.GroundBeans(beans)
    }

    fun steamMilk(milk: Milk): Milk.SteamedMilk {
        log("steaming milk")
        sleep(300)
        return Milk.SteamedMilk(milk)
    }

    fun makeCappuccino(
        order: Menu.Cappuccino,
        espresso: Espresso,
        steamedMilk: Milk.SteamedMilk
    ): Beverage.Cappuccino {
        log("making cappuccino")
        sleep(100)
        return Beverage.Cappuccino(order, espresso, steamedMilk)
    }
}