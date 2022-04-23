package com.stuffToTake.controllers

import com.stuffToTake.models.*
import tornadofx.Controller

class AddItemController : Controller() {

    val items : ItemsListModel by inject()  // TODO is this correct?

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
        items.item = ItemsList(mutableListOf(), mutableListOf(), mutableListOf())
        items.item.addItem(item)

    }

}
