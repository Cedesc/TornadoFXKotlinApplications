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


    // TODO add function that fill the lists with the txt file

    fun addEssentialItem(item: EssentialItem): Boolean {
        return essentialItems.add(item)
    }

    fun addOptionalItem(item: OptionalItem): Boolean {
        return optionalItems.add(item)
    }

    fun addOneTimeItem(item: OneTimeItem): Boolean {
        return oneTimeItems.add(item)
    }

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

    fun getListOfAllItems(): MutableList<AbstractItem> {
        val result: MutableList<AbstractItem> = mutableListOf()
        result += essentialItems + optionalItems + oneTimeItems
        return result
    }

}
