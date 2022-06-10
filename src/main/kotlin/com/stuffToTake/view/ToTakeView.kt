package com.stuffToTake.view

import com.stuffToTake.controllers.ToTakeController
import com.stuffToTake.models.ShowItem
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.control.SelectionMode
import javafx.scene.text.FontWeight
import tornadofx.*

class ToTakeView : View("To Take View") {

    private val toTakeController: ToTakeController by inject()

    private val items: SimpleObjectProperty<ObservableList<ShowItem>> =
        SimpleObjectProperty(toTakeController.itemsListToTake)

    private val ownPrefWidth: Double = 300.0
    private val ownPrefHeight: Double = 800.0


    override val root = vbox {
        // set size
        setPrefSize(ownPrefWidth, ownPrefHeight)

        tableview(items) {
            id = "toTakeList"
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
                    toTakeController.closeToTakeView()
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

            button("Finished") {
                action {
                    toTakeController.finish()
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
        title = toTakeController.getItemListName()
        items.set(toTakeController.itemsListToTake)
        super.onDock()
    }
}
