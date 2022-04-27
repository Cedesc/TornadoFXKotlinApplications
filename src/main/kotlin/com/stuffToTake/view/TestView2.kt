package com.stuffToTake.view

import com.stuffToTake.controllers.MenuController
import com.stuffToTake.controllers.TestController
import tornadofx.*

class TestView2 : View("My View") {  // TODO delete

    private val menuController: MenuController by inject()
    private val testController: TestController by inject()

    override val root = vbox {

        hbox {
            label("Controller Counter: ")
            label { bind(testController.testCounter) }
        }

        hbox {
            label("Item Test: ")
            label { bind(testController.testItem.itemName) }
        }

        button("Back to Menu") { action { menuController.backToMenuView() } }
    }
}
