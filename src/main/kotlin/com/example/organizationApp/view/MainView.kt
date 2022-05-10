package com.example.organizationApp.view

import com.example.organizationApp.styles.Styles
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    override val root = vbox {

        label(title) {
            addClass(Styles.heading)
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
