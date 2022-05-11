package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.models.AbstractItem
import tornadofx.*

class FullListOfItemsView : View("Full List of Items") {
    // TODO doesn't update if list changes (add listener?)
    // TODO fix categories, this is not a good style!

    // content:
    // TODO make sort by category working in a good way or deactivate it
    // TODO button at every item to edit this item

    private val menuController: MenuController by inject()
    private val itemsListController: ItemsListController by inject()

    override val root = vbox {

        tableview(itemsListController.itemsListToMainz.getListOfAllItems().asObservable()) {
            column("Name", AbstractItem::name)
            column("Amount", AbstractItem::amount)
            column("ToTake", AbstractItem::toTake)
        //        column("Categories", AbstractItem::categories.toString())
            column("Categories", AbstractItem::categoriesString)
            smartResize()  // TODO what does this function do
        }


        button("Back to Menu") {
            action {
                menuController.backToMenuView()
            }
        }
    }

}
