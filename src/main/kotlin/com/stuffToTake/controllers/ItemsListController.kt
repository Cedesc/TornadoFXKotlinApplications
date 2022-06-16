package com.stuffToTake.controllers

import com.stuffToTake.models.*
import com.stuffToTake.saves.ItemParser
import com.stuffToTake.view.EditItemView
import tornadofx.Controller

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
    }

    fun changeSelectedListToWW() {
        selectedItemList = itemsListToWW
    }

    fun toEditItemView(showItem: ShowItem) {
        find<EditItemView>(mapOf(EditItemView::item to showItem.originalItem)).openModal()
    }

    fun deleteItem(item: AbstractItem) {
        // Throw Exception if two identical items are found or no item was found.
        if (! selectedItemList.deleteArbitraryItem(item))
            throw Exception("No or multiple identical items are found. Exactly one match must exist.")

        println("Deleted \n$item\n")  // TODO delete!
    }

    fun manualDeleteItem(item: AbstractItem) {
        // Delete item
        deleteItem(item)

        // Close the "Edit Item"-View
        find(EditItemView::class).close()

        println("Deleted \n$item\n")
    }

    private fun saveItemChanges(originalItem: AbstractItem, editedItem: AbstractItem,
                        chosenItemList: ItemsList = selectedItemList) {
        // Save item changes
        if (! chosenItemList.editArbitraryItem(originalItem, editedItem))
            throw Exception("Something went wrong while saving the changes of the edited item.")

        println("Changed from \n$originalItem\nto \n$editedItem\n")  // TODO delete!
    }

    fun manualSaveItemChanges(originalItem: AbstractItem, name: String, amount: String, type: String,
                              categories: List<Category>, toTake: Boolean) {

        // Create new item of the given parameters.
        val newItem: AbstractItem = createItem(name, amount, type, categories, toTake)

        // Check if created item and old item are same, if so give a warning and return.
        if (newItem == originalItem) {
            println("Warning! No changes were made")
            return
        }

        // Save item changes
        saveItemChanges(originalItem, newItem)

        // Close the "Edit Item"-View
        find(EditItemView::class).close()

        println("Changed from \n$originalItem\nto \n$newItem\n")

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

    /**
     * ToTake of the identical optional item in the other list will be changed, if any.
     */
    fun changeToTakeOfOptionalItem(item: AbstractItem) {

        // Create the edited item
        val editedItem = OptionalItem(item.name, item.amount, true, item.categories)

        // Get the other list
        val otherList: ItemsList = getUnselectedList()

        // Check if the item is in the other list
        if (otherList.checkIfOptionalItemExists(editedItem))
            // Save changes
            saveItemChanges(item, editedItem, chosenItemList = otherList)

    }

    /**
     * Returns the list that isn't currently selected.
     */
    private fun getUnselectedList(): ItemsList {
        return when(selectedItemList.name) {
            itemsListToWW.name -> itemsListToMainz
            itemsListToMainz.name -> itemsListToWW
            else -> throw Exception("Not a valid items list selected.")
        }
    }

    fun refreshItemsLists() {
        itemsListToMainz.refreshShowItems()
        itemsListToWW.refreshShowItems()
    }

}
