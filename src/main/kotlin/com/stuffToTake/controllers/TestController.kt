package com.stuffToTake.controllers

import com.stuffToTake.models.Category
import com.stuffToTake.models.EssentialItem
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.Controller

class TestController : Controller() {  // TODO delete

    val testCounter = SimpleIntegerProperty(0)// TODO use this to finish the addItem function

    var testItem = EssentialItem("name", "one", false)

    fun incrementCounter() {
        testCounter.value++
    }

    fun itemNameChange() {
        testItem.name += "e"
    }

    fun categoryChange() {
        testItem.addCategory(Category.NINTENDO_SWITCH)
    }

    fun categoriesChange() {
        testItem.addCategory(Category.CLOTHING)
    }

}
