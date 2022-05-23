package com.stuffToTake.controllers

import com.stuffToTake.models.ShowItem
import com.stuffToTake.view.ToTakeView
import javafx.collections.ObservableList
import tornadofx.*

class ToTakeController: Controller() {

    var itemsListToTake: ObservableList<ShowItem> = observableListOf()

    fun toToTakeView(showItemsList: ObservableList<ShowItem>) {
        itemsListToTake = showItemsList
        find(ToTakeView::class).openModal()
    }

    /**
     * Returns a (deep-) copied observable list of the given Show Items.
     */
    fun createCopiedListOfSelectedItems(showItemsList: ObservableList<ShowItem>): ObservableList<ShowItem> {
        val selectedItems: ObservableList<ShowItem> =
            showItemsList.filtered { it.toTake }.toObservable()
        val copiedList = observableListOf<ShowItem>()
        selectedItems.forEach {
            copiedList.add(it.copy())
        }
        return copiedList
    }

}