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

        val item: AbstractItem = createItem(name, amount, type, categories, toTake)

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
        find<EditItemView>(mapOf(EditItemView::item to showItem.originalItem)).openModal()
    }

    fun deleteItem(item: AbstractItem) {  // TODO test this function

        // Throw Exception if two identical items are found or no item was found.
        if (! selectedItemList.deleteArbitraryItem(item))
            throw Exception("No or multiple identical items are found. Exactly one match must exist.")

        // TODO list should update here
        // TODO edit item window should be closed here

        println("Deleted \n$item\n\n")  // TODO delete

    }

    fun saveItemChanges(originalItem: AbstractItem, name: String, amount: String, type: String, categories: List<Category>,
                        toTake: Boolean) {  // TODO test this function

        // Create new item of the given parameters.
        val editedItem: AbstractItem = createItem(name, amount, type, categories, toTake)

        // Check if created item and old item are same, if so give a warning.
        if (editedItem == originalItem)
            println("Warning! No changes were made")

        // Save item changes
        if (! selectedItemList.editArbitraryItem(originalItem, editedItem))
            throw Exception("Something went wrong while saving the changes of the edited item.")

        // TODO list should update here
        // TODO edit item window should be closed here

        println("Changed from \n$originalItem\nto \n$editedItem\n\n")  // TODO delete

    }

    private fun createItem(name: String, amount: String, type: String, categories: List<Category>,
                           toTake: Boolean): AbstractItem {
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

        return item
    }

}
