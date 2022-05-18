package com.stuffToTake.controllers

import com.stuffToTake.models.*
import com.stuffToTake.saves.ItemParser
import com.stuffToTake.view.EditItemView
import javafx.collections.ObservableList
import tornadofx.Controller
import tornadofx.observableListOf

class ItemsListController : Controller() {

    val itemsListToMainz =
        ItemsList("To Mainz",
            itemParser = ItemParser("src/main/kotlin/com/stuffToTake/saves/toMainzItems.txt"))

    val itemsListToWW =
        ItemsList("To WW",
            itemParser = ItemParser("src/main/kotlin/com/stuffToTake/saves/toWWItems.txt"))


    init {
        // Fill the items list with the saved items.
        if (! itemsListToMainz.loadSavedItems())
            throw Exception("The item list isn't empty or the filepath of the item parser is empty.")
        if (! itemsListToWW.loadSavedItems())
            throw Exception("The item list isn't empty or the filepath of the item parser is empty.")
    }

    var selectedItemList: ItemsList = ItemsList("PLACEHOLDER")
    val selectedObservableList: ObservableList<ShowItem> = observableListOf()


    fun addItem(name: String, amount: String, type: String, categories: List<Category>,
                toTake: Boolean, toMainz: Boolean, toWW: Boolean) {

        val item: AbstractItem = when(type) {
            "Essential Item" -> EssentialItem(name, amount, toTake)
            "Optional Item" -> OptionalItem(name, amount, toTake)
            "One Time Item" -> OneTimeItem(name, amount, toTake)
            else -> throw Exception("Given type is not valid")
        }

        categories.forEach { category ->
            if (! item.addCategory(category))
                println("Warning! Tried to add a category twice to an item.")
        }

        // println(item)  // TODO delete

        if (toMainz)
            itemsListToMainz.addArbitraryItem(item)
        if (toWW)
            itemsListToWW.addArbitraryItem(item)

    }

    fun saveItems() {
        itemsListToMainz.saveItems()
        itemsListToWW.saveItems()
    }

    fun changeSelectedListToMainz() {
        selectedItemList = itemsListToMainz
        selectedObservableList.setAll(selectedItemList.observableShowItems)
    }

    fun changeSelectedListToWW() {
        selectedItemList = itemsListToWW
        selectedObservableList.setAll(selectedItemList.observableShowItems)
    }

    fun toEditItemView(showItem: ShowItem) {
        find<EditItemView>(mapOf(EditItemView::item to showItem.originalItem)).openWindow()
        // TODO deactivate current window ?
    }

    fun deleteItem() {
        TODO("Not yet implemented")

        // Find item in the respective list

        // Throw Exception if two identical items are found or no item was found

        // Delete item

        // Refresh the lists of show items

    }

    fun saveItemChanges() {
        TODO("Not yet implemented")

        // Create new item of the given parameters

        // Check if created item and old item are same, if so give a warning

        // Delete old item

        // Add new item

        // Refresh the list of show items

    }

}
