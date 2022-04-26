package com.stuffToTake.view

import com.stuffToTake.controllers.MenuController
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class TestView1 : View("My View") {  // TODO delete

    var test = SimpleIntegerProperty(1)

    private val menuController: MenuController by inject()

    override val root = vbox {

        button("plus one") {
            action {
                test.value++
            }
        }

        label("Value: ${test.value}") {
            bind(test)
        }

        button("Back to Menu") {
            action {
                menuController.backToMenuView()
            }
        }

    }
}
