package com.stuffToTake.controllers

import com.stuffToTake.models.*
import com.stuffToTake.saves.ItemParser
import tornadofx.Controller

class ItemsListController : Controller() {

    val itemsList = ItemsList(itemParser = ItemParser("src/main/kotlin/com/stuffToTake/saves/items.txt"))

    init {
        // Fill the items list with the saved items.
        if (! itemsList.loadSavedItems())
            throw Exception("The item list isn't empty or the filepath of the item parser is empty.")
    }

    fun addItem(name: String, amount: String, type: String, categories: List<Category>, toTake: Boolean) {

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

        println(item)  // TODO delete

        // TODO Add somewhere, but where ?!? Is this correct???
        // addItemController.itemsListModel.item = ItemsList()
        // addItemController.itemsListModel.item.addArbitraryItem(item)

        itemsList.addArbitraryItem(item)

    }

    fun testAdd() {  // TODO delete
        // addItemController.itemsList.addEssentialItem(EssentialItem("Something", "nothing", false))
    }

}
