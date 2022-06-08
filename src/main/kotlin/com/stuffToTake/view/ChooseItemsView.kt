package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.controllers.ToTakeController
import com.stuffToTake.models.ShowItem
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.scene.control.SelectionMode
import tornadofx.*

class ChooseItemsView : View("Choose Items View") {

    private val menuController: MenuController by inject()
    private val itemsListController: ItemsListController by inject()
    private val toTakeController: ToTakeController by inject()

    private val selectedObservableItemsList: SimpleObjectProperty<ObservableList<ShowItem>> =
        SimpleObjectProperty(itemsListController.selectedItemList.observableShowItems)


    override val root = vbox {
        tableview(selectedObservableItemsList) {
            id = "chooseList"
            column("To Take", ShowItem::toTakeProperty).useCheckbox()
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

        // TODO Idea:
        //  a click on the button "To ToTake-View" first call the function of each element "markAsToTake"
        //  respectively "unmarkAsToTake" depending on the toTake checkBox
        //  (so maybe it should be called "markedAsToTake" and "unmarkedAsToTake")
        //  to call the related functions for the Essential Items etc,
        //  so the Essential Items will stay as "toTake", oneTime will be false in toTake and so on


        button("To \"ToTake\"-View") {
            action {
                val selectedItems: ObservableList<ShowItem> =
                    toTakeController.createCopiedListOfSelectedItems(selectedObservableItemsList.value)
                toTakeController.toToTakeView(selectedItems)
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
