package com.stuffToTake.controllers

import com.stuffToTake.models.ShowItem
import com.stuffToTake.view.ToTakeView
import javafx.collections.ObservableList
import tornadofx.*

class ToTakeController: Controller() {

    var itemsListToTake: ObservableList<ShowItem> = observableListOf()

    fun toToTakeView(showItemsList: ObservableList<ShowItem>) {
        itemsListToTake.setAll(showItemsList)
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

    /**
     * Closes the modal and saves the items in the history.
     */
    fun finish() {
        TODO("Not yet implemented")  // see ChooseItemsView.kt Idea and notices application of the smartphone
    }

    /**
     * Closes the modal without saving the items in the history.
     */
    fun closeToTakeView() {
        find(ToTakeView::class).close()
    }

}