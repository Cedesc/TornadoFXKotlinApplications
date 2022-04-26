package com.stuffToTake.view

import com.stuffToTake.controllers.MenuController
import tornadofx.*

class TestView2 : View("My View") {  // TODO delete

    private val menuController: MenuController by inject()

    override val root = vbox {
        button("Back to Menu") {
            action {
                menuController.backToMenuView()
            }
        }
    }
}
