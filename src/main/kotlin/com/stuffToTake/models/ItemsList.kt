package com.stuffToTake.models

import javafx.beans.property.SimpleListProperty
import javafx.collections.ObservableList
import tornadofx.*

class ItemsList(essentialItems: MutableList<EssentialItem> = mutableListOf(),
                optionalItems: MutableList<OptionalItem> = mutableListOf(),
                oneTimeItems: MutableList<OneTimeItem> = mutableListOf()) {

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
    fun addEssentialItem(item: EssentialItem): Boolean {
        return essentialItems.add(item)
    }

    /**
     * Returns always true because "add" returns true too.
     */
    fun addOptionalItem(item: OptionalItem): Boolean {
        return optionalItems.add(item)
    }

    /**
     * Returns always true because "add" returns true too.
     */
    fun addOneTimeItem(item: OneTimeItem): Boolean {
        return oneTimeItems.add(item)
    }

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
    fun loadSavedItems() {
        TODO("Not yet implemented")
    }

}
