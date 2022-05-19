package com.stuffToTake.controllers

import com.stuffToTake.models.AbstractItem
import com.stuffToTake.view.ConfirmView
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class ConfirmController : Controller() {  // TODO create tests

    val title = SimpleStringProperty("")
    val text = SimpleStringProperty("")

    var result: Boolean? = null
    get() {        // Result can only get once per function call (with confirmDeletion or confirmChanges)
        if (field == null)
            throw Exception("No confirmation asked, so no result present")
        val current = field
        result = null
        return current
    }


    fun confirmDeletion(item: AbstractItem) {  // TODO test
        title.value = "Confirm Deletion"
        text.value = "Are you sure you want to delete \"${item.name}\"?"
        find(ConfirmView::class).openModal()
    }

    fun confirmChanges(item: AbstractItem) {  // TODO test
        title.value = "Confirm Changes"
        text.value = "Are you sure you want to apply the changes to \"${item.name}\"?"
        find(ConfirmView::class).openModal()
    }

    fun setToYes() {
        result = true
    }

    fun setToNo() {
        result = false
    }

}
