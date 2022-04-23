package com.stuffToTake.models

import tornadofx.ItemViewModel

class ItemsList(val essentialItems: MutableList<EssentialItem>,
                val optionalItems: MutableList<OptionalItem>,
                val oneTimeItems: MutableList<OneTimeItem>) {  // TODO create tests
    // TODO fill the lists in a constructor with no parameters with the txt file??

    var hey: String = "Hey"  // TODO delete

    fun addEssentialItem(item: EssentialItem): Boolean {
        return essentialItems.add(item)
    }

    fun addOptionalItem(item: OptionalItem): Boolean {
        return optionalItems.add(item)
    }

    fun addOneTimeItem(item: OneTimeItem): Boolean {
        return oneTimeItems.add(item)
    }

    fun addItem(item: AbstractItem): Boolean {
        return when (item) {
            is EssentialItem -> essentialItems.add(item)
            is OptionalItem -> optionalItems.add(item)
            is OneTimeItem -> oneTimeItems.add(item)
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
