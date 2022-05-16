package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.models.AbstractItem
import com.stuffToTake.models.EssentialItem
import com.stuffToTake.models.ShowedItem
import javafx.collections.ListChangeListener
import javafx.scene.control.SelectionMode
import javafx.scene.paint.Color
import tornadofx.*


class FullListOfItemsView : View("Full List of Items to Mainz") {
    // TODO doesn't update if list changes (add listener?)
    // TODO fix categories, this is not a good style!

    // content:
    // TODO make sort by category working in a good way or deactivate it
    // TODO button at every item to edit this item


    private val menuController: MenuController by inject()
    private val itemsListController: ItemsListController by inject()


    override val root = vbox {
        tableview(itemsListController.showItemsToMainz) {
            id = "itemsList"
            column("Name", ShowedItem::nameProperty)
            column("Amount", ShowedItem::amountProperty)  // .makeEditable()
            // .useCheckbox(false)  // TODO improve with this functions
            column("ToTake", ShowedItem::toTakeProperty).cellFormat {
                // text = ""  // TODO improve
                style {
                    backgroundColor += if (it)
                        Color.GREEN
                    else
                        Color.RED
                }
            }
            column("Categories", ShowedItem::categoriesProperty)  // TODO show vertical? With cellFormat in "category \n category ..." format??

            selectionModel.selectionMode = SelectionMode.SINGLE


            onUserSelect(clickCount = 2) { item ->  // TODO use this or delete this
                println("$item\n")
            }

            smartResize()
        }

        button("Back") {
            action {
                menuController.backToMenuView()
            }
        }

    }
}
