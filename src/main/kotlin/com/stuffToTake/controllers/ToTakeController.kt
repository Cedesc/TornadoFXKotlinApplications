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
     * // TODO Idea:
    //  a click on the button "To ToTake-View" first create an entry in history,
    //  then call the function of each element "markAsToTake"
    //  or "unmarkAsToTake" depending on the toTake checkBox
    //  (so maybe it should be called "markedAsToTake" and "unmarkedAsToTake")
    //  to call the related functions for the Essential Items, Optional Items and One Time Items.
    //  Essential Items will stay as or changed to "toTake".
    //  Optional Items will be set the identical Optional Item in the other list to "toTake" if it wasn't.
    //      This is possible because the ShowedItem.toTake can be different from the originalItem.toTake and the
    //      ShowedItem.markedAsToTake() can call the originalItem.markedAsToTake() which could get the ShowedItem.toTake
    //      as input.
    //  One Time Items will be deleted if they was "toTake", and nothing happens otherwise.
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