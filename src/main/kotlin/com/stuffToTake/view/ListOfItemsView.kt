package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.models.ShowItem
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.control.SelectionMode
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*


class ListOfItemsView : View("List of Items") {

    private val menuController: MenuController by inject()
    private val itemsListController: ItemsListController by inject()

    private val selectedObservableItemsList: SimpleObjectProperty<ObservableList<ShowItem>> =
        SimpleObjectProperty(itemsListController.selectedItemList.observableShowItems)

    private val ownPrefWidth: Double = 500.0
    private val ownPrefHeight: Double = 900.0


    override val root = vbox {
        // set size
        setPrefSize(ownPrefWidth, ownPrefHeight)

        tableview(selectedObservableItemsList) {
            id = "itemsList"
            column("ToTake", ShowItem::toTakeProperty).cellFormat {
                text = "■"
                style {
                    alignment = Pos.CENTER
                    textFill = if (it)
                        Color.GREEN
                    else
                        Color.RED
                }
            }
            column("Name", ShowItem::nameProperty) {
                style {
                    enableTextWrap()
                }
            }
            column("Amount", ShowItem::amountProperty) {
                style {
                    alignment = Pos.CENTER
                }
            }
            column("Categories", ShowItem::categoriesProperty).cellFormat {
                text = ""
                it.forEach { category ->
                    text += "${category}\n"
                }
            }

            selectionModel.selectionMode = SelectionMode.SINGLE
            onUserSelect(clickCount = 2) { item ->
                itemsListController.toEditItemView(item)
            }

            smartResize()


            prefHeight = ownPrefHeight - (ownPrefHeight / 13)
            style {
                fontSize = 15.px
            }
        }

        button("Back") {
            action {
                menuController.backToMenuView()
            }

            prefWidth = ownPrefWidth
            prefHeight = ownPrefHeight / 13
            style {
                alignment = Pos.CENTER
                padding = box(10.px)
                fontSize = 15.px
                fontWeight = FontWeight.BOLD
            }
        }

    }

    override fun onDock() {
        title = "All Items of the \"${itemsListController.selectedItemList.name}\"-List"
        selectedObservableItemsList.set(itemsListController.selectedItemList.observableShowItems)
        super.onDock()
    }
}
