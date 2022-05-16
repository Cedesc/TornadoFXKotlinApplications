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

    // content:  TODO delete this row
    // TODO make sort by category working in a good way or deactivate it
    // TODO button at every item to edit this item

    // source:
    // https://github.com/edvin/tornadofx-samples/blob/master/itemviewmodel/withFXproperties/src/main/kotlin/no/tornadofx/fxsamples/withfxproperties/views/PersonList.kt
    // https://docs.tornadofx.io/0_subsection/5_data_controls#tableview

    private val menuController: MenuController by inject()
    private val itemsListController: ItemsListController by inject()

//    override val root = vbox {
//
//        tableview(itemsListController.itemsListToMainz.getListOfAllItems().asObservable()) {
//            column("Name", AbstractItem::name)
//            column("Amount", AbstractItem::amount)
//            column("ToTake", AbstractItem::toTake)
//            //        column("Categories", AbstractItem::categories.toString())
//            column("Categories", AbstractItem::categoriesString)
//            smartResize()
//
//            itemsListController.selectedItem.rebindOnChange(this) { selectedItem ->
//                item = selectedItem ?: EssentialItem(name.value, amount.value, toTake.value)
//            }
//
//            itemsListController.itemsListToMainz.essentialItemsProperty.addListener { observable, oldValue, newValue ->
//
//            }
//        }
//    }

    override val root = vbox {
        tableview(itemsListController.showItemsToMainz) {
            id = "itemsList"
            column("Name", ShowedItem::nameProperty)
            column("Amount", ShowedItem::amountProperty)  // .makeEditable()
            // .useCheckbox(false)
            column("ToTake", ShowedItem::toTakeProperty).cellFormat {
                // text = ""
                style {
                    backgroundColor += if (it)
                        Color.GREEN
                    else
                        Color.RED
                }
            }
            column("Categories", ShowedItem::categoriesProperty)

//            itemsListController.selectedItem.rebindOnChange(this) { selectedItem ->
//                item = selectedItem ?: ShowedItem(EssentialItem("placeholder", "", false))
//            }

            selectionModel.selectionMode = SelectionMode.SINGLE


            // TODO HOW TO CHANGE THE ITEMS AUTOMATICALLY????????????????


//            items = listOf(ShowedItem(EssentialItem("1", "2", false))).asObservable()

//            onDoubleClick {
//                println("$selectedItem\n")
//            } does the same as the lines of code below
            onUserSelect { item ->
//                println("$item\n")
                println(itemsListController.showItemsToMainz)

            }



            smartResize()
        }

        button("Back") {
            action {
                menuController.backToMenuView()
            }
        }

        button("say") {  // TODO delete
            action {
                println(itemsListController.showItemsToMainz)
            }
        }

    }

    override fun onDock() {  // TODO delete

//        itemsListController.showItemsToMainz = itemsListController.itemsListToMainz.getListOfAllItems().map {
//            ShowedItem(it)
//        }.asObservable()

//        itemsListController.showItemsToMainz.setAll(ShowedItem(EssentialItem("1", "2", false)))
        println("Hello")




    }

}


//        button("Back to Menu") {
//            action {
//                menuController.backToMenuView()
//            }
//        }
