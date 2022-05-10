package com.stuffToTake.controllers

import com.stuffToTake.models.*
import com.stuffToTake.saves.ItemParser
import tornadofx.Controller

class ItemsListController : Controller() {

    val itemsListToMainz =
        ItemsList(itemParser = ItemParser("src/main/kotlin/com/stuffToTake/saves/toMainzItems.txt"))
    val itemsListToWW =
        ItemsList(itemParser = ItemParser("src/main/kotlin/com/stuffToTake/saves/toWWItems.txt"))

    init {
        // Fill the items list with the saved items.
        if (! itemsListToMainz.loadSavedItems())
            throw Exception("The item list isn't empty or the filepath of the item parser is empty.")
        if (! itemsListToWW.loadSavedItems())
            throw Exception("The item list isn't empty or the filepath of the item parser is empty.")
    }

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

}
