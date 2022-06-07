package com.stuffToTake.view

import javafx.scene.control.ToggleButton
import javafx.scene.paint.Color
import tornadofx.*

class OwnButton(text: String) : ToggleButton(text) {

    init {
        style { this.backgroundColor += Color.GREEN }
    }

    override fun fire() {
        setColorToSelected()
        println(isSelected)
        super.fire()
    }

    /**
     * Paints the button green if it is selected, paints it red if it isn't selected.
     */
    fun setColorToSelected() {
        style {
            if (isSelected) {
                this.backgroundColor += Color.GREEN
            }
            else {
                this.backgroundColor += Color.RED
            }
        }
    }

}
