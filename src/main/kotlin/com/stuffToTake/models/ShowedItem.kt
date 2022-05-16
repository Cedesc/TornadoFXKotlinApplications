package com.stuffToTake.models

import tornadofx.ItemViewModel

class ShowedItem(val originalItem: AbstractItem)
    : AbstractItem(originalItem.name, originalItem.amount, originalItem.toTake, originalItem.categories) {

    init {
        if (originalItem is ShowedItem)
            throw Exception("Cannot build a ShowedItem of a ShowedItem")
    }

    override fun markAsToTake() {
        super.markAsToTake()
    }

    override fun unmarkAsToTake() {
        super.unmarkAsToTake()
    }

    override fun toString(): String {
        return "Showed $originalItem"
    }

}

//class ShowedItemModel : ItemViewModel<AbstractItem>() {
//    val name = bind(AbstractItem::nameProperty)
//    val amount = bind(AbstractItem::amountProperty)
//    val toTake = bind(AbstractItem::toTakeProperty)
//    val categories = bind(AbstractItem::categoriesProperty)
//}

