package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.models.ShowItem
import javafx.collections.ObservableList
import javafx.scene.control.SelectionMode
import javafx.scene.paint.Color
import tornadofx.*


class FullListOfToMainzItemsView : View("Full List of Items to Mainz") {

    // TODO make sort by category working in a good way or deactivate it
    // TODO button at every item to edit this item (or per doubly click OR both)

    private val menuController: MenuController by inject()
    private val itemsListController: ItemsListController by inject()

//    val observableList: ObservableList<ShowItem> by param()  // TODO delete


    override val root = vbox {
        tableview(itemsListController.selectedItemList) {
            id = "itemsList"
            column("Name", ShowItem::nameProperty)
            column("Amount", ShowItem::amountProperty)
            column("ToTake", ShowItem::toTakeProperty).cellFormat {
                text = "■"
                style {
                    textFill = if (it)
                        Color.GREEN
                    else
                        Color.RED
                }
            }
            column("Categories", ShowItem::categoriesProperty).cellFormat {
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

//    override fun onDock() {  // TODO delete
////        observableList2.setAll(observableList)
////        observableList2 = observableList
////        observableList2.setAll(observableList)
//        println("on dock")
//    }
//
//    override fun onCreate() {
//        println("on create")
//        super.onCreate()
//    }
//
//    override fun onUndock() {
//        println("on undock")
//        onDelete()
//        super.onUndock()
//    }
}
