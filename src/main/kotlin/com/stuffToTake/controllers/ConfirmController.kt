package com.stuffToTake.controllers

import com.stuffToTake.models.AbstractItem
import com.stuffToTake.view.ConfirmView
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class ConfirmController : Controller() {

    val title = SimpleStringProperty("")
    val text = SimpleStringProperty("")

    var confirmed: Boolean = false
    get() {
        // A positive "confirmed" can only get once per function call (with confirmDeletion or confirmChanges)
        return field.also {
            confirmed = false
        }
    }


    /**
     * Opens new window which asks if the selected item should be deleted or not.
     */
    fun confirmDeletion(item: AbstractItem) {
        title.value = "Confirm Deletion"
        text.value = "Are you sure you want to delete \n\"${item.name}\"?"
        find(ConfirmView::class).openModal()
    }

    /**
     * Opens new window which asks if the made changes should be saved or not.
     */
    fun confirmChanges(item: AbstractItem) {
        title.value = "Confirm Changes"
        text.value = "Are you sure you want to apply the changes to \n\"${item.name}\"?"
        find(ConfirmView::class).openModal()
    }

    /**
     * Sets result to true and closed window. Function that should be called when user clicked "Yes".
     */
    fun setToYes() {
        confirmed = true
        runLater { find(ConfirmView::class).close() }
    }

    /**
     * Sets result to false and closed window. Function that should be called when user clicked "No".
     */
    fun setToNo() {
        confirmed = false
        runLater { find(ConfirmView::class).close() }
    }

}
