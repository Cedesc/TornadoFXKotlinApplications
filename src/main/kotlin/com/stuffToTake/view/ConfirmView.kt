package com.stuffToTake.view

import com.stuffToTake.controllers.ConfirmController
import com.stuffToTake.models.AbstractItem
import tornadofx.*

class ConfirmView : View("PLACEHOLDER") {

    private val confirmController: ConfirmController by inject()


    override val root = vbox {

        label {
            bind(confirmController.text)
        }

        hbox {
            button("Yes") {
                action {
                    confirmController.setToYes()
                }
            }
            button("No") {
                action {
                    confirmController.setToNo()
                }
            }
        }

    }


    override fun onDock() {
        title = confirmController.title.value
    }

}
