package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.ToTakeController
import com.stuffToTake.models.ShowItem
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.scene.control.SelectionMode
import tornadofx.*

class ToTakeView : View("To Take View") {

    private val toTakeController: ToTakeController by inject()
    private val itemsListController: ItemsListController by inject()

    private val items: SimpleObjectProperty<ObservableList<ShowItem>> =
        SimpleObjectProperty(toTakeController.itemsListToTake)

    override val root = vbox {
        tableview(items) {
            id = "itemsList"
            column("new to take", ShowItem::toTakeProperty).useCheckbox()
            column("Name", ShowItem::nameProperty)
            column("Amount", ShowItem::amountProperty)

            selectionModel.selectionMode = SelectionMode.SINGLE

            onUserSelect(clickCount = 1) {
                it.changeToTake()
                selectionModel.select(null)
            }


            smartResize()
        }

        button("Finished") {
            action {
                TODO("Not yet implemented")
            }
        }

        button("Back") {
            action {
                TODO("Not yet implemented")
            }
        }
    }

    override fun onDock() {
        title = itemsListController.selectedItemList.name
        items.set(toTakeController.itemsListToTake)
        super.onDock()
    }
}
