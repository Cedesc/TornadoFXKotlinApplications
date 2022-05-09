package com.stuffToTake.models

import com.stuffToTake.saves.ItemParser
import javafx.beans.property.SimpleListProperty
import javafx.collections.ObservableList
import tornadofx.*

class ItemsList(essentialItems: MutableList<EssentialItem> = mutableListOf(),
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


    /**
     * Returns always true because "add" returns true too.
     */
    fun addEssentialItem(item: EssentialItem): Boolean = essentialItems.add(item)

    /**
     * Returns always true because "add" returns true too.
     */
    fun addOptionalItem(item: OptionalItem): Boolean = optionalItems.add(item)

    /**
     * Returns always true because "add" returns true too.
     */
    fun addOneTimeItem(item: OneTimeItem): Boolean = oneTimeItems.add(item)

    /**
     * Checks which item type is given and call the respective function.
     */
    fun addArbitraryItem(item: AbstractItem): Boolean {
        return when (item) {
            is EssentialItem -> addEssentialItem(item)
            is OptionalItem -> addOptionalItem(item)
            is OneTimeItem -> addOneTimeItem(item)
            is ShowedItem -> {
                println("Warning! Tried to add a ShowedItem to the item list.")
                false
            }
            else -> throw Exception("Given item is not valid")
        }
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
     * Saves the items in this instance in the txt file.
     */
    fun saveItems(): Boolean {

        // Return false if the item parser has an empty filepath.
        if (itemParser.filepath.isEmpty())
            return false

        itemParser.codeToTxt(essentialItems, optionalItems, oneTimeItems)
        return true
    }

}
