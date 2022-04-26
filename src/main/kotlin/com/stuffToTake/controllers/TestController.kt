package com.stuffToTake.controllers

import javafx.beans.property.SimpleIntegerProperty
import tornadofx.Controller

class TestController : Controller() {  // TODO delete

    val testCounter = SimpleIntegerProperty(0)

    fun incrementCounter() {
        testCounter.value++
    }

}
