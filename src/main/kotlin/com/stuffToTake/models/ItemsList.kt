package com.stuffToTake.models

import tornadofx.ItemViewModel

class ItemsList(val essentialItems: MutableList<EssentialItem> = mutableListOf(),
                val optionalItems: MutableList<OptionalItem> = mutableListOf(),
                val oneTimeItems: MutableList<OneTimeItem> = mutableListOf()) {

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

class ItemsListModel : ItemViewModel<ItemsList>()  // TODO is this necessary???
