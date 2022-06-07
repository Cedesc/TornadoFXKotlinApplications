package com.stuffToTake.view

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.controllers.MenuController
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.*

class MenuView : View("Menu") {

    private val menuController: MenuController by inject()
    private val itemsController: ItemsListController by inject()

    override val root = gridpane {
        // set size
        setPrefSize(435.0, 180.0)

        button("Add Item") {
            action {
                menuController.toAddItemView()
            }

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(0, 0)
                columnSpan = 2
                marginBottom = 10.0
            }
        }
        button("Full List of \"To Mainz\"-Items") {
            action {
                itemsController.changeSelectedListToMainz()
                menuController.toListOfItemsView()
            }

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(0, 1)
            }
        }
        button("Full List of \"To WW\"-Items") {
            action {
                itemsController.changeSelectedListToWW()
                menuController.toListOfItemsView()
            }

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(1, 1)
            }
        }
        button("Choose Items for Mainz") {
            action {
                itemsController.changeSelectedListToMainz()
                menuController.toChooseItemsView()
            }

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(0, 2)
            }
        }
        button("Choose Items for WW") {
            action {
                itemsController.changeSelectedListToWW()
                menuController.toChooseItemsView()
            }

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(1, 2)
            }
        }
        button("History View") {
            action {
                menuController.toHistoryView()
            }

            useMaxSize = true
            gridpaneConstraints {
                columnRowIndex(0, 3)
                columnSpan = 2
                marginTop = 10.0
            }
        }

        // style attributes that are applied to every child
        children.style(append = true) {
            padding = box(10.px)
            wrapText = true
            fontWeight = FontWeight.BOLD
            fontSize = 15.px
            textAlignment = TextAlignment.CENTER
        }

    }
}
