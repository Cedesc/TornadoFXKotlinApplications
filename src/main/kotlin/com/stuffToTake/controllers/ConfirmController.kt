package com.stuffToTake.controllers

import com.stuffToTake.models.AbstractItem
import com.stuffToTake.view.ConfirmView
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class ConfirmController : Controller() {

    val title = SimpleStringProperty("")
    val text = SimpleStringProperty("")

    var result: Boolean? = null
    get() {
        // Result can only get once per function call (with confirmDeletion or confirmChanges)
        if (field == null)
            throw Exception("No confirmation asked, so no result present")
        val current = field
        result = null
        return current
    }


    /**
     * Opens new window which asks if the selected item should be deleted or not.
     */
    fun confirmDeletion(item: AbstractItem) {  // TODO test
        title.value = "Confirm Deletion"
        text.value = "Are you sure you want to delete \"${item.name}\"?"
        find(ConfirmView::class).openModal()
    }

    /**
     * Opens new window which asks if the made changes should be saved or not.
     */
    fun confirmChanges(item: AbstractItem) {  // TODO test
        title.value = "Confirm Changes"
        text.value = "Are you sure you want to apply the changes to \"${item.name}\"?"
        find(ConfirmView::class).openModal()
    }

    /**
     * Sets result to true. Function that should be called when user clicked "Yes".
     */
    fun setToYes() {
        result = true
    }

    /**
     * Sets result to false. Function that should be called when user clicked "No".
     */
    fun setToNo() {
        result = false
    }

}
