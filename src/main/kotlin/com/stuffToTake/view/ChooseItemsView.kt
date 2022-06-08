package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.controllers.ToTakeController
import com.stuffToTake.models.ShowItem
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.control.SelectionMode
import javafx.scene.text.FontWeight
import tornadofx.*

class ChooseItemsView : View("Choose Items View") {

    private val menuController: MenuController by inject()
    private val itemsListController: ItemsListController by inject()
    private val toTakeController: ToTakeController by inject()

    private val selectedObservableItemsList: SimpleObjectProperty<ObservableList<ShowItem>> =
        SimpleObjectProperty(itemsListController.selectedItemList.observableShowItems)

    private val ownPrefWidth: Double = 500.0
    private val ownPrefHeight: Double = 900.0


    override val root = vbox {
        // set size
        setPrefSize(ownPrefWidth, ownPrefHeight)

        tableview(selectedObservableItemsList) {
            id = "chooseList"
            column("To Take", ShowItem::toTakeProperty).useCheckbox()
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
            onUserSelect(clickCount = 1) {
                it.changeToTake()
                selectionModel.select(null)
            }

            smartResize()


            prefHeight = ownPrefHeight - (ownPrefHeight / 13)
            style {
                fontSize = 15.px
            }
        }


        hbox {
            button("Back") {
                action {
                    menuController.backToMenuView()
                }

                prefWidth = ownPrefWidth / 2
                prefHeight = ownPrefHeight / 13
                style {
                    alignment = Pos.CENTER
                    padding = box(10.px)
                    fontSize = 15.px
                    fontWeight = FontWeight.BOLD
                }
            }

            button("To \"ToTake\"-View") {
                action {
                    val selectedItems: ObservableList<ShowItem> =
                        toTakeController.createCopiedListOfSelectedItems(selectedObservableItemsList.value)
                    toTakeController.toToTakeView(selectedItems)
                }

                prefWidth = ownPrefWidth / 2
                prefHeight = ownPrefHeight / 13
                style {
                    alignment = Pos.CENTER
                    padding = box(10.px)
                    fontSize = 15.px
                    fontWeight = FontWeight.BOLD
                }
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
