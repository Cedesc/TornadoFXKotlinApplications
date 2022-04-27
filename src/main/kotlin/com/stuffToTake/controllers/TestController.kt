package com.stuffToTake.controllers

import com.stuffToTake.models.Category
import com.stuffToTake.models.TestItem
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.Controller

class TestController : Controller() {  // TODO delete

    val testCounter = SimpleIntegerProperty(0)// TODO use this to finish the addItem function

    var testItem = TestItem("name", "one", false)

    fun incrementCounter() {
        testCounter.value++
    }

    fun itemNameChange() {
        testItem.itemName.value += "e"
    }

    fun categoryChange() {
        testItem.category.value = Category.NINTENDO_SWITCH
    }

}
