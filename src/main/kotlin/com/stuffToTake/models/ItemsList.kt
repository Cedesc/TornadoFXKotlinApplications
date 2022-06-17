package com.stuffToTake.models

import com.stuffToTake.saves.ItemParser
import javafx.beans.property.SimpleListProperty
import javafx.collections.ObservableList
import tornadofx.*

class ItemsList(val name: String,
                essentialItems: MutableList<EssentialItem> = mutableListOf(),
                optionalItems: MutableList<OptionalItem> = mutableListOf(),
                oneTimeItems: MutableList<OneTimeItem> = mutableListOf(),
                var itemParser: ItemParser = ItemParser("")) {

    val essentialItemsProperty: SimpleListProperty<EssentialItem> =
        SimpleListProperty(essentialItems.toObservable())
    val essentialItems: ObservableList<EssentialItem> by essentialItemsProperty

    val optionalItemsProperty: SimpleListProperty<OptionalItem> =
        SimpleListProperty(optionalItems.toObservable())
    val optionalItems: ObservableList<OptionalItem> by optionalItemsProperty

    val oneTimeItemsProperty: SimpleListProperty<OneTimeItem> =
        SimpleListProperty(oneTimeItems.toObservable())
    val oneTimeItems: ObservableList<OneTimeItem> by oneTimeItemsProperty

    val observableShowItems: ObservableList<ShowItem> = listOf<ShowItem>().toObservable()

    init {
        refreshShowItems()
    }


    /**
     * Returns always true (like the "add"-function).
     */
    fun addEssentialItem(item: EssentialItem): Boolean {
        essentialItems.add(item)
        return true
    }

    /**
     * Returns always true (like the "add"-function).
     */
    fun addOptionalItem(item: OptionalItem): Boolean {
        optionalItems.add(item)
        return true
    }

    /**
     * Returns always true (like the "add"-function).
     */
    fun addOneTimeItem(item: OneTimeItem): Boolean {
        oneTimeItems.add(item)
        return true
    }

    /**
     * Checks which item type is given and call the respective function.
     */
    fun addArbitraryItem(item: AbstractItem, giveWarning: Boolean = true): Boolean {
        val result: Boolean = when (item) {
            is EssentialItem -> addEssentialItem(item)
            is OptionalItem -> addOptionalItem(item)
            is OneTimeItem -> addOneTimeItem(item)
            is ShowItem -> {
                if (! giveWarning)
                    println("Warning! Tried to add a ShowedItem to the item list.")
                false
            }
            else -> throw Exception("Given item is not valid")
        }

        // Refresh list of show items
        refreshShowItems()

        return result

    }

    /**
     * Returns one list containing all items.
     */
    fun getListOfAllItems(): MutableList<AbstractItem> {
        val result: MutableList<AbstractItem> = mutableListOf()
        result += essentialItems + optionalItems + oneTimeItems
        return result
    }

    /**
     * Fills the instance with the items saved in the txt file.
     */
    fun loadSavedItems(): Boolean {

        // Return false if the ItemList isn't empty.
        if (! (essentialItems.isEmpty() && optionalItems.isEmpty() && oneTimeItems.isEmpty()))
            return false
        // Return false if the item parser has an empty filepath.
        if (itemParser.filepath.isEmpty())
            return false

        for (item in itemParser.txtToCode())
            addArbitraryItem(item)
        return true
    }

    /**
     * Saves the items in this instance in the txt file. Additionally, it creates a backup.
     */
    fun saveItems(): Boolean {

        // Return false if the item parser has an empty filepath.
        if (itemParser.filepath.isEmpty())
            return false

        itemParser.codeToTxt(essentialItems, optionalItems, oneTimeItems)
        itemParser.createBackup(inSingleFile = false)
        return true
    }

    /**
     * Updates complete list of the ShowItems.
     */
    fun refreshShowItems() {
        observableShowItems.setAll(
            getListOfAllItems().map { item ->
                ShowItem(item)
            }.toObservable()
        )
    }

    /**
     * Deletes an item in essentialItems. Returns false if multiple or no matches were found.
     */
    private fun deleteEssentialItem(item: EssentialItem): Boolean {
        // Return false if no or multiple identical items were found.
        if (essentialItems.count { it == item } != 1)
            return false

        // Delete item
        essentialItems.remove(item)
        return true
    }

    /**
     * Deletes an item in optionalItems. Returns false if multiple or no matches were found.
     */
    private fun deleteOptionalItem(item: OptionalItem): Boolean {
        // Return false if no or multiple identical items were found.
        if (optionalItems.count { it == item } != 1)
            return false

        // Delete item
        optionalItems.remove(item)
        return true
    }

    /**
     * Deletes an item in oneTimeItems. Returns false if multiple or no matches were found.
     */
    private fun deleteOneTimeItem(item: OneTimeItem): Boolean {
        // Return false if no or multiple identical items were found.
        if (oneTimeItems.count { it == item } != 1)
            return false

        // Delete item
        oneTimeItems.remove(item)
        return true
    }

    /**
     * Checks which item type is given and call the respective function.
     */
    fun deleteArbitraryItem(item: AbstractItem): Boolean {
        val result: Boolean = when (item) {
            is EssentialItem -> deleteEssentialItem(item)
            is OptionalItem -> deleteOptionalItem(item)
            is OneTimeItem -> deleteOneTimeItem(item)
            is ShowItem -> {
                println("Warning! Tried to delete a ShowedItem of the item list.")
                false
            }
            else -> throw Exception("Given item is not valid")
        }

        // Refresh list of show items
        refreshShowItems()

        return result
    }

    /**
     * Edits (delete and add) an item.
     * Returns false if the "delete"- or the "add"- function returns false.
     */
    fun editArbitraryItem(originalItem: AbstractItem, editedItem: AbstractItem): Boolean {

        // Check if created item and old item are the same, if so return false.
        // Mandatory because otherwise the delete function won't work.
        if (originalItem == editedItem)
            return false

        // First the add-function because if it cannot be added, the old element won't be deleted.
        if (! addArbitraryItem(editedItem))
            return false
        if (! deleteArbitraryItem(originalItem)) {
            println("Warning! New item has been added but old item wasn't removed!")
            return false
        }

        // Refresh list of show items
        refreshShowItems()

        return true
    }

    /**
     * Returns true if the item is exactly once in the optionalItems-List unmarked, returns false if it isn't.
     * An error will be thrown if there are multiple copies of this item in the list because this shouldn't be possible.
     * To check if the item exists, the name and the item type (in form of the first line of the string) are compared.
     * This isn't a good solution, but it works for ONLY this purpose.
     */
    fun checkIfUnmarkedOptionalItemExists(item: AbstractItem): Boolean {
        return when(optionalItems.count { (it.toString().lines()[0] == item.toString().lines()[0]) && !it.toTake}) {
            0 -> false
            1 -> true
            else -> throw Exception("Multiple copies of the item are in the list. This shouldn't be possible.")
        }
    }

}
