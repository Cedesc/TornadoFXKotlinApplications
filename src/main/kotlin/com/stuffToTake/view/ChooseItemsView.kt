package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.models.ShowItem
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.scene.control.SelectionMode
import tornadofx.*

class ChooseItemsView : View("Choose Items View") {

    private val menuController: MenuController by inject()
    private val itemsListController: ItemsListController by inject()

    private val selectedObservableItemsList: SimpleObjectProperty<ObservableList<ShowItem>> =
        SimpleObjectProperty(itemsListController.selectedItemList.observableShowItems)


    override val root = vbox {
        tableview(selectedObservableItemsList) {
            id = "itemsList"
            column("new to take", ShowItem::toTakeProperty).useCheckbox()
            column("Name", ShowItem::nameProperty)
            column("Amount", ShowItem::amountProperty)
            column("Categories", ShowItem::categoriesProperty).cellFormat {
                text = ""
                it.forEach { category ->
                    text += "${category}\n"
                }
            }

            selectionModel.selectionMode = SelectionMode.SINGLE

            onUserSelect(clickCount = 1) {
                it.changeToTake()
                selectionModel.select(null)
            }


            smartResize()
        }

        // TODO Idea: a click on the button "To ToTake-View" first call the function of each element "markAsToTake"
        //  respectively "unmarkAsToTake" depending on the toTake checkBox
        //  (so maybe it should called "markedAsToTake" and "unmarkedAsToTake")
        //  to call the related functions for the Essential Items etc,
        //  so the Essential Items will stay as "toTake", oneTime will be false in toTake and so on


        // TODO put this in the controller (a new controller??)
        button("Print all selected") {  // TODO (afterwards) delete
            action {
                selectedObservableItemsList.value.forEach { item ->
                    if (item.toTake)
                        println(item)
                }
                println("\n------------------------\n")
            }
        }
        button("Print all unselected") {  // TODO (afterwards) delete
            action {
                selectedObservableItemsList.value.forEach { item ->
                    if (! item.toTake)
                        println(item)
                }
                println("\n------------------------\n")
            }
        }
        button("Back") {
            action {
                menuController.backToMenuView()
            }
        }

    }

    override fun onDock() {
        title = "Choose Items of the\"${itemsListController.selectedItemList.name}\"-List"
        selectedObservableItemsList.set(itemsListController.selectedItemList.observableShowItems)
        super.onDock()
    }

    override fun onUndock() {
        itemsListController.refreshItemsLists()
        super.onUndock()
    }
}
