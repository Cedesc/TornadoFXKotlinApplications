package com.stuffToTake.view

import com.stuffToTake.controllers.ConfirmController
import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.models.*
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import tornadofx.*

class EditItemView : View("PLACEHOLDER") {

    private val itemsListController: ItemsListController by inject()
    private val confirmController: ConfirmController by inject()

    val item: AbstractItem by param()

    private val model = ViewModel()
    private val itemName = model.bind { SimpleStringProperty() }
    private val itemAmount = model.bind { SimpleStringProperty() }
    private val itemType = model.bind { SimpleStringProperty() }
    private val itemToTake = model.bind { SimpleBooleanProperty() }
    private val categoriesListView = ListView(Category.values().toList().toObservable())


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
            add(categoriesListView)
        }
        fieldset {
            hbox {
                checkbox("To Take", itemToTake)
            }
        }

        button("print") {  // TODO delete
            action {
                println("$item\n")
            }
        }

        button("Save Changes") {  // TODO test
            action {
                // Show window to check if user is sure
                confirmController.confirmChanges(item)

                // wait until the pop-up window is closed
                currentStage?.focusedProperty()?.onChangeOnce {
                    // if the confirmation is given, apply the changes
                    if (confirmController.confirmed)
                        itemsListController.saveItemChanges(
                            item,
                            itemName.value, itemAmount.value, itemType.value,
                            categoriesListView.selectionModel.selectedItems, itemToTake.value)
                }
            }
        }

        button("Delete") {  // TODO test
            action {
                // Show window to check if user is sure
                confirmController.confirmDeletion(item)

                // wait until the pop-up window is closed
                currentStage?.focusedProperty()?.onChangeOnce {
                    // if the confirmation is given, apply the changes
                    if (confirmController.confirmed)
                        itemsListController.deleteItem(item)
                }
            }
        }

    }

    override fun onDock() {

        itemName.value = item.name
        itemAmount.value = item.amount
        itemToTake.value = item.toTake

        // Select the given item type.
        itemType.value = when(item) {
            is EssentialItem -> "Essential Item"
            is OptionalItem -> "Optional Item"
            is OneTimeItem -> "One Time Item"
            else -> throw Exception("No valid type.")
        }

        // Select categories which the item has already.
        categoriesListView.selectionModel.selectionMode = SelectionMode.MULTIPLE
        item.categories.forEach { category ->
            categoriesListView.selectionModel.select(category)
        }

        title = "Edit \"${item.name}\" of the \"${itemsListController.selectedItemList.name}\"-List"
    }

}
