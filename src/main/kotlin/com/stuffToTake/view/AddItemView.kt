package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.models.Category
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import tornadofx.*

class AddItemView : View("Add Item") {

    private val model = ViewModel()
    private val itemName = model.bind { SimpleStringProperty() }
    private val itemAmount = model.bind { SimpleStringProperty() }
    private val itemType = model.bind { SimpleStringProperty() }
    private val itemToTake = model.bind { SimpleBooleanProperty() }
    private val toMainz = model.bind { SimpleBooleanProperty() }
    private val toWW = model.bind { SimpleBooleanProperty() }
    private val categoriesListView = ListView(Category.values().toList().toObservable())

    private val menuController: MenuController by inject()
    private val itemsListController: ItemsListController by inject()

    override val root = vbox {
        fieldset("Name") {
            textfield(itemName).required()
        }
        fieldset("Amount") {
            textfield(itemAmount)
        }
        fieldset("Type") {
            combobox(itemType, observableListOf("Essential Item", "Optional Item", "One Time Item")).required()
        }
        fieldset("Categories") {
            categoriesListView.selectionModel.selectionMode = SelectionMode.MULTIPLE
            add(categoriesListView)
        }
        fieldset {
            hbox {
                checkbox("To Take", itemToTake)
                checkbox("To Mainz", toMainz)
                checkbox("To WW", toWW)
            }
        }

        button("Create Item") {
            enableWhen(model.valid)
            isDefaultButton = true
            action {
                itemsListController.addItem(  // TODO uncomment it
                    itemName.value, itemAmount.value, itemType.value,
                    categoriesListView.selectionModel.selectedItems,
                    itemToTake.value, toMainz.value, toWW.value
                )
//                itemsListController.addIt()  // TODO delete

                // clear fields and disable button = reset view
                menuController.refresh()
            }
        }

        button("Back to Menu") {
            action {
                menuController.backToMenuView()
            }
        }
    }

    override fun onDock() {
        itemName.value = ""
        itemAmount.value = ""
        itemType.value = null
        categoriesListView.selectionModel.clearSelection()
        itemToTake.value = true
        toMainz.value = true
        toWW.value = true
        model.clearDecorators()
    }

}
