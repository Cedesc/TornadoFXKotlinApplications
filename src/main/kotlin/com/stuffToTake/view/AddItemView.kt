package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import com.stuffToTake.models.Category
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.text.FontWeight
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

    private val ownPrefWidth: Double = 400.0
    private val ownPrefHeight: Double = 350.0

    override val root = gridpane {
        // set size
        setPrefSize(ownPrefWidth, ownPrefHeight)

        checkbox("To Mainz", toWW) {
            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(0, 0)
            }
            style {  // TODO repetitive, bring together
                alignment = Pos.CENTER
                padding = box(10.px)
                fontWeight = FontWeight.BOLD
            }
        }
        checkbox("To WW", toMainz) {
            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(1, 0)
            }
            style {
                alignment = Pos.CENTER
                padding = box(10.px)
                fontWeight = FontWeight.BOLD
            }
        }

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
            categoriesListView.selectionModel.selectionMode = SelectionMode.MULTIPLE
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

        button("Back to Menu") {
            action {
                menuController.backToMenuView()
            }

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(0, 5)
            }
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        button("Create Item") {
            // Only enabled if the model is valid (means that all .required() fields are filled)
            // and toMainz or toWW is checked.
            enableWhen(model.valid.and(toMainz.or(toWW)))

            isDefaultButton = true
            action {
                itemsListController.addItem(
                    itemName.value, itemAmount.value, itemType.value,
                    categoriesListView.selectionModel.selectedItems,
                    itemToTake.value, toMainz.value, toWW.value
                )

                // clear fields and disable button = reset view
                menuController.refresh()
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
