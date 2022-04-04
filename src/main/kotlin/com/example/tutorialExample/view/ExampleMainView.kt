package com.example.tutorialExample.view

import com.example.tutorialExample.ExampleStyles
import tornadofx.*

class ExampleMainView : View("Hello TornadoFX") {
    override val root = vbox {

        label(title) {
            addClass(ExampleStyles.heading)
        }

        label("Hello again")

        button {
            this.text = "click me!"
            action {
                print("Button Clicked")
            }
        }

        flowpane {
            for (i in 1..50) {
                button(i.toString()) {
                    setOnAction { println("You pressed $i") }
                }
            }
        }
    }
}