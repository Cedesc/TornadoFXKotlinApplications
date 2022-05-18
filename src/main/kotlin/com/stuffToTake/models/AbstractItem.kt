package com.stuffToTake.models

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleSetProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableSet
import tornadofx.*

abstract class AbstractItem(itemName: String,
                            itemAmount: String,
                            itemToTake: Boolean,
                            itemCategories: Set<Category> = emptySet())  {

    // the amount can be given as an Integer instead of a String
    constructor(itemName: String, itemAmount: Int, itemToTake: Boolean, itemCategories: Set<Category> = emptySet())
            : this(itemName, itemAmount.toString(), itemToTake, itemCategories)


    val nameProperty: SimpleStringProperty = SimpleStringProperty(itemName)
    var name: String by nameProperty

    val amountProperty: SimpleStringProperty = SimpleStringProperty(itemAmount)
    var amount: String by amountProperty

    val toTakeProperty: SimpleBooleanProperty = SimpleBooleanProperty(itemToTake)
    var toTake: Boolean by toTakeProperty

    val categoriesProperty: SimpleSetProperty<Category> =
        SimpleSetProperty(mutableSetOf<Category>().toObservable())
    val categories: ObservableSet<Category> by categoriesProperty


    init {
        itemCategories.forEach { category ->
            if (! this.addCategory(category))
                throw Exception("Duplicated Category? " +
                        "This shouldn't be possible, because it is a set what is given, not a list")
        }
    }

    /**
     * Changes the "to_take" variable to true.
     */
    open fun markAsToTake() {
        toTake = true
    }

    /**
     * Changes the "to_take" variable to false.
     */
    open fun unmarkAsToTake() {
        toTake = false
    }

    /**
     * Returns true if "amount" has a valid value, false if it's empty.
     */
    fun hasAmount(): Boolean {
        return amount != ""
    }

    /**
     * Adds a category. Returns false if the category was already in the list.
     */
    fun addCategory(category: Category): Boolean {
        if (category in categories)
            return false
        else
            categories.add(category)
        return true
    }

    /**
     * Overrides the toString method for better (debug-) output.
     */
    override fun toString(): String {

        return if (hasAmount())
            "Item: $name\n" +
            "    Amount: $amount\n" +
            "    Categories: $categories\n" +
            "    To Take: $toTake"
        else
            "Item: $name\n" +
            "    Amount: -\n" +
            "    Categories: $categories\n" +
            "    To Take: $toTake"
    }

    /**
     * Overrides the equals method and checks if the toStrings of both are equal.
     */
    override fun equals(other: Any?): Boolean {
        if (other is AbstractItem) {
            return toString() == other.toString()
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = nameProperty.hashCode()
        result = 31 * result + amountProperty.hashCode()
        result = 31 * result + toTakeProperty.hashCode()
        result = 31 * result + categoriesProperty.hashCode()
        return result
    }

}
