package com.stuffToTake.view

import com.stuffToTake.controllers.AddItemController
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
    private val categoriesListView = ListView(Category.values().toList().toObservable())

    private val menuController: MenuController by inject()
    private val addItemController: AddItemController by inject()

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
            checkbox("To Take", itemToTake)
        }

        button("Create Item") {
            enableWhen(model.valid)
            isDefaultButton = true
            action {
                addItemController.addItem(
                    itemName.value, itemAmount.value, itemType.value,
                    categoriesListView.selectionModel.selectedItems, itemToTake.value
                )
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
        model.clearDecorators()
    }

}
