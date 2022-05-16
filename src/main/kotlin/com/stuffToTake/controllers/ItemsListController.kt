package com.stuffToTake.controllers

import com.stuffToTake.models.*
import com.stuffToTake.saves.ItemParser
import javafx.collections.ObservableList
import tornadofx.Controller
import tornadofx.toObservable

class ItemsListController : Controller() {

    val itemsListToMainz =
        ItemsList(itemParser = ItemParser("src/main/kotlin/com/stuffToTake/saves/toMainzItems.txt"))

    val itemsListToWW =
        ItemsList(itemParser = ItemParser("src/main/kotlin/com/stuffToTake/saves/toWWItems.txt"))

    var showItemsToMainz: ObservableList<ShowedItem>
    // TODO create showItemsToWW


    init {
        // Fill the items list with the saved items.
        if (! itemsListToMainz.loadSavedItems())
            throw Exception("The item list isn't empty or the filepath of the item parser is empty.")
        if (! itemsListToWW.loadSavedItems())
            throw Exception("The item list isn't empty or the filepath of the item parser is empty.")

        showItemsToMainz = itemsListToMainz.getListOfAllItems().map { item ->  // TODO uncomment it
            ShowedItem(item)
        }.toObservable()
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

        showItemsToMainz.add(ShowedItem(item))  // TODO make this prettier
                                                // 1. automatically add to showItemsToMainz (with add function here?)
                                                // 2. make

    }

    fun saveItems() {
        itemsListToMainz.saveItems()
        itemsListToWW.saveItems()
    }

}
