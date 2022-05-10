package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.controllers.TestController
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class TestView1 : View("My View") {  // TODO delete

    var localCounter = SimpleIntegerProperty(0)

    private val menuController: MenuController by inject()
    private val testController: TestController by inject()
    private val itemsController: ItemsListController by inject()

    override val root = vbox {

        hbox {
            label("Items List Essential Items: ")
            label(itemsController.itemsList.essentialItems.toString()) {
                itemsController.itemsList.essentialItemsProperty.addListener { _, _, newValue ->
                    text = newValue.toString()
                }
            }
        }

        hbox {
            label("Local Counter: ")
            label { bind(localCounter) }
        }
        button("Plus One") { action { localCounter.value++ } }

        hbox {
            label("Controller Counter: ")
            label { bind(testController.testCounter) }
        }
        button("Plus One") { action { testController.incrementCounter() } }

        hbox {
            label("ItemName: ")
            label { bind(testController.testItem.nameProperty) }
        }
        hbox {
            label("Category: ")
        }
        hbox {
            label("Categories: ")
            label(testController.testItem.categories.toString()) {
                testController.testItem.categoriesProperty.addListener { _, _, newValue ->
                    text = newValue.toString()
                }
            }
        }

        button("Change Name") { action { testController.itemNameChange() } }
        button("Change Category") { action { testController.categoryChange() } }
        button("Change Categories") { action { testController.categoriesChange() } }

        button("Back to Menu") { action { menuController.backToMenuView() } }

    }
}
