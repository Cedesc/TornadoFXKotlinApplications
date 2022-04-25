package com.stuffToTake.view

import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class TestView : View("My View") {  // TODO delete

    var test = SimpleIntegerProperty(1)

    override val root = vbox {

        button("plus one") {
            action {
                test.value++
            }
        }

        label("Value: ${test.value}") {
            bind(test)
        }

    }
}
