package com.stuffToTake.view

import com.stuffToTake.controllers.MenuController
import tornadofx.*

class MenuView : View("Menu") {

    private val menuController: MenuController by inject()

    override val root = vbox {
        button("Add Item") {
            action {
                menuController.toAddItemView()
            }
        }
        button("Full List of Items") {
            action {
                menuController.toFullListOfItemsView()
            }
        }
        button("Choose Items") {
            action {
                menuController.toChooseItemsView()
            }
        }
        button("History View") {
            action {
                menuController.toHistoryView()
            }
        }

        button("Test View 1") {  // TODO delete
            action {
                menuController.toTestView1()
            }
        }

        button("Test View 2") {  // TODO delete
            action {
                menuController.toTestView2()
            }
        }
    }
}
