package com.stuffToTake.models

class OneTimeItem(itemName: String,
                  itemAmount: String,
                  itemToTake: Boolean,
                  itemCategories: Set<Category> = emptySet())
    : AbstractItem(itemName, itemAmount, itemToTake, itemCategories) {

    // the amount can be given as an Integer instead of a String
    constructor(itemName: String, itemAmount: Int, itemToTake: Boolean, itemCategories: Set<Category> = emptySet())
            : this(itemName, itemAmount.toString(), itemToTake, itemCategories)


    override fun markedAsToTake(showItemToTake: Boolean): Boolean {  // TODO create tests
        TODO("Not yet implemented")
        // delete this item (how? with a return true and so the item will be deleted? in this case the method to set the
        // toTake in ShowItem should be modified...
        // or give the item as a parameter and delete it with a function call here???
        // or create more abstract methods "itemShouldDeleted(): Boolean" and "itemShouldBeModified(): Boolean"???)
    }

    override fun unmarkedAsToTake(showItemToTake: Boolean): Boolean {  // TODO create tests
        return false
    }

    override fun toString(): String {
        return "One Time ${super.toString()}"
    }

    override fun copy(): OneTimeItem {  // TODO create tests
        return OneTimeItem(this.name, this.amount, this.toTake, this.categories)
    }

}
