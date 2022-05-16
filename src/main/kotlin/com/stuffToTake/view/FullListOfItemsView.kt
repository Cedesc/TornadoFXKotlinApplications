package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.models.ShowedItem
import javafx.scene.control.SelectionMode
import javafx.scene.paint.Color
import tornadofx.*


class FullListOfItemsView : View("Full List of Items to Mainz") {

    // TODO make sort by category working in a good way or deactivate it
    // TODO button at every item to edit this item (or per doubly click OR both)

    private val menuController: MenuController by inject()
    private val itemsListController: ItemsListController by inject()


    override val root = vbox {
        tableview(itemsListController.showItemsToMainz) {
            id = "itemsList"
            column("Name", ShowedItem::nameProperty)
            column("Amount", ShowedItem::amountProperty)
            column("ToTake", ShowedItem::toTakeProperty).cellFormat {
                text = "■"
                style {
                    textFill = if (it)
                        Color.GREEN
                    else
                        Color.RED
                }
            }
            column("Categories", ShowedItem::categoriesProperty).cellFormat {
                text = ""
                it.forEach { category ->
                    text += "${category}\n"
                }
            }

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
