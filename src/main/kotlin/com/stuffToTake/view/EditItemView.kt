package com.stuffToTake.view

import com.stuffToTake.models.*
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import tornadofx.*

class EditItemView : View("My View") {

    val item: AbstractItem by param()

    private val model = ViewModel()
    private val itemName = model.bind { SimpleStringProperty() }
    private val itemAmount = model.bind { SimpleStringProperty() }
    private val itemType = model.bind { SimpleStringProperty() }
    private val itemToTake = model.bind { SimpleBooleanProperty() }
    private val toMainz = model.bind { SimpleBooleanProperty() }  // TODO add equal to AbstractItem and check if this item is in there?
    private val toWW = model.bind { SimpleBooleanProperty() }  // TODO add equal to AbstractItem and check if this item is in there?
    private val categoriesListView = ListView(Category.values().toList().toObservable())

//    init {
//        refresh()
//    }

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
                checkbox("To Mainz", toMainz)
                checkbox("To WW", toWW)
            }
        }

        button("print") {  // TODO delete
            action {
                println("$item\n")
            }
        }

        button("Save") {
            action {
                TODO("Not yet implemented")  // TODO delete old and add new?
            }
        }

    }

    override fun onDock() {

        itemName.value = item.name
        itemAmount.value = item.amount
        itemToTake.value = item.toTake

        // Select the given item type
        itemType.value = when(item) {
            is EssentialItem -> "Essential Item"
            is OptionalItem -> "Optional Item"
            is OneTimeItem -> "One Time Item"
            else -> throw Exception("No valid type.")
        }

        // Select categories which the item has already
        categoriesListView.selectionModel.selectionMode = SelectionMode.MULTIPLE
        item.categories.forEach { category ->
            categoriesListView.selectionModel.select(category)
        }
    }

}
