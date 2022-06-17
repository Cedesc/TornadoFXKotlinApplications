package com.stuffToTake.controllers

import com.stuffToTake.models.ShowItem
import com.stuffToTake.view.ToTakeView
import javafx.collections.ObservableList
import tornadofx.*

class ToTakeController: Controller() {

    private val itemsListController: ItemsListController by inject()

    var itemsListToTake: ObservableList<ShowItem> = observableListOf()


    fun toToTakeView(selectedItems: ObservableList<ShowItem>) {
        itemsListToTake.setAll(selectedItems)
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
     * // TODO Idea: (write as documentation)
    //  a click on the button "To ToTake-View" first create an entry in history,
    //  then call the function of each element (ShowItem) "checked", which calls "markAsToTake"
    //  or "unmarkAsToTake" depending on the toTake checkBox,
    //  to call the related functions for the Essential Items, Optional Items and One Time Items.
    //  Essential Items will stay as or changed to "toTake".
    //  Optional Items will set the identical Optional Item in the other list to "toTake" if it wasn't.
    //      This is possible because the ShowedItem.toTake can be different from the originalItem.toTake and the
    //      ShowedItem.markedAsToTake() can call the originalItem.markedAsToTake() which could get the ShowedItem.toTake
    //      as input.
    //  One Time Items will be deleted if they was "toTake", and nothing happens otherwise.
     */
    fun finish() {
        // TODO error if result is 2.
        //  Suspected Problem: The iteration list changes.
        //  Proposal: Instead of deleting the items in the iteration, fill a list with this items and
        //            delete them after the iteration.
        itemsListController.selectedItemList.observableShowItems.forEach { showItem ->
            when(showItem.checked()) {
                // Nothing happens.
                0 -> Unit
                // Identical optional item in the other list will be changed, if any.
                1 -> itemsListController.changeToTakeOfOptionalItem(showItem.originalItem)
                // Delete the one time item.
                2 -> itemsListController.deleteItem(showItem.originalItem)
                else -> throw Exception("Return value isn't a valid number.")
            }
        }
        // TODO save the itemsList for HistoryView
        closeToTakeView()
    }

    /**
     * Closes the modal without saving the items in the history.
     */
    fun closeToTakeView() {
        find(ToTakeView::class).close()
    }

    /**
     * Returns the name of the list.
     */
    fun getItemListName(): String {
        return itemsListController.selectedItemList.name
    }

}