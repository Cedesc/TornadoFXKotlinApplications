package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import tornadofx.*

class MenuView : View("Menu") {

    private val menuController: MenuController by inject()
    private val itemsController: ItemsListController by inject()

    override val root = vbox {
        button("Add Item") {
            action {
                menuController.toAddItemView()
            }
        }
        button("Full List of \"To Mainz\"-Items") {
            action {
                itemsController.changeSelectedListToMainz()
                menuController.toListOfItemsView()
            }
        }
        button("Full List of \"To WW\"-Items") {
            action {
                itemsController.changeSelectedListToWW()
                menuController.toListOfItemsView()
            }
        }
        button("Choose Items for Mainz") {
            action {
                itemsController.changeSelectedListToMainz()
                menuController.toChooseItemsView()
            }
        }
        button("Choose Items for WW") {
            action {
                itemsController.changeSelectedListToWW()
                menuController.toChooseItemsView()
            }
        }
        button("History View") {
            action {
                menuController.toHistoryView()
            }
        }

//        button("Test View 1") {  // TODO (afterwards) delete
//            action {
//                menuController.toTestView1()
//            }
//        }
//
//        button("Test View 2") {  // TODO (afterwards) delete
//            action {
//                menuController.toTestView2()
//            }
//        }
    }
}
