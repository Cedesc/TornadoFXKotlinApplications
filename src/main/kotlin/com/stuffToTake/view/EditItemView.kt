package com.stuffToTake.view

import com.stuffToTake.controllers.ConfirmController
import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.models.*
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.text.FontWeight
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

    private val ownPrefWidth: Double = 500.0
    private val ownPrefHeight: Double = 350.0


    override val root = gridpane {
        // set size
        setPrefSize(ownPrefWidth, ownPrefHeight)

        fieldset("Name") {
            textfield(itemName).required()

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(0, 1)
            }
            style {
                setPrefWidth(ownPrefWidth / 2)
            }
            // only affects the text "Name"
            this.children[0].style {
                fontWeight = FontWeight.BOLD
            }
        }

        fieldset("Amount") {
            textfield(itemAmount)

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(0, 2)
            }
            style {
                setPrefWidth(ownPrefWidth / 2)
            }
            // only affects the text "Name"
            this.children[0].style {
                fontWeight = FontWeight.BOLD
            }
        }

        fieldset("Type") {
            combobox(itemType, observableListOf("Essential Item", "Optional Item", "One Time Item")).required()

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(0, 3)
            }
            style {
                setPrefWidth(ownPrefWidth / 2)
            }
            // only affects the text "Name"
            this.children[0].style {
                fontWeight = FontWeight.BOLD
            }
        }

        fieldset("Categories") {
            add(categoriesListView)

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(1, 1)
                rowSpan = 3
            }
            style {
                setPrefWidth(ownPrefWidth / 2)
            }
            // only affects the text "Name"
            this.children[0].style {
                fontWeight = FontWeight.BOLD
            }
        }


        checkbox("To Take", itemToTake) {
            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(0, 4)
                columnSpan = 2
            }
            style {
                alignment = Pos.CENTER
                padding = box(10.px)
                fontWeight = FontWeight.BOLD
            }
        }


        button("Delete") {
            action {
                // Show window to check if user is sure
                confirmController.confirmDeletion(item)

                runLater {
                    // wait until the pop-up window is closed
                    find(ConfirmView::class).whenUndockedOnce {
                        // if the confirmation is given, apply the changes
                        if (confirmController.confirmed)
                            itemsListController.deleteItem(item)
                    }
                }
            }

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(0, 5)
            }
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        button("Save Changes") {
            action {
                // Show window to check if user is sure
                confirmController.confirmChanges(item)

                runLater {
                    // wait until the pop-up window is closed
                    find(ConfirmView::class).whenUndockedOnce {
                        // if the confirmation is given, apply the changes
                        if (confirmController.confirmed)
                            itemsListController.saveItemChanges(
                                item,
                                itemName.value, itemAmount.value, itemType.value,
                                categoriesListView.selectionModel.selectedItems, itemToTake.value
                            )
                    }
                }
            }

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(1, 5)
            }
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        // style attributes that are applied to every child
        children.style(append = true) {
            fontSize = 15.px
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
