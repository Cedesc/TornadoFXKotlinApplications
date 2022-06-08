package com.stuffToTake.view

import com.stuffToTake.controllers.ConfirmController
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.*

class ConfirmView : View("PLACEHOLDER") {

    private val confirmController: ConfirmController by inject()

    private val ownPrefWidth: Double = 300.0
    private val ownPrefHeight: Double = 180.0


    override val root = vbox {
        // set size
        setPrefSize(ownPrefWidth, ownPrefHeight)

        label {
            bind(confirmController.text)

            prefHeight = ownPrefHeight * 3 / 4
            style {
                alignment = Pos.CENTER
                textAlignment = TextAlignment.CENTER
                padding = box(10.px)
                fontSize = 18.px
                fontWeight = FontWeight.BOLD
                wrapText = true
            }
        }

        hbox {
            button("Yes") {
                action {
                    confirmController.setToYes()
                }

                prefWidth = ownPrefWidth / 2
                prefHeight = ownPrefHeight / 4
                style {
                    alignment = Pos.CENTER
                    padding = box(10.px)
                    fontSize = 15.px
                    fontWeight = FontWeight.BOLD
                }
            }
            button("No") {
                action {
                    confirmController.setToNo()
                }

                prefWidth = ownPrefWidth / 2
                prefHeight = ownPrefHeight / 4
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
        title = confirmController.title.value
    }

}
