package com.stuffToTake.controllers

import com.stuffToTake.models.Category
import com.stuffToTake.models.EssentialItem
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.Controller

class TestController : Controller() {  // TODO (afterwards) delete

    val testCounter = SimpleIntegerProperty(0)

    var testItem = EssentialItem("name", "one", false)

    fun incrementCounter() {
        testCounter.value++
    }

    fun itemNameChange() {
        testItem.name += "e"
    }

    fun categoryChange() {
        testItem.addCategory(Category.GAME_CONSOLES_STUFF)
    }

    fun categoriesChange() {
        testItem.addCategory(Category.CLOTHING)
    }

}
