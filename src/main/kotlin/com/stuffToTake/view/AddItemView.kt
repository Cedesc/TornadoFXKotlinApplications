package com.stuffToTake.view

import com.stuffToTake.controllers.AddItemController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.models.Category
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class AddItemView : View("Add Item") {

    private val model = ViewModel()
    private val itemName = model.bind { SimpleStringProperty() }
    private val itemAmount = model.bind { SimpleStringProperty() }
    private val itemType = model.bind { SimpleStringProperty() }  // TODO use better type? (class?)
    private val itemCategories = model.bind { SimpleListProperty<Category>() }
    private val itemToTake = model.bind { SimpleBooleanProperty() }
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
            // TODO find a meaningful form for "choose from 3 elements" - rotate box (?)
        }
        fieldset("Categories") {
            // TODO find a meaningful form for "pick multiple categories" - ???
        }
        fieldset("To Take") {
            // TODO add checkbox
        }

        button("Create Item") {
            enableWhen(model.valid)
            isDefaultButton = true
            action {
                addItemController.addItem(
                    itemName.value, itemAmount.value, itemType.value,
                    itemCategories.value, itemToTake.value
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
        itemType.value = ""
        itemCategories.value = observableListOf()
        itemToTake.value = true
        model.clearDecorators()
    }

}
