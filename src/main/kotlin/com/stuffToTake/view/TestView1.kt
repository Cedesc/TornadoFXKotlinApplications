package com.stuffToTake.view

import com.stuffToTake.controllers.MenuController
import com.stuffToTake.controllers.TestController
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class TestView1 : View("My View") {  // TODO delete

    var localCounter = SimpleIntegerProperty(0)

    private val menuController: MenuController by inject()
    private val testController: TestController by inject()

    override val root = vbox {

        hbox {
            label("Local Counter: ")
            label { bind(localCounter) }
        }
        button("Plus One") { action { localCounter.value++ } }

        hbox {
            label("Controller Counter: ")
            label { bind(testController.testCounter) }
        }
        button("Plus One") { action { testController.incrementCounter() } }

        hbox {
            label("ItemName: ")
            label { bind(testController.testItem.itemName) }
        }
        button("Change Name") { action { testController.itemNameChange() } }

        button("Back to Menu") { action { menuController.backToMenuView() } }

    }
}
